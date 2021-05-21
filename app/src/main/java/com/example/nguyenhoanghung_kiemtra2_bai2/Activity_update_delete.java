package com.example.nguyenhoanghung_kiemtra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenhoanghung_kiemtra2_bai2.model.KhoaHoc;

import java.util.Calendar;

public class Activity_update_delete extends AppCompatActivity implements View.OnClickListener {
    private EditText ten, ngay;
    private Button btnDate, btnUpdate, btnDelete, btnCanCel;
    private Spinner spinnerCN;
    private CheckBox kichHoat;

    private SQLiteKhoaHocHelper sqLiteHelper;
    private ArrayAdapter adapterS;

    private int id;
    private int kichhoatCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        init();
        btnDate.setOnClickListener(this);
        String nganh[] = {"Tieng anh", "CNTT", "Kinh te", "Truyen Thong"};
        adapterS = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nganh);
        spinnerCN.setAdapter(adapterS);
        getDataIntent();

        btnUpdate.setOnClickListener(v -> {
            sqLiteHelper = new SQLiteKhoaHocHelper(this);
            KhoaHoc khoaHoc = getData();
            khoaHoc.setId(id);
            sqLiteHelper.updateKhoaHoc(khoaHoc);
            Toast.makeText(this, "Update Thành Công", Toast.LENGTH_SHORT).show();
            finish();
        });
        btnCanCel.setOnClickListener(v -> {
            finish();
        });
        btnDelete.setOnClickListener(v -> {

            sqLiteHelper = new SQLiteKhoaHocHelper(this);
            if (kichhoatCheck != 1) {
                sqLiteHelper.deleteKhoaHoc(id);
                finish();
                Toast.makeText(this, "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Xoa khong Thanh Cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        KhoaHoc khoaHoc = (KhoaHoc) intent.getSerializableExtra("khoaHoc");
        id = khoaHoc.getId();
        kichhoatCheck = khoaHoc.getKichhoat();
        ten.setText(khoaHoc.getTen());
        ngay.setText(khoaHoc.getNgay());
        spinnerCN.setSelection(adapterS.getPosition(khoaHoc.getChuyenNganh()));
        if (khoaHoc.getKichhoat() == 1) kichHoat.setChecked(true);

    }

    public void init() {
        ten = findViewById(R.id.ten);
        ngay = findViewById(R.id.ngay);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCanCel = findViewById(R.id.btnCancel);
        btnDate = findViewById(R.id.btnDate);

        spinnerCN = findViewById(R.id.chuyenNganh);
        kichHoat = findViewById(R.id.kichhoat);
    }

    public KhoaHoc getData() {
        KhoaHoc khoaHoc = new KhoaHoc();
        khoaHoc.setTen(ten.getText().toString());
        khoaHoc.setNgay(ngay.getText().toString());
        khoaHoc.setChuyenNganh(spinnerCN.getSelectedItem().toString());
        if (kichHoat.isChecked()) khoaHoc.setKichhoat(1);
        else khoaHoc.setKichhoat(0);
        return khoaHoc;
    }

    @Override
    public void onClick(View v) {
        if (v == btnDate) {
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    ngay.setText(dayOfMonth + "/" + month + "/" + year);
                }
            }, y, m, d);
            datePickerDialog.show();
        }
    }

}