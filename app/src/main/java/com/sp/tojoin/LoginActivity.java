package com.sp.tojoin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.Socket;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.service.SocketManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{

    private static final String TAG = "LoginActivity";

    private Socket socket=null;

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
                doGetEmail("");
                break;
        }
    }
    private void doGetEmail(String address){
        socket.emit("getcheck","995199235@qq.com");
    }
    private void doLogin(String address,String pwd){

    }
}

