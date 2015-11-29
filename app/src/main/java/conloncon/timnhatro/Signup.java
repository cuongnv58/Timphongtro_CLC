package conloncon.timnhatro;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Signup extends AppCompatActivity{
    SharedPreferences sharePreferences;
    EditText textUser, textPass, textPassAgain, textEmail, textPhone;
    Button confirm, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        confirm = (Button) findViewById(R.id.button_confirm);
        cancel = (Button) findViewById(R.id.cancel_button);
        textUser = (EditText) findViewById(R.id.editTextUserName);
        textPass = (EditText) findViewById(R.id.editTextPassword);
        textPassAgain = (EditText) findViewById(R.id.editTextPasswordAgain);
        textEmail = (EditText) findViewById(R.id.editTextEmail);
        textPhone = (EditText) findViewById(R.id.editTextPhone);
        /*confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
        String taikhoan, matkhau;
        taikhoan = sharePreferences.getString("TaiKhoan", "");
        matkhau = sharePreferences.getString("MatKhau","");
        //control button
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kiểm tra tên ĐN, email, sđt, pass
                if (!checkNull(textUser.getText().toString())){
                    Toast.makeText(Signup.this, "Chưa có Tên Đăng Nhập", Toast.LENGTH_SHORT).show();
                }
                if (!checkNull(textPass.getText().toString())){
                    Toast.makeText(Signup.this, "Chưa có Mật Khẩu", Toast.LENGTH_SHORT).show();
                }
                if (!checkEmail(textEmail.getText().toString())){
                    Toast.makeText(Signup.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                if (!checkPhone(textPhone.getText().toString())) {
                    Toast.makeText(Signup.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                if (!checkPass(textPass.getText().toString(), textPassAgain.getText().toString())){
                    Toast.makeText(Signup.this, "Mật khẩu chưa khớp", Toast.LENGTH_SHORT).show();
                }
                if (!checkUserName(textUser.getText().toString())){
                    Toast.makeText(Signup.this, "Tên Đăng Nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                //nạp vào CSDL
                SharedPreferences.Editor edit = sharePreferences.edit();
                edit.putString("TaiKhoan",textUser.getText().toString());
                edit.putString("MatKhau",textPass.getText().toString());
                edit.commit();
                Toast.makeText(Signup.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
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


    private boolean checkNull(String str){
        if (str.length() == 0) return false;
        return true;
    }

    private boolean checkEmail(String email){
        for (int i = 0; i < email.length(); i++){
            if (email.charAt(i) == '@') return true;
        }
        return false;
    }

    private  boolean checkPhone(String phone){
        if (!checkNull(phone)) return false;
        for (int i = 0; i < phone.length(); i++){
            if (phone.charAt(i) < '0' || phone.charAt(i) > '9') return false;
            i++;
        }
        return true;
    }

    private boolean checkPass(String pass, String passAgain){
        if (pass.equals(passAgain)) return true;
        return false;
    }

    private boolean checkUserName(String username){
        String tkcf = sharePreferences.getString("TaiKhoan", "");
        if (username.equals(tkcf)) return false;
        return true;
    }
}
