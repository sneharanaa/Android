package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnins , btnupdt , btndlt , btnview;
    EditText edid , edname , edage;
    TextView tv;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edid = findViewById(R.id.edtid);
        edname = findViewById(R.id.edtname);
        edage = findViewById(R.id.edtage);
        btnins = findViewById(R.id.insert);
        btnupdt = findViewById(R.id.update);
        btndlt = findViewById(R.id.delete);
        btnview = findViewById(R.id.display);
        tv = findViewById(R.id.textView);
        SharedPreferences sp = getSharedPreferences("mydata" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        btnins.setOnClickListener(v -> {
            editor.putString("id" , edid.getText().toString());
            editor.putString("name" , edname.getText().toString());
            editor.putString("age" , edage.getText().toString());
            editor.commit();
            Toast.makeText(this , "data inserted" , Toast.LENGTH_SHORT).show();
        });

        btnupdt.setOnClickListener(v -> {
            editor.putString("id" , edid.getText().toString());
            editor.putString("name" , edname.getText().toString());
            editor.putString("age" , edage.getText().toString());
            editor.commit();
            Toast.makeText(this , "data updated" , Toast.LENGTH_SHORT).show();
        });

        btndlt.setOnClickListener(v -> {
            editor.clear();
            editor.commit();
            Toast.makeText(this , "data deleted" , Toast.LENGTH_SHORT).show();
        });

        btnview.setOnClickListener( v -> {
            String id = sp.getString("id" , "");
            String name = sp.getString("name" , "");
            String age = sp.getString("age" , "");
            tv.setText("id :" + id + "\nName :" + name + "\nAge :" + age + "\n");
        });
    }
}