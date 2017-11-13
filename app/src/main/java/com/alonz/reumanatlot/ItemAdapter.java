package com.alonz.reumanatlot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by alonz on 13/11/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private ArrayList mItemData;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){

            }
        }
    }
    public ItemAdapter(Context context, ArrayList mItemData) {
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
        Glide.with(context).load(mItemData.get(position)).asBitmap().error(R.id.tv_error_message_display)
//                .listener(new RequestListener<String, Bitmap>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        onPallete(Palette.from(resource).generate());
//                        holder.imageView.setImageBitmap(resource);
//                        return false;
//                    }
//                    public void onPallete(Palette pallete){
//                        if (null!=pallete){
//                            ViewGroup parent = (ViewGroup)holder.imageView.getParent().getParent();
//                            parent.setBackgroundColor(pallete.getDarkVibrantColor(Color.GRAY));
//                        }
//                    }
//                })

                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mItemData.size();
    }
}
