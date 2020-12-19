package com.sharvil.securityapp.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharvil.securityapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    ArrayList<RequestHelperClass> requestLocations;

    public RequestAdapter(ArrayList<RequestHelperClass> requestLocations) {
        this.requestLocations = requestLocations;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card_design,parent,false);
        RequestViewHolder requestViewHolder = new RequestViewHolder(view);

        return requestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {

        RequestHelperClass requestHelperClass = requestLocations.get(position);

        holder.image.setImageResource(requestHelperClass.getImage());
        holder.name.setText(requestHelperClass.getName());
        holder.dateandtime.setText(requestHelperClass.getDateandtime());
        holder.reason.setText(requestHelperClass.getReason());

    }

    @Override
    public int getItemCount() {
        return requestLocations.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{


        ImageView image;
        TextView name, dateandtime, reason;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            image = itemView.findViewById(R.id.request_image);
            name = itemView.findViewById(R.id.request_name);
            dateandtime = itemView.findViewById(R.id.request_dateandtime);
            reason = itemView.findViewById(R.id.request_reason);

        }
    }
}