package com.example.findmytherapist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TherapistAdapter extends RecyclerView.Adapter<TherapistAdapter.MyViewHolder>{

    ArrayList<String> fNameList = new ArrayList<>();
    ArrayList<String> lNameList = new ArrayList<>();
    ArrayList<String> gender = new ArrayList<>();
    //ArrayList<String> platformsList = new ArrayList<>();
    Context nContext;
    LayoutInflater nimflator;

    public TherapistAdapter(ArrayList<String> fNameList, ArrayList<String> lNameList, ArrayList<String> gender, /*ArrayList<String> platformsList, */Context nContext) {
        this.fNameList = fNameList;
        this.lNameList = lNameList;
        this.gender = gender;
        //this.platformsList = platformsList;
        this.nContext = nContext;
    }

    @NonNull
    @Override
    public TherapistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = nimflator.from(parent.getContext()).inflate(R.layout.therapist_childview, parent, false);
        return new TherapistAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TherapistAdapter.MyViewHolder holder, int position) {
        holder.fName.setText(fNameList.get(position));
        holder.lName.setText(lNameList.get(position));
        holder.gender.setText(gender.get(position));
        //holder.platforms.setText(platformsList.get(position));

        holder.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // THIS WILL HAVE TO SEND A NOTIFICATION FOR THE THERAPIST IN QUESTION
            }
        });

    }

    @Override
    public int getItemCount() {
        return fNameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        Button bookNow;
        TextView fName, lName, gender, platforms;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //if it's 0 don't do anything, if it's 1 put " "+platformName
            fName = itemView.findViewById(R.id.therapistFnameTV);
            lName = itemView.findViewById(R.id.therapistLnameTV);
            gender = itemView.findViewById(R.id.genderTV);
            platforms = itemView.findViewById(R.id.platformTV);
            bookNow = itemView.findViewById(R.id.bookNowBtn);
        }
    }
}
