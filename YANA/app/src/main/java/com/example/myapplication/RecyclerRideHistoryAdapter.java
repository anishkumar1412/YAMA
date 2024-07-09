package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerRideHistoryAdapter extends RecyclerView.Adapter<RecyclerRideHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<RideModel> rideModelArrayList;
RecyclerRideHistoryAdapter(Context context, ArrayList<RideModel> rideModelArrayList)
{
    this.context = context;
    this.rideModelArrayList = rideModelArrayList;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ride_history_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.img.setImageResource(rideModelArrayList.get(position).img);
    holder.PickupAddress.setText(rideModelArrayList.get(position).PickupAddress);
    holder.RideDuration.setText(rideModelArrayList.get(position).RideDuration);
    holder.RideTime.setText(rideModelArrayList.get(position).RideTime);
    holder.RideDate.setText(rideModelArrayList.get(position).RideDate);
    holder.Fair.setText(String.valueOf(rideModelArrayList.get(position).Fair));

    }

    @Override
    public int getItemCount() {
        return rideModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

    TextView Fair, PickupAddress, RideDuration,RideTime, RideDate;
    ImageView img;
        public ViewHolder(View itemView)
        {
            super(itemView);
            Fair = itemView.findViewById(R.id.Fair);
            PickupAddress = itemView.findViewById(R.id.PickupAddress);
            RideDuration = itemView.findViewById(R.id.RideDuration);
            RideTime = itemView.findViewById(R.id.RideTime);
            RideDate = itemView.findViewById(R.id.RideDate);
            img = itemView.findViewById(R.id.img);
        }
    }



}
