package edmt.dev.androidvisionapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button hm = (Button)findViewById(R.id.hm);
        Button zara = (Button)findViewById(R.id.zara);
        Button nike = (Button)findViewById(R.id.nike);
        Button ca = (Button)findViewById(R.id.ca);

        zara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this , MainActivity.class);
                startActivity(intent);
            }
        });

        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent1);
            }
        });


        nike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent2);
            }
        });


        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent3);
            }
        });

    }
}
