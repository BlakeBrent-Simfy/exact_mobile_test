package com.exactmobile.jean.challenge.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exactmobile.jean.challenge.R;
import com.exactmobile.jean.challenge.callbacks.ImageClickedListener;
import com.exactmobile.jean.challenge.model.ImageItem;

import java.util.List;

/**
 * Created by jmvnkuru on 20/04/16.
 */
public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageItemViewHolder> {
    private List<ImageItem> imageItemList;
    private Context context;
    private ImageClickedListener imageClickedListener;


    public ImageItemAdapter(List<ImageItem> imageItemList, Context context, ImageClickedListener imageClickedListener) {
        this.imageItemList = imageItemList;
        this.context = context;
        this.imageClickedListener = imageClickedListener;
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View contactView = inflater.inflate(R.layout.image_list_item, parent, false);

        return new ImageItemViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ImageItemViewHolder holder, final int position) {
        Glide.with(context)
                .load(imageItemList.get(position).getImageUrl())
                .into(holder.ivImage);
        holder.tvDesc.setText(imageItemList.get(position).getImageDescription());
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClickedListener.onImageClicked(imageItemList.get(holder.getLayoutPosition()), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ImageItemViewHolder extends RecyclerView.ViewHolder {

        CardView cvContainer;
        ImageView ivImage;
        TextView tvDesc;

        public ImageItemViewHolder(View itemView) {
            super(itemView);
            cvContainer = (CardView) itemView.findViewById(R.id.cv_container);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_image_desc);

        }
    }

}
