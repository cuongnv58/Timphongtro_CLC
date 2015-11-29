package conloncon.timnhatro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class View_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_info);

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
