package edu.huflit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import edu.huflit.myapplication.ManHinhUser.ManHinhUser;
import edu.huflit.myapplication.OOP.User;
import edu.huflit.myapplication.fragment_admin.ManHinhChinhAdmin;
import edu.huflit.myapplication.fragment_dau_sach.AddDauSach;
import edu.huflit.myapplication.fragment_dau_sach.ManHinhDauSach;
import edu.huflit.myapplication.fragment_dau_sach.UpdateDauSach;
import edu.huflit.myapplication.fragments.DangKy2;
import edu.huflit.myapplication.fragments.Login2;

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
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        anhXa();
        SharedPreferences get_user = getSharedPreferences("login", MODE_PRIVATE);
        String username = get_user.getString("username", null);
        boolean is_login = get_user.getBoolean("is_login", false);
        if (is_login == false)
        {
            TextView show_username2 = navigationView.getHeaderView(0).findViewById(R.id.username1);
            TextView show_email2 = navigationView.getHeaderView(0).findViewById(R.id.email1);
            show_email2.setText("Email");
            show_username2.setText("Username");
            if (currentFragment != FR_MAN_HINH_CHINH_USER)
            {
                replaceFragment(new ManHinhUser());
                currentFragment = FR_MAN_HINH_CHINH_USER;
            }
        }
        else
        {
            //LẤY USER NAME EMAIL SHOW LÊN HEADER
            TextView show_username = navigationView.getHeaderView(0).findViewById(R.id.username1);
            TextView show_email = navigationView.getHeaderView(0).findViewById(R.id.email1);
            Cursor cursor = database.getUserByUsername(username);
            int username_index = cursor.getColumnIndex(DBHelper.USERNAME_USER);
            int email_index = cursor.getColumnIndex(DBHelper.EMAIL_USER);
            cursor.moveToFirst();

            show_username.setText(cursor.getString(username_index));
            show_email.setText(cursor.getString(email_index));
            cursor.close();
            User user = database.checkRole(username);
            if (user.getRole_user().equals("admin"))
            {
                if (currentFragment != FR_MAN_HINH_CHINH_ADMIN)
                {
                    replaceFragment(new ManHinhChinhAdmin());
                    currentFragment = FR_MAN_HINH_CHINH_ADMIN;
                }
            }
            else
            {
                if (currentFragment != FR_MAN_HINH_CHINH_USER)
                {
                    replaceFragment(new ManHinhUser());
                    currentFragment = FR_MAN_HINH_CHINH_USER;
                }
            }

        }
        //ADD Toolbar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,   R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_admin_1)
                {
                    SharedPreferences get_user = getSharedPreferences("login", MODE_PRIVATE);
                    String username = get_user.getString("username", null);
                    boolean iss_login = get_user.getBoolean("is_login", false);
                    if (iss_login == false)
                    {

                    }
                    else
                    {
                        User user = database.checkRole(username);
                        if (user.getRole_user().equals("admin"))
                        {
                            if (currentFragment != FR_MAN_HINH_CHINH_ADMIN)
                            {
                                currentFragment = FR_MAN_HINH_CHINH_ADMIN;
                            }
                            replaceFragment(new ManHinhChinhAdmin());
                        }
                        else
                        {
                            if (currentFragment != FR_MAN_HINH_CHINH_USER)
                            {
                                currentFragment = FR_MAN_HINH_CHINH_USER;
                            }
                            replaceFragment(new ManHinhUser());
                        }
                    }
                }
                return true;
            }
        });
        //Kiểm tra đăng nhập, nếu chưa thì hiện đăng nhập, ngược lại hiện đăng xuất trên menu
        //SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        Menu navigationMenu = navigationView.getMenu();
        MenuItem menuItem = navigationMenu.findItem(R.id.nav_tittle6);
        boolean check_login = sharedPreferences.getBoolean("is_login", false);
        if (check_login == false)
        {
            menuItem.setTitle("Đăng nhập");
        }
        else
        {
            menuItem.setTitle("Đăng xuất");
        }
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
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_tittle1)
        {

        }
        else if (id == R.id.nav_tittle2)
        {

        }
        else if (id == R.id.nav_tittle3)
        {
            SharedPreferences get_user = getSharedPreferences("login", Context.MODE_PRIVATE);
            String username = get_user.getString("username", null);
            if (username == null)
            {
                Toast.makeText(ManHinhChinh.this, "Bạn chưa đăng nhập", Toast.LENGTH_LONG).show();
            }

        }

        else if (id == R.id.nav_tittle6)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean check_login = sharedPreferences.getBoolean("is_login", false);
            if (check_login == false)
            {
                if (currentFragment != FR_LOGIN)
                {
                    replaceFragment(new Login2());
                    currentFragment = FR_LOGIN;
                }
                else
                {
                    replaceFragment(new Login2());
                }
            }
            else if (check_login)
            {
                //Khi đăng xuất thì header ko còn hiện username và email
                TextView show_username2 = navigationView.getHeaderView(0).findViewById(R.id.username1);
                TextView show_email2 = navigationView.getHeaderView(0).findViewById(R.id.email1);
                show_email2.setText("Email");
                show_username2.setText("Username");

                SharedPreferences sharedPreferences1 = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editorr = sharedPreferences1.edit();
                editorr.putBoolean("is_login", false);
                editorr.putString("username", null);
                editorr.apply();
                Toast.makeText(ManHinhChinh.this, "Đã đăng xuất", Toast.LENGTH_LONG).show();

                //Kiểm tra đăng nhập, nếu chưa thì hiện đăng nhập, ngược lại hiện đăng xuất trên menu
                Menu navigationMenu = navigationView.getMenu();
                MenuItem menuItem = navigationMenu.findItem(R.id.nav_tittle6);
                menuItem.setTitle("Đăng nhập");
                replaceFragment(new ManHinhUser());
                currentFragment = FR_HOME;
            }
        }

        //Đóng drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
    public void gotoLogin()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Login2 login2 = new Login2();

        fragmentTransaction.replace(R.id.hcontent_frame, login2);
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hcontent_frame, fragment);
        transaction.commit();
    }
    public void gotoDangKy()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DangKy2 dangKy2 = new DangKy2();

        fragmentTransaction.replace(R.id.hcontent_frame, dangKy2);
        fragmentTransaction.commit();
    }
}