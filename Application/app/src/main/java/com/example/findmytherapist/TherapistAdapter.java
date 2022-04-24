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
    ArrayList<String> availabilitiesList = new ArrayList<>();
    ArrayList<String> platformsList = new ArrayList<>();
    ArrayList<String> specialitiesList = new ArrayList<>();
    Context nContext;
    LayoutInflater nimflator;

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
        holder.availabilities.setText(availabilitiesList.get(position));
        holder.platforms.setText(platformsList.get(position));
        holder.specialities.setText(specialitiesList.get(position));

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
        TextView fName, lName, availabilities, platforms, specialities;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fName = itemView.findViewById(R.id.therapistFnameTV);
            lName = itemView.findViewById(R.id.therapistLnameTV);
            availabilities = itemView.findViewById(R.id.availabilitiesTV);
            platforms = itemView.findViewById(R.id.platformTV);
            specialities = itemView.findViewById(R.id.specialitiesTV);
            bookNow = itemView.findViewById(R.id.bookNowBtn);
        }
    }
}
