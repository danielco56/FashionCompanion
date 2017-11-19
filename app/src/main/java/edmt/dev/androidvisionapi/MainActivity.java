package edmt.dev.androidvisionapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Caption;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Bitmap mBitmap;
    ImageView imageView;
    public VisionServiceClient visionServiceClient = new VisionServiceRestClient("065713641e6e48228364a33d44bc50bc", "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0");

    private final static int SELECT_PHOTO = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         imageView = (ImageView)findViewById(R.id.imageView);

        Button btnProcess = (Button)findViewById(R.id.btnProcess);
        Button upload = (Button)findViewById(R.id.upload);

      //  imageView.setImageBitmap(mBitmap);

        //Convert image to stream


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);

            }
        });


        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 mBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                 final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                final AsyncTask<InputStream,String,String> visionTask = new AsyncTask<InputStream, String, String>() {
                    ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                    @Override
                    protected String doInBackground(InputStream... params) {
                      try{
                          publishProgress("Recognizing....");
                          String[] features = {"Color"};
                          String[] details = {};

                          AnalysisResult result = visionServiceClient.analyzeImage(params[0],features,details);

                          String strResult = new Gson().toJson(result);
                          return strResult;

                      } catch (Exception e) {
                         return null;
                      }
                    }

                    @Override
                    protected void onPreExecute() {
                        mDialog.show();
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        mDialog.dismiss();

                        AnalysisResult result = new Gson().fromJson(s,AnalysisResult.class);
                        TextView textView = (TextView)findViewById(R.id.txtDescription);
                        StringBuilder stringBuilder = new StringBuilder();
                   //     for(Caption caption:result.description.captions)
                   //     {
                            stringBuilder.append(result.color.dominantColorForeground+" "+result.color.dominantColorBackground +" "+result.color.accentColor);
                  //      }
                        textView.setText(stringBuilder);

                    }

                    @Override
                    protected void onProgressUpdate(String... values) {
                        mDialog.setMessage(values[0]);
                    }
                };

                visionTask.execute(inputStream);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData()!=null) {
            Uri pickedImage = data.getData();
           try {
                 mBitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),pickedImage);
                 imageView.setImageBitmap(mBitmap);
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }

        }
    }
}
