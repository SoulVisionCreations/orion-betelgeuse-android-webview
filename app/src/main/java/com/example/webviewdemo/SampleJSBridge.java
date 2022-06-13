package com.example.webviewdemo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

/**
 * This is a sample class showcasing how to use the AvataarJSBridgeInterface
 */
public class SampleJSBridge  extends AppCompatActivity implements AvataarJSBridgeInterface {

    private Context  context;

    public SampleJSBridge(Context ctx) {
        this.context = ctx;
    }

    public SampleJSBridge() {
    }

    // Add Product to Card
    @JavascriptInterface
    @Override
    public String addToCart(String productId, String variantId) {
        Log.d("LOGCAT", productId + "," + variantId);
        Toast.makeText(context, "addToCart" + productId +","+ variantId, Toast.LENGTH_SHORT).show();
        return "SUCCESS";
    }

    // Remove Product from Card
    @JavascriptInterface
    @Override
    public String removeFromCart(String productId, String variantId) {
        Log.d("LOGCAT", productId + "," + variantId);
        Toast.makeText(context, "removeFromCart", Toast.LENGTH_SHORT).show();
        return "SUCCESS";
    }


    // Example. close WebView Activity redirect to another activity on click x button on top
    @JavascriptInterface
    @Override
    public void closeWebView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Example: close WebView and redirect to main activity. It can be modified to redirect user to Cart Page.
    @JavascriptInterface
    @Override
    public void goToCart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
