package edu.huflit.myapplication.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.huflit.myapplication.R;
import edu.huflit.myapplication.fragment_dau_sach.ManHinhDauSach;

public class MyAdapterDMDauSach extends BaseAdapter {
    LayoutInflater inflater;
    TextView textView;
    ImageView imageView;
    Context context;
    public MyAdapterDMDauSach(Context context)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return ManHinhDauSach.loaiSaches.size();
    }

    @Override
    public Object getItem(int i) {
        return ManHinhDauSach.loaiSaches.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ManHinhDauSach.loaiSaches.get(i).getMa_loai_sach_ls();
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.item_list_dmds, null);

        textView = (TextView) view.findViewById(R.id.tv_dmds1);
        textView = (TextView) view.findViewById(R.id.tv_dmds2);
        textView = (TextView) view.findViewById(R.id.tv_dmds3);
        textView.setText(Integer.toString(ManHinhDauSach.loaiSaches.get(i).getMa_loai_sach_ls()));
        textView = (TextView) view.findViewById(R.id.tv_dmds4);
        textView.setText(ManHinhDauSach.loaiSaches.get(i).getLoai_sach_ls());
        imageView = (ImageView) view.findViewById(R.id.dmds_image);
        imageView.setImageResource(R.drawable.icon_book_shelf);


        return view;
    }
}
