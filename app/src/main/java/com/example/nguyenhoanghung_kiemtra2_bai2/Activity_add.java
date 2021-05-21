package com.example.nguyenhoanghung_kiemtra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenhoanghung_kiemtra2_bai2.model.KhoaHoc;

import java.util.Calendar;

public class Activity_add extends AppCompatActivity implements View.OnClickListener{
    private EditText ten,ngay;
    private Button btnDate, btnAdd,btnCanCel;
    private Spinner spinnerCN;
    private CheckBox kichHoat;

    private SQLiteKhoaHocHelper sqLiteHelper;
    private ArrayAdapter adapterS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        init();
        btnAdd.setOnClickListener(v -> {
            sqLiteHelper=new SQLiteKhoaHocHelper(this);
            KhoaHoc khoaHoc=getData();
            sqLiteHelper.addKhoaHoc(khoaHoc);
            Toast.makeText(this,"Them Thanh Cong",Toast.LENGTH_SHORT).show();
            finish();
        });
        btnCanCel.setOnClickListener(v -> {
            finish();
        });
        String nganh[]={"Tieng anh","CNTT","Kinh te","Truyen Thong"};
        adapterS=new ArrayAdapter(this, android.R.layout.simple_list_item_1,nganh);
        spinnerCN.setAdapter(adapterS);

        btnDate.setOnClickListener(this);
    }

    public void init(){
        ten=findViewById(R.id.ten);
        ngay=findViewById(R.id.ngay);

        btnAdd=findViewById(R.id.btnAdd);
        btnCanCel=findViewById(R.id.btnCancel);
        btnDate=findViewById(R.id.btnDate);

        spinnerCN=findViewById(R.id.chuyenNganh);
        kichHoat=findViewById(R.id.kichhoat);
    }

    public KhoaHoc getData(){
        KhoaHoc khoaHoc=new KhoaHoc();
        khoaHoc.setTen(ten.getText().toString());
        khoaHoc.setNgay(ngay.getText().toString());
        khoaHoc.setChuyenNganh(spinnerCN.getSelectedItem().toString());
        if(kichHoat.isChecked()) khoaHoc.setKichhoat(1);
        else khoaHoc.setKichhoat(0);
        return khoaHoc;
    }
    @Override
    public void onClick(View v) {
        if(v==btnDate){
            Calendar c=Calendar.getInstance();
            int y=c.get(Calendar.YEAR);
            int m=c.get(Calendar.MONTH);
            int d=c.get(Calendar.DAY_OF_MONTH);



            DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    ngay.setText(dayOfMonth+"/"+month+"/"+year);
                }
            },y,m,d);
            datePickerDialog.show();
        }
    }

}