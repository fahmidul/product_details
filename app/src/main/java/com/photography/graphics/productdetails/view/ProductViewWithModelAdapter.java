package com.photography.graphics.productdetails.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.photography.graphics.productdetails.R;

import java.util.ArrayList;
import java.util.List;

public class ProductViewWithModelAdapter extends RecyclerView.Adapter<ProductViewWithModelAdapter.ImageViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    private final View header;
    private final ArrayList<Integer> images;

    public ProductViewWithModelAdapter(View header, ArrayList<Integer> images) {
        if (header == null) {
            throw new IllegalArgumentException("header may not be null");
        }
        this.header = header;
        this.images = images;

    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return new ImageViewHolder(header);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_image_with_model, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        if (isHeader(position)) {
            return;
        }
//        final String label = labels.get(position - 1);  // Subtract 1 for header
        holder.imgProductWithModel.setImageResource(images.get(position-1));

    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return images.size() + 1;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProductWithModel;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imgProductWithModel = (ImageView) itemView.findViewById(R.id.imgProductWithModel);
        }
    }
}