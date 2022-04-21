package com.example.findmytherapist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    ArrayList<String> firstnames = new ArrayList<>();
    ArrayList<String> lastnames = new ArrayList<>();
    ArrayList<String> ages = new ArrayList<>();
    ArrayList<String> genders = new ArrayList<>();
    Context mContext;
    LayoutInflater minflator;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflator.from(parent.getContext()).inflate(R.layout.activity_client_child_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstName.setText(firstnames.get(position));
        holder.lastName.setText(lastnames.get(position));
        holder.age.setText(ages.get(position));
        holder.gender.setText(genders.get(position));
    }

    @Override
    public int getItemCount() {
        return firstnames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstName,lastName,age,gender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstNameTextView);
            lastName = itemView.findViewById(R.id.lastNameTextView);
            age = itemView.findViewById(R.id.ageTextView);
            gender = itemView.findViewById(R.id.genderTextView);
        }
    }
}
