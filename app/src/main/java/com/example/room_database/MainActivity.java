package com.example.room_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyItemClick {

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<SingleRow> singleRowArrayList;
    SingleRow singleRow;
    SingleRow singleRow_deletion;
    List<todo_table> li;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        singleRowArrayList = new ArrayList<>();
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Appdatabase").build();
        MyAsync myAsync = new MyAsync();
        myAsync.execute();
    }

    public void openAddScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), AddScreen.class);
        startActivity(intent);
        finish();
    }

    public void allprocessdone() {
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(getApplicationContext(), singleRowArrayList, this);
        recyclerView.setAdapter(myRecyclerAdapter);
    }

    @Override
    public void onCustomClick(View view, int position) {
        singleRow_deletion = singleRowArrayList.get(position);
        //Toast.makeText(getApplicationContext(), singleRow.getId() + "", Toast.LENGTH_SHORT).show();
        myAyncdelete myAyncdelete = new myAyncdelete();
        myAyncdelete.execute();
    }

    @Override
    public void onupdateCustomClick(View view, int position) {
        SingleRow singleRow_id = singleRowArrayList.get(position);
            Intent intent=new Intent(getApplicationContext(),UpdateScreen.class);
            intent.putExtra("id",singleRow_id.getId());
            intent.putExtra("title",singleRow_id.getTitle());
            intent.putExtra("description",singleRow_id.getDescription());
            startActivity(intent);
    }


    class MyAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            li = appDatabase.todoDao().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            singleRowArrayList.clear();
            for (int i = 0; i < li.size(); i++) {
                //Toast.makeText(getApplicationContext(), li.get(i).getTitle() + " " + li.get(i).getId(), Toast.LENGTH_SHORT).show();
                singleRow = new SingleRow(li.get(i).getPriority(), li.get(i).getTitle(), li.get(i).getDescription(), li.get(i).getId());
                singleRowArrayList.add(singleRow);
            }
            allprocessdone();

        }
    }

    class myAyncdelete extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Appdatabase").build();
            appDatabase.todoDao().deleteitem(singleRow_deletion.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Refresh..", Toast.LENGTH_SHORT).show();
            Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(refresh);
            finish();
        }
    }

}
