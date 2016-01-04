package conloncon.timnhatro;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class View_Info extends AppCompatActivity {
    SharedPreferences sharePreferences;
    SQLiteDatabase database;
    public static final String TABLE = "postComment1";
    List<Comment> list;
    ListView lvCmt;
    CommentListView adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_info);

        database = openOrCreateDatabase(Post.DATABASE, MODE_PRIVATE, null);
        String[] column = {"pk","comment","username","time"};
        list = new ArrayList<Comment>();
        lvCmt = (ListView) findViewById(R.id.lvCmt);

        String sql="";
        sql = "CREATE TABLE IF NOT EXISTS "+ TABLE + "(pk TEXT, comment TEXT, username TEXT, time TEXT)";
        database.execSQL(sql);

        sql = "Select * From " + TABLE + " Where pk LIKE '%" + MainActivity.display_addrres + "%'";
        Cursor c = database.rawQuery(sql, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Comment item = new Comment();
            item.setPk(c.getString(0));
            item.setComment(c.getString(1));
            item.setUsername(c.getString(2));
            item.setTime(c.getString(3));
            list.add(item);
            c.moveToNext();
        }

        setAdapterListView(list);
        adapter.notifyDataSetChanged();

        /*
        lvCmt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/


        //hiển thị thông tin
        TextView display_id = (TextView) findViewById(R.id.display_id);
        display_id.setText(MainActivity.display_id);
        final TextView display_address = (TextView) findViewById(R.id.display_address);
        display_address.setText(MainActivity.display_addrres);
        TextView display_price = (TextView) findViewById(R.id.display_price);
        display_price.setText(MainActivity.display_price);
        TextView display_square = (TextView) findViewById(R.id.display_square);
        display_square.setText(MainActivity.display_square);
        TextView display_info = (TextView) findViewById(R.id.display_info);
        display_info.setText(MainActivity.display_info);
        TextView display_extra_info = (TextView) findViewById(R.id.display_extra_info);
        display_extra_info.setText(MainActivity.display_extra_info);
        TextView map = (TextView) findViewById(R.id.display_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geoIntent = new Intent(
                        android.content.Intent.ACTION_VIEW, Uri
                        .parse("geo:0,0?q=" + display_address));
                startActivity(geoIntent);
            }
        });

        // bình luận và hiển thị
        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        final String id_now, taikhoan, matkhau;
        id_now = sharePreferences.getString("id_now", "");
        taikhoan = sharePreferences.getString("TK"+id_now, "");
        matkhau = sharePreferences.getString("MK"+id_now, "");


        Button btncmt;
        btncmt =(Button)findViewById(R.id.btnCmt);
        btncmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText cmt = (EditText) findViewById(R.id.edtCmt);



                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                Calendar cal = Calendar.getInstance();

                //doInsert()
                ContentValues value = new ContentValues();

                String _pk = MainActivity.display_addrres + list.size();
                value.put("pk", _pk);
                String _cmt = cmt.getText().toString();
                value.put("comment", _cmt);
                String _tvUsername = id_now + ": ";
                value.put("username", _tvUsername);
                String _time = dateFormat.format(cal.getTime()).toString();
                value.put("time", _time);
                database.insert(TABLE, null, value);


                Comment item = new Comment();
                item.setPk(_pk);
                item.setUsername(_tvUsername);
                item.setComment(_cmt);
                item.setTime(_time);
                list.add(item);
                adapter.notifyDataSetChanged();

                cmt.setText("");

            }
        });

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setAdapterListView(List<Comment> list) {
        adapter = new CommentListView(this, R.layout.comment_list_view, list);
        lvCmt.setAdapter(adapter);
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