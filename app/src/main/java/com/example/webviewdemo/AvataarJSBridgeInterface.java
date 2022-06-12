package com.example.webviewdemo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

/**
 * This interface contains the functions to be defined by anyone integrating Avataar's experience
 */
public interface AvataarJSBridgeInterface {

    // Add Product to Card
    public String addToCart(String productId, String variantId);

    // Remove Product from Card
    public String removeFromCart(String productId, String variantId);

    //Close View
    public void closeWebView();
}
