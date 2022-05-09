package com.example.findmytherapist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{

    ArrayList<String> idAdapter = new ArrayList<>();
    ArrayList<String> timeAdapter = new ArrayList<>();
    ArrayList<String> appointment = new ArrayList<>();
    ArrayList<String> timeSlot = new ArrayList<>();
    ArrayList<String> TherapistLicense = new ArrayList<>();
    //ArrayList<String> clientId = new ArrayList<>();
    LayoutInflater minInflater;
    Context context;

    //id of people being passed on
    String therapistId;
    String clientId;
    public BookingAdapter(ArrayList<String> idAdapter, ArrayList<String> timeAdapter,String therapistId,String clientId,Context context) {
        this.idAdapter = idAdapter;
        this.timeAdapter = timeAdapter;
        //this.timeSlot = timeSlot;
        this.therapistId = therapistId;
        this.clientId = clientId;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minInflater.from(parent.getContext()).inflate(R.layout.booking_child, parent, false);
        return new BookingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DBHelper db = new DBHelper(context);
        holder.timeSlot.setText(timeAdapter.get(position));
        holder.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeId = idAdapter.get(position);
                // delete time slot availability + add the appointment
                db.deleteTimeSlot(timeId);
                // inserting appointment
                Integer therapist = Integer.valueOf(therapistId);
                Integer client = Integer.valueOf(clientId);
                boolean  isBooked = db.insertAppointment(therapist,client,timeAdapter.get(position));
                if(isBooked){
                    Toast.makeText(context,"Appointment booked!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
//                if(isTimeSlotDeleted > 0 && insertAppointment == true){
////                    id++
//                    timeSlot.remove(timeSlot.get(holder.getAdapterPosition()));
//                    idAdapter.add(idAdapter.get(holder.getAdapterPosition()));
//                    // how tf am i supposed to add the therapist license and client ID?
//                    notifyItemRemoved(holder.getAdapterPosition());
//
//                } else {
//                    Toast.makeText(view.getContext(),
//                            "There was an issue with removing the time slot", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return idAdapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView timeSlot;
        Button bookNow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeSlot = itemView.findViewById(R.id.timeTV);
            bookNow = itemView.findViewById(R.id.bookButton);
        }
    }
}
