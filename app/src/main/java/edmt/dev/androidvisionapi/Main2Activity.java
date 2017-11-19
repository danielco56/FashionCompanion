package edmt.dev.androidvisionapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {
    ListView lst;
    String[] numeMagazin ={"H&m","Zara","Nike","C&A","Adidas"};
    String[] hashTag={"#Fasion #Style #NewCollection","#ForWomen #ForMen #ForKids","#Sport #ForEver #Fresh","#NewCollection #Dress #T-Shirts","#Sneakers #Sports #trendy","#Active #Beachlife #Boho #Goals" };
    Integer[] imagini={R.drawable.hm,R.drawable.zara,R.drawable.nike,R.drawable.ca,R.drawable.adidas};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        lst =(ListView) findViewById(R.id.lista);
        ListAdapter listadapter = new ListAdapter(this,numeMagazin,hashTag,imagini);
        lst.setAdapter(listadapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inet = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(inet);
            }
        });
    }
}
