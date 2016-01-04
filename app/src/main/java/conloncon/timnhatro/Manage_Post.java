package conloncon.timnhatro;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Manage_Post extends AppCompatActivity {

    //public boolean CHECK_SIGNIN = true;
    List<BaiDang> list, list1;
    Button btnDelete;
    ListView lvHienThi;
    SQLiteDatabase database;
    Post post;
    ManagePostListView adapter =null;
    SharedPreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_post);
        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        //btnDelete = (Button) findViewById(R.id.btnDelete);


        lvHienThi = (ListView) findViewById(R.id.lvHienThi);
        list = new ArrayList<BaiDang>();
        list1 = new ArrayList<BaiDang>();
        //database.doCreateDB()
        database = openOrCreateDatabase(post.DATABASE, MODE_PRIVATE, null);
        //list = database.LayDanhSachBaiDang();
        String[] column = {"id","address","square","price","info","extra_infor"};
        //String[] column = {"id","address","square","price","info","extra_infor"};;
        String sql="";
        sql = "CREATE TABLE IF NOT EXISTS "+ Post.TABLE +
                "(id TEXT, address TEXT, square TEXT ,price TEXT ,info TEXT ,extra_infor TEXT)";
        database.execSQL(sql);

        String _id = "Đăng bởi " + sharePreferences.getString("id_now", "");
        String truyvan = "Select " + column[0] + " , " + column[1] + " , "
                + column[2] + " , "+ column[3]+ " , " + column[4] + "," + column[5] + " From "
                + post.TABLE + " Where id LIKE '" + _id + "'";

        //Lay danh sach bai dang trong co so du lieu
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
            list.add(0, item);
            c.moveToNext();
        }
        setAdapterListView(list);
        adapter.notifyDataSetChanged();

        lvHienThi.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                /*AlertDialog.Builder b = new AlertDialog.Builder(Manage_Post.this);
                b.setTitle("Question");
                b.setMessage("Bạn có chắc chắn muốn xóa không !?");
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getOK = 1;
                    }
                });

                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getOK = 0;
                    }
                });
                b.create().show();*/

                BaiDang item = list.get(position);
                list.remove(position);

                database.delete(Post.TABLE, "extra_infor=?", new String[] {item.getExtra_infor()});
                adapter.notifyDataSetChanged();

                /*BaiDang item = list.get(position);
                MainActivity.display_id = item.getId();
                MainActivity.display_addrres = item.getAdress();
                MainActivity.display_square = item.getSquare();
                MainActivity.display_price = item.getPrice();
                MainActivity.display_info = item.getInfor();
                MainActivity.display_extra_info = item.getExtra_infor();
                Intent intent = new Intent(Manage_Post.this, View_Info.class);
                startActivity(intent);*/
            }
        });


        /*btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });*/


        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setAdapterListView(List<BaiDang> list) {
        adapter = new ManagePostListView(this, R.layout.manage_post_list_view, list);
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
