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

    // Add Product to Card
    @JavascriptInterface
    public String addToCart(String params) {
        return "SUCCESS";
    }

    // Remove Product from Card
    @JavascriptInterface
    public String removeFromCart(String params) {
        return "SUCCESS";
    }

    // close WebView Activity
    @JavascriptInterface
    public void closeWebView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
