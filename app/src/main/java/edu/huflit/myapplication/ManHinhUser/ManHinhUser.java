package edu.huflit.myapplication.ManHinhUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.huflit.myapplication.DBHelper;
import edu.huflit.myapplication.MyAdapter.MyAdapterDMDauSach;
import edu.huflit.myapplication.MyDatabase;
import edu.huflit.myapplication.OOP.Sach;
import edu.huflit.myapplication.R;

public class ManHinhUser extends Fragment {
    View view;
    MyDatabase database;
    public static ListView listView;
    public static ArrayList<Sach> saches;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.man_hinh_user,container,false);


        return view;
    }

}
