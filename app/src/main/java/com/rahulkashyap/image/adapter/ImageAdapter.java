package com.rahulkashyap.image.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahulkashyap.image.model.Image;
import com.rahulkashyap.image.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<Image> images;
    Context context;
    String url ="https://picsum.photos/300/300?image=";

    public ImageAdapter(ArrayList<Image> images,Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {

        Image object = images.get(position);

        Glide.with(context).load(url+object.getId()).centerCrop().into(holder.imageView);
        holder.authorName.setText(object.getAuthor());

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView authorName;

        public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.image);
                authorName = itemView.findViewById(R.id.authorName);
        }
    }
}
