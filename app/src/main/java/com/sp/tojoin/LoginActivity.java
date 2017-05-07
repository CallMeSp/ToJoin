package com.sp.tojoin;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.Socket;
import com.sp.tojoin.IListener.ILoginActivity;
import com.sp.tojoin.presenter.LoginPresenter;
import com.sp.tojoin.service.SocketManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener,ILoginActivity{

    private static final String TAG = "LoginActivity";

    private Socket socket=null;

    private LoginPresenter loginPresenter=new LoginPresenter(this);

    @BindView(R.id.edit_email)TextView email_text;
    @BindView(R.id.edit_password)TextView pwd_text;
    @BindView(R.id.btn_login)Button login_btn;
    @BindView(R.id.btn_register)Button register_btn;
    @BindView(R.id.login_progress)ProgressBar progressBar;
    @BindView(R.id.btn_getemail)Button getemail_btn;
    @BindView(R.id.edit_checknum)EditText checknum_text;
    @BindView(R.id.linear_register)LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        socket=SocketManager.getSocket();

        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        getemail_btn.setOnClickListener(this);

        email_text.setText("995199235@qq.com");
        pwd_text.setText("sp123465");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                if (linearLayout.getVisibility()==View.GONE){
                    linearLayout.setVisibility(View.VISIBLE);
                    login_btn.setVisibility(View.GONE);
                    register_btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    register_btn.setTextColor(getResources().getColor(R.color.white));
                }else {
                    //提交注册申请的地方
                    String address=email_text.getText().toString();
                    String pwd=pwd_text.getText().toString();
                    String check=checknum_text.getText().toString();
                    int num=Integer.valueOf(check);
                    doRegister(address,pwd,num);
                    //UI恢复
                    linearLayout.setVisibility(View.GONE);
                    register_btn.setBackgroundColor(getResources().getColor(R.color.gray));
                    register_btn.setTextColor(getResources().getColor(R.color.white));
                    login_btn.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_login:
                String address=email_text.getText().toString();
                String pwd=pwd_text.getText().toString();
                doLogin(address,pwd);
                break;
            case R.id.btn_getemail:
                String emailaddress=email_text.getText().toString();
                doGetEmail(emailaddress);
                break;
        }
    }
    private void doGetEmail(String address){
        loginPresenter.getEmail(address);
    }
    private void doRegister(String address,String pwd,int check){
        loginPresenter.register(address,pwd,check);
    }
    private void doLogin(String address,String pwd){
        loginPresenter.login(address,pwd);
    }

    @Override
    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void makeToast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void loginFail() {
        hideProgressBar();
        makeToast("密码错误 ");
    }

    @Override
    public void loginSuccess() {
        hideProgressBar();
        makeToast("登陆成功");
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}

