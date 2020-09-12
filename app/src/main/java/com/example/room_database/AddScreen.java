package com.example.room_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddScreen extends AppCompatActivity {
private EditText edittitle;
private EditText editdesc;
private Button btnadd;
private Spinner spinner;
AppDatabase appDatabase;
    String title;
    String desc;

int i=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);
        edittitle=findViewById(R.id.edttit);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Appdatabase").build();
        editdesc=findViewById(R.id.edtdesc);
        spinner=findViewById(R.id.spinprio);
        btnadd=findViewById(R.id.addbtn);
        String arr[] =getResources().getStringArray(R.array.data_prio);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=edittitle.getText().toString();
                desc=editdesc.getText().toString();
                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(desc) || i==-1){
                    Toast.makeText(getApplicationContext(),"Fill all categories",Toast.LENGTH_SHORT).show();
                }
                else{
                        MyAsync myAsync=new MyAsync();
                        myAsync.execute();
                }
            }
        });
    }
    class MyAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            todo_table todo_table = new todo_table(title, desc, i);
            appDatabase.todoDao().insert(todo_table);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}