package com.example.room_database;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SingleRow> singleRowArrayList;
    public MyItemClick myItemClick;
    SingleRow singleRow;
    String arrStatus[];

    public MyRecyclerAdapter(Context context, ArrayList<SingleRow> singleRowArrayList, MyItemClick myItemClick) {
        this.context = context;
        this.myItemClick = myItemClick;
        this.singleRowArrayList = singleRowArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlerow, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, myItemClick);
        arrStatus = context.getResources().getStringArray(R.array.data_status);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        singleRow = singleRowArrayList.get(position);
        holder.textTitle.setText(singleRow.getTitle());
        holder.textDesc.setText(singleRow.getDescription());

        holder.textStatus.setText(arrStatus[singleRow.getStatus()]);
        int i = singleRow.getPrority();
        if (i == 0) {
            holder.priority.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));

        } else {
            holder.priority.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));

        }


    }

    @Override
    public int getItemCount() {
        return singleRowArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textTitle;
        TextView textDesc;
        TextView textStatus;
        ImageView priority;
        MyItemClick myItemClick;

        public MyViewHolder(@NonNull View itemView, final MyItemClick myItemClick) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            this.myItemClick = myItemClick;
            textDesc = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
            textStatus = itemView.findViewById(R.id.status);
            priority = itemView.findViewById(R.id.priority);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    myItemClick.onCustomClick(v, getAdapterPosition());
                    return false;
                }
            });
        }


        @Override
        public void onClick(View v) {
            myItemClick.onupdateCustomClick(v, getAdapterPosition());
        }
    }


}

interface MyItemClick {
    void onCustomClick(View view, int position);

    void onupdateCustomClick(View view, int position);
}


