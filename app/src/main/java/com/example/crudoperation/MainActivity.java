package com.example.crudoperation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et11;
    EditText et22;
    EditText et33;
    Button view1;
    Button insert;
    Button delete3;
    Button update4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database g=new database(this);
     //   SQLiteDatabase db=g.getReadableDatabase();

        et11=findViewById(R.id.et1);
        et22=findViewById(R.id.et2);
        et33=findViewById(R.id.et3);
        view1=findViewById(R.id.view);
        insert=findViewById(R.id.insert);
        delete3=findViewById(R.id.delete);
        update4=findViewById(R.id.update);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et11.getText().toString();
                String username=et22.getText().toString();
                String password=et33.getText().toString();

                if(name.equals("") || username.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this,"Enter all fields ",Toast.LENGTH_SHORT).show();
                }else {
                    boolean i=g.insert_data(name, username, password);
                    if(i==true){
                        Toast.makeText(MainActivity.this,"data entry Successfull",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Not Successfull",Toast.LENGTH_SHORT).show();
                    }
                }
                et11.setText("");
                et22.setText("");
                et33.setText("");}
        });
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t=g.getinfo();
                if(t.getCount()==0){
                    Toast.makeText(MainActivity.this,"No data Found",Toast.LENGTH_SHORT).show();
                }else{
                    StringBuffer buffer=new StringBuffer();
                    while(t.moveToNext()){
                        buffer.append("id # " + t.getString(0)+"\n");
                        buffer.append("Name  " +t.getString(1)+"\n");
                        buffer.append("Username  " +t.getString(2)+"\n");
                        buffer.append("Password  " +t.getString(3)+"\n\n\n");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Users data");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });
        update4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et11.getText().toString();
                String username=et22.getText().toString();
                String password=et33.getText().toString();
                boolean i=g.update_data(name, username, password);
                if(i==true){
                    Toast.makeText(MainActivity.this,"data updated sucessfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Sorry! Data not updated",Toast.LENGTH_SHORT).show();
                }
                et11.setText("");
                et22.setText("");
                et33.setText("");}
        });
        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String username=et22.getText().toString();
                boolean i=g.delete_data(username);
                if(i==true){
                    Toast.makeText(MainActivity.this,"Deleted sucessfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Sorry! Not deleted",Toast.LENGTH_SHORT).show();
                }
            et11.setText("");
            et22.setText("");
            et33.setText("");}
        });

    }
}