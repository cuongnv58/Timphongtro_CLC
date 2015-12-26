package conloncon.timnhatro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Manage_Post extends AppCompatActivity {

    //public boolean CHECK_SIGNIN = true;
    List<BaiDang> list, list1;
    ListView lvHienThi;
    SQLiteDatabase database;
    Post post;
    CustomListView adapter =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_post);


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
            item.setAdress(c.getString(1));
            item.setSquare(c.getString(2));
            item.setPrice(c.getString(3));
            item.setInfor(c.getString(4));
            item.setExtra_infor(c.getString(5));
            list.add(0, item);
            c.moveToNext();
        }
        setAdapterListView(list);


        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setAdapterListView(List<BaiDang> list) {
        adapter = new CustomListView(this, R.layout.custom_list_view, list);
        lvHienThi.setAdapter(adapter);

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

        return super.onOptionsItemSelected(item);
    }



}
