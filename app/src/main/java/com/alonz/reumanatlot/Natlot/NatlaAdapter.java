package com.alonz.reumanatlot.Natlot;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alonz.reumanatlot.AppDatabase;
import com.alonz.reumanatlot.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alonz on 13/11/2017.
 */

public class NatlaAdapter extends RecyclerView.Adapter<NatlaAdapter.ViewHolder>{
    private List<Natla> mItemData;
    private Context context;
    private ImageView favoriteButton;

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.e("detached","detached");
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView holderImageView;
        public ProgressBar holderPb;
        public ImageView holderFavoriteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            holderImageView = itemView.findViewById(R.id.image);
            holderPb = itemView.findViewById(R.id.pb_item);
            holderFavoriteButton = itemView.findViewById(R.id.favoriteButton);
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Natla clickedDataItem = mItemData.get(pos);
                        Toast.makeText(view.getContext(), "You clicked " + clickedDataItem.getUrl()+" "+"favorite state is "+clickedDataItem.getFavorite(), Toast.LENGTH_SHORT).show();
                        Log.e("ss",clickedDataItem.getUrl());
                        if (!clickedDataItem.getFavorite()){
                            clickedDataItem.setFavorite(true);
                            AppDatabase.getAppDatabase(context).natlaDao().insert(createNote(clickedDataItem.getUrl(),true));
                            notifyItemChanged(pos);
                        }else{
                            clickedDataItem.setFavorite(false);
                            AppDatabase.getAppDatabase(context).natlaDao().delete(clickedDataItem);
                            notifyItemChanged(pos);
                        }

                    }
                }
            });

        }

        @Override
        public void onClick(View view) {

        }
    }

    public NatlaAdapter(Context context) {
        this.context = context;
    }
    public NatlaAdapter(Context context, List<Natla> mItemData) {
        this.mItemData=mItemData;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view,parent,false);
        favoriteButton = view.findViewById(R.id.favoriteButton);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //Glide.with(context).load(mItemData.get(mPosition)).error(R.id.tv_error_message_display).into(holder.favoriteButton);

        holder.holderPb.setVisibility(View.VISIBLE);
        Natla natla = mItemData.get(position);
        String url= natla.getUrl();
        if (natla.getFavorite()){
            holder.holderFavoriteButton.setBackgroundResource(R.mipmap.ic_favorites_red);
        }else {
            holder.holderFavoriteButton.setBackgroundResource(R.mipmap.ic_favorite_hollow_black);
        }


       // Picasso.with(context).load(x).resize(200,200).into(holder.favoriteButton);

        Glide.with(context)
                .load(url)
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
                      holder.holderPb.setVisibility(View.GONE);
                        return false;
                    }

                })
                .into(new FileTarget(url, holder.holderImageView,300,300));



    }

    @Override
    public int getItemCount() {
        return mItemData.size();
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
    private Natla createNote(String url, Boolean favorite){
        Natla note = new Natla();
        note.setUrl(url);
        note.setFavorite(favorite);
        return note;
    }
    public void updateList(List<Natla> updatedList){
        mItemData = updatedList;
        notifyDataSetChanged();
    }
}
