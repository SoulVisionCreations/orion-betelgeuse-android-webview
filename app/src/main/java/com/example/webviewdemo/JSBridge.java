package com.example.webviewdemo;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class JSBridge  extends AppCompatActivity {

    private Context  context;

    public JSBridge(Context ctx) {
        this.context = ctx;
    }

    public JSBridge() {
    }

    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String addToCart(String params) {
        return "SUCCESS";
    }

    @JavascriptInterface
    public String removeFromCart(String params) {
        return "SUCCESS";
    }

    @JavascriptInterface
    public void closeWebView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
