package com.example.nguyenhoanghung_kiemtra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.nguyenhoanghung_kiemtra2_bai2.model.KhoaHoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAdd;
    private TextView soluongKH;
    private SQLiteKhoaHocHelper sqLiteHelper;
    private KhoaHocAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init() {
        searchView = findViewById(R.id.searchview);
        recyclerView = findViewById(R.id.recycleView);
        btnAdd = findViewById(R.id.addbutton);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_add.class);
            startActivity(intent);
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<KhoaHoc> list=sqLiteHelper.getKhoaHocByName(newText);
                adapter.setMlist(list);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
        soluongKH=findViewById(R.id.soKichHoat);
        sqLiteHelper=new SQLiteKhoaHocHelper(this);
        List<KhoaHoc> list = sqLiteHelper.getKhoaHocByKH(1);
        soluongKH.setText("So luong kich hoat:"+list.size());
    }

    public void setData() {
        sqLiteHelper = new SQLiteKhoaHocHelper(this);

        List<KhoaHoc> list = sqLiteHelper.getAllKhoaHoc();

        adapter = new KhoaHocAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
        sqLiteHelper=new SQLiteKhoaHocHelper(this);
        List<KhoaHoc> list = sqLiteHelper.getKhoaHocByKH(1);
        soluongKH.setText("So luong kich hoat:"+list.size());
    }
}