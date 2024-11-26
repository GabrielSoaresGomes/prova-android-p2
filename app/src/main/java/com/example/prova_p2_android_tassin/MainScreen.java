package com.example.prova_p2_android_tassin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends Activity {

    private ListView productsListView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        productsListView = findViewById(R.id.products_list_view);
        List<Product> productsList = loadProductsFromJson();

        adapter = new ProductAdapter(this, productsList);
        productsListView.setAdapter(adapter);
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
        } catch (Exception exception) {
            Log.e("MainScreen", "Erro ao pegar produtos do JSON", exception);
        }

        return productList;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button button = findViewById(R.id.btn_to_detail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainScreen.this, DetailScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                } catch (Exception error) {
                    Log.e("MainScreen", "Erro ao carregar tela principal no start", error);
                }
            }
        });
    }
}
