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

public class ClientAppointmentAdapter extends RecyclerView.Adapter<ClientAppointmentAdapter.MyViewHolder>{

    ArrayList<String> therapistLicense = new ArrayList<>();
    ArrayList<String> appointmentTime = new ArrayList<>();
    Context mContext;
    Context context;
    LayoutInflater minflator;

    public ClientAppointmentAdapter(ArrayList<String> therapistLicense, ArrayList<String> appointmentTime,
                                       Context mContext){

        this.therapistLicense = therapistLicense;
        this.appointmentTime = appointmentTime;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ClientAppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = minflator.from(parent.getContext()).inflate(R.layout.client_appointments_childview, parent, false);
        return new ClientAppointmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAppointmentAdapter.MyViewHolder holder, int position){
        DBHelper db = new DBHelper(context);

        String therapistLicense = "";
        String appointmentTime = "";

        Cursor cursor = db.getTherapistAppointments();

        while(cursor.moveToNext()){
            therapistLicense = cursor.getString(2);
            appointmentTime = cursor.getString(3);
        }

        holder.therapistLicense.setText(therapistLicense);
        holder.appointmentTime.setText(appointmentTime);
    }

    @Override
    public int getItemCount(){
        return appointmentTime.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView therapistLicense, appointmentTime;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            therapistLicense = itemView.findViewById(R.id.therapistLicenseAppointment);
            appointmentTime = itemView.findViewById(R.id.clientAppointmentTime);
        }
    }
}