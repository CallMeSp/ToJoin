package com.sp.tojoin.service;

import android.util.Log;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.sp.tojoin.base.LogUtil;

import java.net.URISyntaxException;

/**
 * Created by Administrator on 2017/4/14.
 */

public class SocketManager{
    private static Socket socket=null;
    static {
        try {
                Log.e("socketmanager","static");
                socket=IO.socket("http://192.168.137.1:4000");
                socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public static Socket getSocket(){
        return socket;
    }
    public static void getCheck(String address){
        LogUtil.log("getcheck","socket.connected():"+socket.connected());
        socket.emit("getcheck",address);
    }
}
