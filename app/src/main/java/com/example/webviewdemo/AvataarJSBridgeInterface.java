package com.example.webviewdemo;
/**
 * This interface contains the functions to be defined by anyone integrating Avataar's experience
 */
public interface AvataarJSBridgeInterface {

    // Add Product to Card
    public String addToCart(String productId, String variantId);

    // Remove Product from Card
    public String removeFromCart(String productId, String variantId);

    // close webView and redirect to Cart page
    public void goToCart();

    //Close View
    public void closeWebView();
}
