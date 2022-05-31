package com.example.webviewdemo;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JSBridge {

    private Context  context;

    public JSBridge(Context ctx) {
        this.context = ctx;
    }

    @JavascriptInterface
    public void ShowToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
