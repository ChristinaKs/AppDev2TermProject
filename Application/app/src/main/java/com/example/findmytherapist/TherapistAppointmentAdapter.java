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

public class TherapistAppointmentAdapter extends RecyclerView.Adapter<TherapistAppointmentAdapter.MyViewHolder>{

    ArrayList<String> clientID = new ArrayList<>();
    ArrayList<String> appointmentTime = new ArrayList<>();
    Context mContext;
    Context context;
    LayoutInflater minflator;

    public TherapistAppointmentAdapter(ArrayList<String> clientID, ArrayList<String> appointmentTime,
                                      Context mContext){

        this.clientID = clientID;
        this.appointmentTime = appointmentTime;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TherapistAppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = minflator.from(parent.getContext()).inflate(R.layout.therapist_appointments_childview, parent, false);
        return new TherapistAppointmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TherapistAppointmentAdapter.MyViewHolder holder, int position){
        DBHelper db = new DBHelper(context);

        String clientID = "";
        String appointmentTime = "";

        Cursor cursor = db.getTherapistAppointments();

        while(cursor.moveToNext()){
            clientID = cursor.getString(2);
            appointmentTime = cursor.getString(3);
        }

        holder.clientID.setText(clientID);
        holder.appointmentTime.setText(appointmentTime);
    }

    @Override
    public int getItemCount(){
        return appointmentTime.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView clientID, appointmentTime;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            clientID = itemView.findViewById(R.id.clientIDAppointment);
            appointmentTime = itemView.findViewById(R.id.therapistAppointmentTime);
        }
    }
}
