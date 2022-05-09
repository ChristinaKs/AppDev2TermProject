package com.example.findmytherapist;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyClientsAdapter extends RecyclerView.Adapter<MyClientsAdapter.MyViewHolder>{

//    ArrayList<String> myClientsID = new ArrayList<>();
    ArrayList<String> myClientsFirstName = new ArrayList<>();
    ArrayList<String> myClientsLastName = new ArrayList<>();
    ArrayList<String> myClientsAge = new ArrayList<>();
    ArrayList<String> myClientsGender = new ArrayList<>();
    Context mContext;
    Context context;
    LayoutInflater minflator;

    public MyClientsAdapter(/*ArrayList<String> myClientsID, */ArrayList<String> myClientsFirstName,
                            ArrayList<String> myClientsLastName, ArrayList<String> myClientsAge,
                            ArrayList<String> myClientsGender, Context mContext){
//        this.myClientsID = myClientsID;
        this.myClientsFirstName = myClientsFirstName;
        this.myClientsLastName = myClientsLastName;
        this.myClientsAge = myClientsAge;
        this.myClientsGender = myClientsGender;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyClientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = minflator.from(parent.getContext()).inflate(R.layout.activity_client_child_row, parent, false);
        return new MyClientsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClientsAdapter.MyViewHolder holder, int position){
        DBHelper db = new DBHelper(context);

        String clientID = "";

        Cursor myClientsID = db.getMyClients();

        while(myClientsID.moveToNext()){
            clientID = myClientsID.getString(0);
        }

        holder.clientID.setText(clientID);
        holder.clientFirstName.setText(myClientsFirstName.get(position));
        holder.clientLastName.setText(myClientsLastName.get(position));
        holder.clientAge.setText(myClientsAge.get(position));
        holder.clientGender.setText(myClientsGender.get(position));
    }

    @Override
    public int getItemCount(){
        return myClientsFirstName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView clientFirstName,clientLastName, clientAge, clientGender, clientID;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            clientID = itemView.findViewById(R.id.idTextView);
            clientFirstName =  itemView.findViewById(R.id.firstNameTextView);
            clientLastName = itemView.findViewById(R.id.lastNameTextView);
            clientAge = itemView.findViewById(R.id.ageTextView);
            clientGender = itemView.findViewById(R.id.genderTextView);
        }
    }
}
