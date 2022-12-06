package com.example.webviewdemo;

import android.content.Context;

/**
 * This interface contains the functions to be defined by anyone integrating Avataar's experience
 */
public interface AvataarJSBridgeInterface {

    // Add Product to Card
    public String addToCart(String productId, String variantId);

    // Remove Product from Card
    public String removeFromCart(String productId, String variantId);

   // Goto Cart
    public void goToCart();

    //Close View
    public void closeWebView();

    // request camera permission
    public void requestCameraPermission();

//    backbutton
    public void backButton();
}
