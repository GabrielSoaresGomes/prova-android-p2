package com.example.prova_p2_android_tassin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        }

        Product product = productList.get(position);

        TextView nameView = convertView.findViewById(R.id.product_name);
        TextView priceView = convertView.findViewById(R.id.product_price);
        ImageView imageView = convertView.findViewById(R.id.product_image);
        TextView ratingView = convertView.findViewById(R.id.product_rating);

        nameView.setText(product.getName());
        priceView.setText(product.getPrice());
        Log.d("Teste Teste", product.getImageUrl());
        URL newurl = new URL(product.getImageUrl());
        Bitmap productImageBitMap = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
        imageView.setImageBitmap(productImageBitMap);
        ratingView.setText(product.getRating());

        convertView.setOnClickListener(v -> {
            try {
            Intent intent = new Intent(context, DetailScreen.class);
            intent.putExtra("name", product.getName());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("imageUrl", product.getImageUrl());
            intent.putExtra("rating", product.getRating());
            intent.putExtra("description", product.getDescription());
            context.startActivity(intent);
            } catch (Exception error) {
                Log.e("ProductAdapter", "Erro", error);
            }
        });
        } catch (Exception error) {
            Log.e("ProductAdapter", " Erro Geral", error);
        }
        return convertView;
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            Log.e("ProductAdapter", "Errinhos", e);
            return null;
        }
    }
}
