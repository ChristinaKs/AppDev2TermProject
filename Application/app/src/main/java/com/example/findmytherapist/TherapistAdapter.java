package com.example.findmytherapist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TherapistAdapter extends RecyclerView.Adapter<TherapistAdapter.MyViewHolder>{

    ArrayList<String> fNameList = new ArrayList<>();
    ArrayList<String> lNameList = new ArrayList<>();
    ArrayList<String> gender = new ArrayList<>();
    ArrayList<String> platformsList = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();
    Context nContext;
    LayoutInflater nimflator;

    public TherapistAdapter(ArrayList<String> fNameList, ArrayList<String> lNameList, ArrayList<String> gender, ArrayList<String> platformsList, ArrayList<String> address,Context nContext) {
        this.fNameList = fNameList;
        this.lNameList = lNameList;
        this.gender = gender;
        this.platformsList = platformsList;
        this.address = address;
        this.nContext = nContext;
    }

    @NonNull
    @Override
    public TherapistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = nimflator.from(parent.getContext()).inflate(R.layout.therapist_childview, parent, false);
        return new TherapistAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TherapistAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.fName.setText(fNameList.get(position));
        holder.lName.setText(lNameList.get(position));
        holder.gender.setText(gender.get(position));
        holder.platforms.setText(platformsList.get(position));
        String addy = address.get(position);
        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addy = address.get(position);
                Geocoder geocoder = new Geocoder(nContext);
                List<Address> addressList;

                try {
                    addressList = geocoder.getFromLocationName(addy,1);
                    if(addressList != null){
                        double doubleLat = addressList.get(0).getLatitude();
                        double doubleLong= addressList.get(0).getLongitude();
                        Intent intent = new Intent(nContext,MapsActivity.class);
                        intent.putExtra("Latitude",doubleLat);
                        intent.putExtra("Longitude",doubleLong);
                        nContext.startActivity(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send to another activity displaying time slots from that therapist
                // THIS WILL HAVE TO SEND A NOTIFICATION FOR THE THERAPIST IN QUESTION
            }
        });

    }

    @Override
    public int getItemCount() {
        return fNameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        Button bookNow,location;
        TextView fName, lName, gender, platforms;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //if it's 0 don't do anything, if it's 1 put " "+platformName
            fName = itemView.findViewById(R.id.therapistFnameTV);
            lName = itemView.findViewById(R.id.therapistLnameTV);
            gender = itemView.findViewById(R.id.genderTV);
            platforms = itemView.findViewById(R.id.platformTV);
            bookNow = itemView.findViewById(R.id.bookNowBtn);
            location = itemView.findViewById(R.id.mapButton);
        }
    }
}
