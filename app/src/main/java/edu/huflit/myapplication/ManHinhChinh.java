package edu.huflit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import edu.huflit.myapplication.fragment_admin.ManHinhChinhAdmin;
import edu.huflit.myapplication.fragment_dau_sach.AddDauSach;
import edu.huflit.myapplication.fragment_dau_sach.ManHinhDauSach;
import edu.huflit.myapplication.fragment_dau_sach.UpdateDauSach;

public class ManHinhChinh extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FR_HOME = 0;
    private static final int FR_MAN_HINH_CHINH_ADMIN = 1;
    private static final int FR_MAN_HINH_CHINH_USER = 2;
    private static final int FR_DOI_MK = 3;
    private static final int FR_LOGIN = 2;

    private int currentFragment = FR_HOME;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    public static NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    MyDatabase database;
    EditText edt_tim_kiem;
    ImageView img_tim_kiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        setTitle("");
        database = new MyDatabase(getApplicationContext());
        replaceFragment(new ManHinhChinhAdmin());

        anhXa();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,   R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
    public void anhXa()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.hdrawer_layout);
        toolbar = (Toolbar) findViewById(R.id.htoolbar);
        navigationView = (NavigationView) findViewById(R.id.hnavigation_view);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        img_tim_kiem = (ImageView) findViewById(R.id.img_tim_kiem);
        edt_tim_kiem = (EditText) findViewById(R.id.edt_tim_kiem);
    }

    //Chuyển FRAGMENT
    public void gotoManHinhDauSach()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ManHinhDauSach manHinhDauSach = new ManHinhDauSach();

        fragmentTransaction.replace(R.id.hcontent_frame, manHinhDauSach);
        fragmentTransaction.commit();
    }
    public void gotoUpdateDauSach()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        UpdateDauSach updateDauSach = new UpdateDauSach();

        fragmentTransaction.replace(R.id.hcontent_frame, updateDauSach);
        fragmentTransaction.commit();
    }
    public void gotoManHinhAddDauSach()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        AddDauSach addDauSach = new AddDauSach();

        fragmentTransaction.replace(R.id.hcontent_frame, addDauSach);
        fragmentTransaction.commit();
    }
    public void gotoManHinhChinhAdmin()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ManHinhChinhAdmin manHinhChinhAdmin = new ManHinhChinhAdmin();

        fragmentTransaction.replace(R.id.hcontent_frame, manHinhChinhAdmin);
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hcontent_frame, fragment);
        transaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}