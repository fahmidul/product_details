package com.photography.graphics.productdetails.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.photography.graphics.productdetails.R;

import com.photography.graphics.productdetails.databinding.ActivityProductDetailsBinding;
import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity implements ProductImageAdapter.ItemClickListener {

    Menu menu = null;
    boolean isShow = false;
    int scrollRange = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        setSupportActionBar(binding.toolbar);
        binding.tvRegularPrice.setPaintFlags(binding.tvRegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerViewProduct = (RecyclerView) findViewById(R.id.recyclerViewProduct);
        recyclerViewProduct.setLayoutManager(layoutManager);

        ArrayList<Integer> productImageLIst = new ArrayList<>();
        productImageLIst.add(R.mipmap.img1);
        productImageLIst.add(R.mipmap.img2);
        productImageLIst.add(R.mipmap.img3);
        productImageLIst.add(R.mipmap.img4);
        productImageLIst.add(R.mipmap.img5);
        productImageLIst.add(R.mipmap.img6);
        productImageLIst.add(R.mipmap.img7);
        productImageLIst.add(R.mipmap.img8);

        ProductImageAdapter productImageAdapter = new ProductImageAdapter(this, productImageLIst);
        productImageAdapter.setClickListener(this);
        recyclerViewProduct.setAdapter(productImageAdapter);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProductWithModel);
        recyclerView.addItemDecoration(new MarginDecoration(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setHasFixedSize(true);

        View header = LayoutInflater.from(this).inflate(R.layout.item_product_with_model_header, recyclerView, false);
        ((ImageView) header.findViewById(R.id.imageHeader)).setImageResource(R.mipmap.mimg1);

        ArrayList<Integer> images = new ArrayList();
        images.add(R.mipmap.mimg2);
        images.add(R.mipmap.mimg3);
        final ProductViewWithModelAdapter adapter = new ProductViewWithModelAdapter(header, images);

        final GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isHeader(position) ? manager.getSpanCount() : 1;
            }
        });

        recyclerView.setAdapter(adapter);

        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption();
                    binding.toolbar.setNavigationIcon(R.drawable.back_icon);
                } else if (isShow) {
                    isShow = false;
                    hideOption();
                    binding.toolbar.setNavigationIcon(null);
                }
            }
        });


    }

    @Override
    public void onItemClick(View view, int position) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu, menu);
        this.menu = menu;
        hideOption();
        return super.onCreateOptionsMenu(menu);
    }

    void hideOption() {
        MenuItem item = menu.findItem(R.id.action_home);
        item.setVisible(false);
        MenuItem item2 = menu.findItem(R.id.action_search);
        item2.setVisible(false);
    }

    void showOption() {
        MenuItem item = menu.findItem(R.id.action_home);
        item.setVisible(true);
        MenuItem item2 = menu.findItem(R.id.action_search);
        item2.setVisible(true);
    }



}