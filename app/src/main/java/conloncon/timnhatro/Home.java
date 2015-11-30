package conloncon.timnhatro;

import android.util.Log;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;



public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {


    SharedPreferences sharePreferences;
    Button btnDangKy;
    EditText editTaiKhoanDK, editMatKhauDK, editNhapLaiMatKhauDK, editTaiKhoanDN, editMatKhauDN;
    //protected boolean CHECK_SIGNIN=true;
    SQLiteDatabase database;
    Post post;
    List<BaiDang> list, list1;
    ListView lvHienThi;
    CustomListView adapter =null;
    //MySimpleArrayAdapter adapter;
    public static String chuoiTimKiem = "";

    public static String display_id, display_addrres, display_square, display_price, display_info, display_extra_info;
    static private final int GET_TEXT_REQUEST_CODE = 1;
    static private final String TAG = "PhongtroCLC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_when_signin_success);
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
        list1 = new ArrayList<BaiDang>();
        //database.doCreateDB()
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
            display_id = item.getId();
            item.setAdress(c.getString(1));
            display_addrres = item.getAdress();
            item.setSquare(c.getString(2));
            display_square = item.getSquare();
            item.setPrice(c.getString(3));
            display_price = item.getPrice();
            item.setInfor(c.getString(4));
            display_info = item.getInfor();
            item.setExtra_infor(c.getString(5));
            display_extra_info = item.getExtra_infor();
            list.add(item);
            c.moveToNext();
        }
        /*setAdapterListView(list);

        lvHienThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, View_Info.class);
                startActivity(intent);
            }
        });*/ // no bao loi o day by Duc ANh =))
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
        /*
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);//tam thoi bo di
        */
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*
        int id = item.getItemId();

        if (id == R.id.action_search) {
        }
        else if (id == R.id.filter_search) {
            Intent intent = new Intent(this, Filter_search.class);
            startActivity(intent);
        }
        */   //tam thoi bo
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.signup) {
            /*String taikhoan, matkhau;
            taikhoan = sharePreferences.getString("TaiKhoan", "");
            matkhau = sharePreferences.getString("MatKhau","");
            if(taikhoan.trim().length() == 0 || matkhau.trim().length() == 0) {*/
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
            /*}else
                Toast.makeText(getApplicationContext(),"Bạn đã có tài khoản rồi", Toast.LENGTH_LONG).show();*/
        }
        else if (id == R.id.signin) {
            Intent intent = new Intent( this, Signin.class);
            startActivityForResult(intent,GET_TEXT_REQUEST_CODE);
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
        else if (id == R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String chuoi) {
        //List<BaiDang> list1;
        //list = database.LayDanhSachBaiDang();
        list1 = new ArrayList<BaiDang>();
        database = openOrCreateDatabase(post.DATABASE, MODE_PRIVATE, null);
        //list = database.LayDanhSachBaiDang();
        String[] column = {"id","address","square","price","info","extra_infor"};;
        //String[] column = {"id","address","square","price","info","extra_infor"};;
        String sql="";
        sql = "CREATE TABLE IF NOT EXISTS "+ post.TABLE+ "(id TEXT, address TEXT, square TEXT ,price TEXT ,info TEXT ,extra_infor TEXT )";
        database.execSQL(sql);

        //String[] column = {"id","address","square","price","info","extra_infor"};;
        //String[] column = {"id","address","square","price","info","extra_infor"};;
        //String[] column = {"id","address","square","price","info","extra_infor"};
        String truyvan = "Select " + column[0] + " , " + column[1] + " , "
                + column[2] + " , "+ column[3]+ " , " + column[4] + "," + column[5]+  " From "
                + post.TABLE + " Where address LIKE '%" + chuoi.toLowerCase() + "%'";
        Cursor c = database.rawQuery(truyvan, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            //BaiDang item = new BaiDang();
            BaiDang item = new BaiDang();
            item.setId(c.getString(0));
            display_id = item.getId();
            item.setAdress(c.getString(1));
            display_addrres = item.getAdress();
            item.setSquare(c.getString(2));
            display_square = item.getSquare();
            item.setPrice(c.getString(3));
            display_price = item.getPrice();
            item.setInfor(c.getString(4));
            display_info = item.getInfor();
            item.setExtra_infor(c.getString(5));
            display_extra_info = item.getExtra_infor();
            list1.add(item);
            c.moveToNext();
        }
        setAdapterListView(list1);
        adapter.notifyDataSetChanged();
        chuoiTimKiem = chuoi;
        return true;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG, "Entered onActivityResult()");
        if(requestCode==GET_TEXT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                data.getBooleanExtra("isLogin",Signin.CHECK_SIGNIN);
            }
        }
    }*/
}
