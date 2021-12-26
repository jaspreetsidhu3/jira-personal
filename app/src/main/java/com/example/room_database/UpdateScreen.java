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

import org.w3c.dom.Text;

public class UpdateScreen extends AppCompatActivity {
    //Classes
    private EditText edittitle;
    private EditText editdesc;
    private Spinner spinnerPriority;
    private Spinner spinnerStatus;
    private Button buttonupdate;
    // Variable
    int ID = 0;
    int priorityIndex = -1;
    int statusIndex = -1;
    String utitle;
    String udesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_screen);
        edittitle = findViewById(R.id.edtupdatetit);
        editdesc = findViewById(R.id.edtupdatedesc);
        int i = getIntent().getExtras().getInt("id", -1);
        String titlefetch = getIntent().getExtras().getString("title", "null");
        String descfetch = getIntent().getExtras().getString("description", "null");
        int priorityfetch = getIntent().getExtras().getInt("priority", 0);
        int statusfetch = getIntent().getExtras().getInt("status", 0);


        spinnerPriority = findViewById(R.id.spinupdateprio);
        spinnerStatus = findViewById(R.id.spinupdatestatus);
        buttonupdate = findViewById(R.id.updatebtn);

        String arrPriority[] = getResources().getStringArray(R.array.data_prio);
        ArrayAdapter arrayAdapterPriority = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrPriority);
        spinnerPriority.setAdapter(arrayAdapterPriority);
        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priorityIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String arrStatus[] = getResources().getStringArray(R.array.data_status);
        ArrayAdapter arrayAdapterStatus = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrStatus);
        spinnerStatus.setAdapter(arrayAdapterStatus);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statusIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (i == -1) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            ID = i;
            spinnerStatus.setSelection(statusfetch);
            spinnerPriority.setSelection(priorityfetch);
            edittitle.setText(titlefetch);
            editdesc.setText(descfetch);
        }


        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utitle = edittitle.getText().toString();
                udesc = editdesc.getText().toString();
                if (TextUtils.isEmpty(utitle) || TextUtils.isEmpty(udesc) || priorityIndex == -1 || statusIndex == -1) {
                    Toast.makeText(getApplicationContext(), "Please fill all the blocks", Toast.LENGTH_SHORT).show();
                } else {
                    MyAsyncUpdate myAsyncUpdate = new MyAsyncUpdate();
                    myAsyncUpdate.execute();
                }
            }
        });
    }

    class MyAsyncUpdate extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Appdatabase").build();
            appDatabase.todoDao().update(utitle, udesc, priorityIndex, statusIndex, ID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}