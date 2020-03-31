package com.example.todoapp.ui.firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.model.Work;

import java.util.List;

public class AdapterFirebase extends RecyclerView.Adapter<AdapterFirebase.ViewHolder> {

    List<Work> data;

    public AdapterFirebase(List<Work> data) {
        this.data = data;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_work,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleText,descriptionText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.textTitle);
            descriptionText = itemView.findViewById(R.id.textDesc);
        }

        public void bind(Work work){
            titleText.setText(work.getTitle());
            descriptionText.setText(work.getDescription());
        }
    }
}
