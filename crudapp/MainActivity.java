package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtname ,edtage , edtdob;
    Spinner sp;
    Button btnins , btnup , btndel , btnview;
    ListView list;
    DBhelper db;
    String dob = "";
    RadioGroup rg ;
    CheckBox gmale , gfemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtname = findViewById(R.id.edtname);
        edtage = findViewById(R.id.edtage);
        edtdob = findViewById(R.id.edtdob);
        sp = findViewById(R.id.spinner);
        btnins = findViewById(R.id.insert);
        btnup = findViewById(R.id.update);
        btndel = findViewById(R.id.delete);
        btnview = findViewById(R.id.view);
        list = findViewById(R.id.listview);
        db = new DBhelper(this);
       /* rg = findViewById(R.id.genradio);
        gmale = findViewById(R.id.chkmale);
        gfemale = findViewById(R.id.chkfemale);
    //radio
        int selectedid = rg.getCheckedRadioButtonId();
        String gen = "";
        if(selectedid != -1){
            RadioButton rb = findViewById(selectedid);
            gen = rb.getText().toString();
        }
    //check
        String checkgen = "";
        if(gmale.isChecked()){
            checkgen += "Male";
        }
        else {
            checkgen += "Female";
        }
*/

        //spinner
        String course[] = {"BBA" , "BCA" , "MCA" , "MSC" , "BCS" , "AIML"};
        ArrayAdapter adapter = new ArrayAdapter(this , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item , course);
        sp.setAdapter(adapter);

        //date picker
        edtdob.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dp = new DatePickerDialog(this , (view , y , m , d) -> {
                dob = d + "/" + (m+1) + "/" + y;
                edtdob.setText(dob);
            } , year, month ,day);
            dp.show();
        });

        //String finalCheckgen = checkgen;
        btnins.setOnClickListener(v -> {
            boolean res = db.insert(edtname.getText().toString() ,
                                    Integer.parseInt(edtage.getText().toString()),
                                    dob ,
                                    sp.getSelectedItem().toString());
                                   // String.valueOf(selectedid),
                                    //finalCheckgen);
            Toast.makeText(this , "Inserted" , Toast.LENGTH_SHORT).show();
        });

        btnup.setOnClickListener(v -> {
            boolean res = db.update(edtname.getText().toString() ,
                                    Integer.parseInt(edtage.getText().toString()),
                                    dob ,
                                    sp.getSelectedItem().toString());
            Toast.makeText(this , "updated" , Toast.LENGTH_SHORT).show();
        });

        btndel.setOnClickListener(v -> {
            boolean res = db.delete(edtname.getText().toString());
            Toast.makeText(this , "deleted" , Toast.LENGTH_SHORT).show();
        });

        ArrayList<String> listdata = new ArrayList<>();
        btnview.setOnClickListener(v -> {
            Cursor c = db.view();
            while(c.moveToNext()) {
                String data =   "Name : " + c.getString(1) + "\n" +
                                "Age : " + c.getString(2) + "\n" +
                                "DOB : " + c.getString(3) + "\n" +
                                "Course : " + c.getString(4) + "\n" ;
                                /*"Radio gen : " c.getString(5)+ "\n" +
                                "checkbox gen : " c.getString(6);*/
                listdata.add(data);
            }
            ArrayAdapter<String> myadapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , listdata);
            list.setAdapter(myadapter);
        });
    }
}