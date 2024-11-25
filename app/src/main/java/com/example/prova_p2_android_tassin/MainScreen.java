package com.example.prova_p2_android_tassin;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainScreen extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    private List<Product> loadProductsFromJson() {
        List<Product> productList= new ArrayList<>();

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.products);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            String jsonDataString = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonDataString);
            for (int count = 0; count < jsonArray.length(); count++) {
                JSONObject jsonObject = jsonArray.getJSONObject(count);

                String name = jsonObject.getString("name");
                String price = jsonObject.getString("price");
                String imageUrl = jsonObject.getString("imageUrl");
                String rating = jsonObject.getString("rating");
                String description = jsonObject.getString("description");

                productList.add(new Product(name, price, imageUrl, rating, description));
            }
        }

    }
}
