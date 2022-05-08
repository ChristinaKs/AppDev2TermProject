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
    ArrayList<String> clientId = new ArrayList<>();
    LayoutInflater minInflater;
    Context context;

    public BookingAdapter(ArrayList<String> idAdapter, ArrayList<String> timeAdapter,Context context) {
        this.idAdapter = idAdapter;
        this.timeAdapter = timeAdapter;
        this.timeSlot = timeSlot;
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
        holder.timeSlot.setText(timeAdapter.get(position));

 /*       holder.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeId = idAdapter.get(position);
                // delete time slot availability + add the appointment
                Integer isTimeSlotDeleted = Booking.db.deleteTimeSlot(timeSlot.get(holder.getAdapterPosition()));
                // inserting appointment doesnt exist
                boolean insertAppointment = Booking.db.insertAppointment(timeSlot.get(holder.getAdapterPosition()));
                if(isTimeSlotDeleted > 0 && insertAppointment == true){
//                    id++
                    timeSlot.remove(timeSlot.get(holder.getAdapterPosition()));
                    idAdapter.add(idAdapter.get(holder.getAdapterPosition()));
                    // how tf am i supposed to add the therapist license and client ID?
                    notifyItemRemoved(holder.getAdapterPosition());

                } else {
                    Toast.makeText(view.getContext(),
                            "There was an issue with removing the time slot", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
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
