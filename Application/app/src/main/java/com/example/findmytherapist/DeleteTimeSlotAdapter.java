package com.example.findmytherapist;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
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

public class DeleteTimeSlotAdapter extends RecyclerView.Adapter<DeleteTimeSlotAdapter.ViewHolder> {

    ArrayList<String> timeSlot = new ArrayList<>();
    Context context;
    LayoutInflater minInflator;
    DBHelper db = new DBHelper(context);
    String id;
    public DeleteTimeSlotAdapter(ArrayList<String> timeSlot, Context context,String id) {
        this.timeSlot = timeSlot;
        this.context = context;
        this.id = id;
    }

    @NonNull
    @Override
    public DeleteTimeSlotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minInflator.from(parent.getContext()).inflate(R.layout.delete_time_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteTimeSlotAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.time.setText(timeSlot.get(position));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                View popupView = minInflator.from(context).inflate(R.layout.popup_delete_time_slot,null);
                Button yes = (Button)popupView.findViewById(R.id.yesButton);
                Button no = (Button) popupView.findViewById(R.id.noButton);
                dialog.setView(popupView);
                Dialog dialog2 = dialog.create();
                dialog2.show();
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //finding time_id in database using the time slot and the therapist id/license number
                        /*Integer timeId = db.getTimeIdByLicenseAndTime(id,timeSlot.get(position));
                        String timeIdToUse = timeId.toString();
                        //deleting it in db
                        db.deleteTimeSlot(timeIdToUse);*/
                        //deleting it in recyclerview
                        timeSlot.remove(position);
                        notifyItemRemoved(position);
                        dialog2.dismiss();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });

                //DELETING A TIME SLOT
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer isTimeSlotDeleted = DeleteTimeSlotTherapist.db.deleteTimeSlot(timeSlot.get(holder.getAdapterPosition()));
                        if(isTimeSlotDeleted > 0){
                            Toast.makeText(view.getContext(),
                                    "Time slot removed successfully", Toast.LENGTH_SHORT).show();
                            timeSlot.remove(timeSlot.get(holder.getAdapterPosition()));
                            notifyItemRemoved(holder.getAdapterPosition());
                        } else {
                            Toast.makeText(view.getContext(),
                                    "There was an issue with removing the time slot", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.timeslot);
            delete = itemView.findViewById(R.id.removeTimeSlotButton);
        }
    }
}
