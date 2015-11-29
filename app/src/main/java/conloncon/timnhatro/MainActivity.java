package conloncon.timnhatro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharePreferences;
    Button btnDangKy;
    EditText editTaiKhoanDK, editMatKhauDK, editNhapLaiMatKhauDK, editTaiKhoanDN, editMatKhauDN;
    //protected static boolean CHECK_SIGNIN;
    SQLiteDatabase database;
    Post post;
    List<BaiDang> list;
    ListView lvHienThi;
    CustomListView adapter =null;
    //MySimpleArrayAdapter adapter;
    public static String chuoiTimKiemKiem = "";

    public static String display_id, display_addrres, display_square, display_price, display_info, display_extra_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onStop();
        onRestart();
        if (Signin.CHECK_SIGNIN) {
            setContentView(R.layout.activity_when_signin_success);
        }
        else {
            setContentView(R.layout.activity_main);
        }
        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lvHienThi = (ListView) findViewById(R.id.lvHienThi);
        list = new ArrayList<BaiDang>();
        //database.doCreateDB();
        database = openOrCreateDatabase(post.DATABASE, MODE_PRIVATE, null);
        //list = database.LayDanhSachBaiDang();
        String[] column = {"id","address","square","price","info","extra_infor"};;
        //String[] column = {"id","address","square","price","info","extra_infor"};;
        String sql="";
        sql = "CREATE TABLE IF NOT EXISTS "+ post.TABLE+ "(id TEXT, address TEXT, square TEXT ,price TEXT ,info TEXT ,extra_infor TEXT )";
        database.execSQL(sql);
        //Lay danh sach bai dang trong co so du lieu
        Cursor c = database.query(post.TABLE, column, null, null,
                null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            BaiDang item = new BaiDang();
            item.setId(c.getString(0));
            display_id = item.setId(c.getString(0));
            item.setAdress(c.getString(1));
            display_addrres = item.setId(c.getString(0));
            item.setSquare(c.getString(2));
            display_square = item.setId(c.getString(0));
            item.setPrice(c.getString(3));
            display_price = item.setId(c.getString(0));
            item.setInfor(c.getString(4));
            display_info = item.setId(c.getString(0));
            item.setExtra_infor(c.getString(5));
            display_extra_info = item.setId(c.getString(0));
            list.add(item);
            c.moveToNext();
        }
        setAdapterListView(list);

        lvHienThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, View_Info.class);
                startActivity(intent);
            }
        });
    }


    private void setAdapterListView(List<BaiDang> list) {
        adapter = new CustomListView(this, R.layout.custom_list_view, list);
        lvHienThi.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
        }
        else if (id == R.id.filter_search) {
            Intent intent = new Intent(this, Filter_search.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.signup) {
            String taikhoan, matkhau;
            taikhoan = sharePreferences.getString("TaiKhoan", "");
            matkhau = sharePreferences.getString("MatKhau","");
            if(taikhoan.trim().length() == 0 || matkhau.trim().length() == 0) {
                Intent intent = new Intent(this, Signup.class);


              /*  btnDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String matkhaudk, nhaplaimatkhaudk;
                        editMatKhauDK = (EditText)findViewById(R.id.editTextPassword);
                        editNhapLaiMatKhauDK = (EditText)findViewById(R.id.editTextPasswordAgain);
                        matkhaudk = editMatKhauDK.getText().toString();
                        nhaplaimatkhaudk = editNhapLaiMatKhauDK.getText().toString();
                        if(!matkhaudk.equals(nhaplaimatkhaudk))
                        {
                            Toast.makeText(getApplicationContext(),"Mật khẩu không khớp",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                        }
                    }
                });*/
                startActivity(intent);
            }else
                Toast.makeText(getApplicationContext(),"Bạn đã có tài khoản rồi", Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.signin) {
            Intent intent = new Intent( this, Signin.class);
            startActivity(intent);
        }
        else if (id == R.id.feedback) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=")));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=")));
            }
        }
        else if (id == R.id.help){
            Intent intent = new Intent(this, Help.class);
            startActivity(intent);
        }
        else if (id == R.id.post){
            Intent intent = new Intent(this, Post.class);
            startActivity(intent);
        }
        else if (id == R.id.post_list) {
            Intent intent = new Intent(this, Manage_Post.class);
            startActivity(intent);
        }
        else if (id == R.id.signout) {
            Signin.CHECK_SIGNIN = false;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
