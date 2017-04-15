package com.sp.tojoin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2017/4/15.
 */

public class splashActivity extends Activity {
    private SharedPreferences sharedPreferences;
    private boolean NEEDLOGIN=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if (!sharedPreferences.contains("needlogin")){
            editor.putBoolean("needlogin",true);
            editor.commit();
        }
        NEEDLOGIN=sharedPreferences.getBoolean("needlogin",true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (NEEDLOGIN){
            Intent intent=new Intent(splashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
