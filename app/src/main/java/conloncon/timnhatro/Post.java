package conloncon.timnhatro;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class Post extends AppCompatActivity {

    EditText address, square, price, info, extra_info;
    Button post, cancel;
    String _address,_square,_price,_info,_extra_info,_id;
    public static final String TABLE = "postTable1";
    public static final String DATABASE = "timphongtro.db";
    SharedPreferences sharePreferences;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        sharePreferences =getSharedPreferences("config",Context.MODE_PRIVATE);
        connectView();
    }

    public void doCreateDB(){

        database = openOrCreateDatabase(DATABASE,MODE_PRIVATE,null);
    }
    public void doCreateTable(){
        String sql="";
        sql = "CREATE TABLE IF NOT EXISTS "+ TABLE+ "(id TEXT, address TEXT, square TEXT ,price TEXT ,info TEXT ,extra_infor TEXT )";
        database.execSQL(sql);
        /*
        if(isTableExists(database,"pos")== true){
            Toast.makeText(Post.this,"done",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Post.this,"shit",Toast.LENGTH_LONG).show();
        }
        */


    }
    public boolean isTableExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }


    public void taoCSDL(){
        doCreateDB();
        doCreateTable();
    }
    public void doInsert(){
        // address TEXT, square TEXT ,price TEXT ,info TEXT ,extra_infor TEXT
        ContentValues value = new ContentValues();
        value.put("id",_id);
        value.put("address",_address);
        value.put("square",_square);
        value.put("price",_price);
        value.put("info",_info);
        value.put("extra_infor",_extra_info);

        String msg ="";

        if(database.insert(TABLE, null, value)==-1){
                msg="Failed";
            }
            else{
                msg= _id + "-"+ _address+"-"+_square+"-"+_price+"-"+_info+"-"+_extra_info;
        }

        Toast.makeText(Post.this,msg,Toast.LENGTH_LONG).show();
    }
    public List<BaiDang> LayDanhSachTheoTuKhoa(String tenbaihat) {
        List<BaiDang> list = new ArrayList<BaiDang>();

        String[] column = {"id","address","square","price","info","extra_infor"};
        //id TEXT, address TEXT, square TEXT ,price TEXT ,info TEXT ,extra_infor TEXT
        // Cursor c = db.query(DatabaseHelper.TABLE_SONG, column, null, null,
        // null, null, null);

        // select _id , song_name , song_name2 , song_lyric From song where song_name2 like '% tenbaihat %'
        String truyvan = "Select " + column[0] + " , " + column[1] + " , "
                + column[2] + " , "+ column[3]+ " , " + column[4] + "," + column[5]+  " From "
                + TABLE + " Where address LIKE '%" + tenbaihat.toLowerCase() + "%'";
        Cursor c = database.rawQuery(truyvan, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            BaiDang item = new BaiDang();
            item.setId(c.getString(0));
            item.setAdress(c.getString(1));
            item.setSquare(c.getString(2));
            item.setPrice(c.getString(3));
            item.setInfor(c.getString(4));
            item.setExtra_infor(c.getString(5));
            list.add(item);
            c.moveToNext();
        }
        return list;
    }
    public List<BaiDang> LayDanhSachBaiDang() {
        List<BaiDang> list = new ArrayList<BaiDang>();
        String[] column = {"id","address","square","price","info","extra_infor"};;
        Cursor c = database.query(TABLE, column, null, null,
                null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            BaiDang item = new BaiDang();
            item.setId(c.getString(0));
            item.setAdress(c.getString(1));
            item.setSquare(c.getString(2));
            item.setPrice(c.getString(3));
            item.setInfor(c.getString(4));
            item.setExtra_infor(c.getString(5));
            list.add(item);
            c.moveToNext();
        }
        return list;
    }
    public void connectView(){
        post = (Button) findViewById(R.id.post_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        address = (EditText) findViewById(R.id.editTextAddress);
        square = (EditText) findViewById(R.id.editTextSquare);
        price = (EditText) findViewById(R.id.editTextPrice);
        info = (EditText) findViewById(R.id.editTextInfo);
        extra_info = (EditText) findViewById(R.id.editTextExtraInfo);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _address = "Địa chỉ: "+address.getText().toString();
                _square = "Diện tích: "+square.getText().toString()+" m2";
                _price = "Giá cho thuê: "+price.getText().toString()+"/tháng";
                _info = "Liên hệ: "+info.getText().toString();
                _extra_info = extra_info.getText().toString();
                _id = "Đăng bởi "+sharePreferences.getString("TaiKhoan", "");
                taoCSDL();
                doInsert();
                finish();

                /*
                if(database!= null){
                    Toast toast = Toast.makeText(Post.this, "OK da them du lieu thanh cong", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(Post.this, "Failed", Toast.LENGTH_LONG);
                    toast.show();
                }
                */

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        else if (id == R.id.filter_search){

        }

        return super.onOptionsItemSelected(item);
    }

}