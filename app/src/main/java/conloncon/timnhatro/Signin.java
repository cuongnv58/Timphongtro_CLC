package conloncon.timnhatro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Signin extends AppCompatActivity {

    SharedPreferences sharePreferences;
    EditText username, pass;
    Button login, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String taikhoan, matkhau;
        taikhoan = sharePreferences.getString("TaiKhoan", "");
        matkhau = sharePreferences.getString("MatKhau", "");

        username = (EditText) findViewById(R.id.editTextUserName);
        pass = (EditText) findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.button_login);
        cancel = (Button) findViewById(R.id.cancel_button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkcf,tkcf,mkdn, tkdn;
                mkcf = sharePreferences.getString("MatKhau","");
                tkcf = sharePreferences.getString("TaiKhoan", "");
                tkdn = username.getText().toString();
                mkdn = pass.getText().toString();
                if(tkdn.equals(tkcf) && mkdn.equals(mkcf)){
                    Toast.makeText(getApplication(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplication(),"Đăng nhập không thành công",Toast.LENGTH_LONG).show();
                }

            }
        });

        //cancel button
        cancel.setOnClickListener(new OnClickListener() {
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
