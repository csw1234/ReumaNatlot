package com.alonz.reumanatlot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alonz on 13/11/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private String[] mItemData;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            progressBar = (ProgressBar)itemView.findViewById(R.id.pb_item);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){

            }
        }
    }
    public ItemAdapter(Context context, String[] mItemData) {
        this.mItemData = mItemData;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //Glide.with(context).load(mItemData.get(position)).error(R.id.tv_error_message_display).into(holder.imageView);
        holder.progressBar.setVisibility(View.VISIBLE);
        String x = (String)mItemData[position];

        Glide.with(context)
                .load(x)
                .asBitmap()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                      holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                })
                .into(new FileTarget(x, holder.imageView,300,300));



    }

    @Override
    public int getItemCount() {
        return mItemData.length;
    }


    public class FileTarget extends SimpleTarget<Bitmap> {

        private String fileName;
        private Bitmap.CompressFormat format;
        private int quality;
        private ImageView imageViewTarget;

        public FileTarget(String fileName, ImageView imageViewTarget) {
            this(fileName, imageViewTarget, Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL, Bitmap.CompressFormat.PNG, 100);
        }

        public FileTarget(String fileName, ImageView imageViewTarget, int width, int height) {
            this(fileName, imageViewTarget, width, height, Bitmap.CompressFormat.PNG, 100);
        }

        private FileTarget(String fileName, ImageView imageViewTarget, int width, int height, Bitmap.CompressFormat format, int quality) {
            super(width, height);
            this.fileName = fileName;
            this.format = format;
            this.quality = quality;
            this.imageViewTarget = imageViewTarget;

        }

        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            try {
                imageViewTarget.setImageBitmap(resource);

                if (fileName != null) {
                    FileOutputStream out = new FileOutputStream(fileName);
                    resource.compress(format, quality, out);
                    out.flush();
                    out.close();
                    onFileSaved();
                }
            } catch (IOException e) {
                e.printStackTrace();
                onSaveException(e);
            }
        }

        private void onFileSaved() {
            // do nothing, should be overriden (optional)
        }

        private void onSaveException(Exception e) {
            // do nothing, should be overriden (optional)
        }
    }

}
