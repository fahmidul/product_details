package com.photography.graphics.productdetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.photography.graphics.productdetails.R;

import java.util.ArrayList;

public class BottomSheetFragment extends BottomSheetDialogFragment implements ProductImageAdapter.ItemClickListener, View.OnClickListener {

    ArrayList<Integer> productImageLIst;
    ImageView imgProductExpand, imgArrowLeft, imgArrowRight;
    int selectedImagePosition = 0;

    public BottomSheetFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);

        root.findViewById(R.id.imgCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        imgProductExpand = root.findViewById(R.id.imgProductExpand);
        imgArrowLeft = root.findViewById(R.id.imgArrowLeft);
        imgArrowLeft.setOnClickListener(this);
        imgArrowRight = root.findViewById(R.id.imgArrowRight);
        imgArrowRight.setOnClickListener(this);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerViewProduct = (RecyclerView) root.findViewById(R.id.recyclerViewProduct);
        recyclerViewProduct.setLayoutManager(layoutManager);

        productImageLIst = new ArrayList<>();
        productImageLIst.add(R.mipmap.mimg1);
        productImageLIst.add(R.mipmap.mimg2);
        productImageLIst.add(R.mipmap.mimg3);
        productImageLIst.add(R.mipmap.img8);
        productImageLIst.add(R.mipmap.img7);
        productImageLIst.add(R.mipmap.img6);
        productImageLIst.add(R.mipmap.img5);
        productImageLIst.add(R.mipmap.img4);

        ProductImageAdapter productImageAdapter = new ProductImageAdapter(getContext(), productImageLIst);
        productImageAdapter.setClickListener(this);
        recyclerViewProduct.setAdapter(productImageAdapter);

        if (productImageLIst.size() > 0) {
           loadImage();
        }

        return root;

    }


    @Override
    public void onItemClick(View view, int position) {
        selectedImagePosition = position;
        loadImage();
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgArrowLeft) {
            if (selectedImagePosition > 0) {
                selectedImagePosition--;
            }
        } else if (v.getId() == R.id.imgArrowRight) {
            if (selectedImagePosition < productImageLIst.size() - 1) {
                selectedImagePosition++;
            }
        }
        loadImage();
    }

    public void loadImage() {
        imgProductExpand.setImageResource(productImageLIst.get(selectedImagePosition));

        if (selectedImagePosition > 0 && selectedImagePosition < productImageLIst.size() - 1) {
            imgArrowLeft.setVisibility(View.VISIBLE);
            imgArrowRight.setVisibility(View.VISIBLE);
        }
        if (selectedImagePosition == 0) {
            imgArrowLeft.setVisibility(View.GONE);
        }
        if (selectedImagePosition == productImageLIst.size() - 1) {
            imgArrowRight.setVisibility(View.GONE);
        }
    }

    @Override
    public int getTheme() {
         return R.style.BottomSheetDialogTheme;
    }
}