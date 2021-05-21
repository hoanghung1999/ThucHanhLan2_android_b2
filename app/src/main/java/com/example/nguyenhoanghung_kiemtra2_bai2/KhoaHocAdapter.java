package com.example.nguyenhoanghung_kiemtra2_bai2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhoanghung_kiemtra2_bai2.model.KhoaHoc;

import java.util.List;

public class KhoaHocAdapter extends RecyclerView.Adapter<KhoaHocAdapter.KhoaHocViewHolder> {
    private List<KhoaHoc> mlist;
    private View v;


    public KhoaHocAdapter(List<KhoaHoc> mlist) {
        this.mlist = mlist;
    }

    public List<KhoaHoc> getMlist() {
        return mlist;
    }

    public void setMlist(List<KhoaHoc> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public KhoaHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.khoahoc_card, parent, false);
        return new KhoaHocViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoaHocViewHolder holder, int position) {
            KhoaHoc khoaHoc=mlist.get(position);
            holder.ten.setText(khoaHoc.getId()+"- "+khoaHoc.getTen());
            holder.chuyennganh.setText("chuyên ngành: "+ khoaHoc.getChuyenNganh());
            holder.ngay.setText("ngày"+ khoaHoc.getNgay());
            if (khoaHoc.getKichhoat()==1){
                holder.kichhoat.setText("Đã kích hoạt");
            }
        if (khoaHoc.getKichhoat()==0){
            holder.kichhoat.setText("Chưa kích hoạt");
        }
        holder.itemView.setOnClickListener(v1 -> {
            KhoaHoc khoaHoc1=mlist.get(position);
            Intent intent=new Intent(v.getContext(),Activity_update_delete.class);
            intent.putExtra("khoaHoc",khoaHoc1);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(mlist!=null) return mlist.size();
        return 0;
    }

    class KhoaHocViewHolder extends RecyclerView.ViewHolder {
        private TextView ten, ngay, chuyennganh, kichhoat;

        public KhoaHocViewHolder(@NonNull View v) {
            super(v);
            ten=v.findViewById(R.id.tenC);
            ngay=v.findViewById(R.id.ngayC);
            chuyennganh=v.findViewById(R.id.chuyenNganhC);
            kichhoat=v.findViewById(R.id.kichhoatC);
        }
    }
}
