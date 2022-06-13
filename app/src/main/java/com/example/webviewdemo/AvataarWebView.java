package com.example.webviewdemo;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * This class contains code to load the Avataar WebView
 */
public class AvataarWebView {
    private WebView webView;
    private AvataarJSBridgeInterface jsBridge;

    public AvataarWebView(WebView webView, AvataarJSBridgeInterface jsBridge) {
        this.webView = webView;
        this.jsBridge = jsBridge;
    }

    @SuppressLint("JavascriptInterface")
    public void open(String productId, String variantId) {
        WebSettings webSettings = webView.getSettings();
        // needed for debugging JS code inside webView.
        webView.setWebContentsDebuggingEnabled(true);

        // This is needed to make sure that webView can execute JS code properly.
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String ulr) {
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
        });
        // This method attaches the Javascript bridge Object with webView document.
        // The methods inside this object can now be accessed through window.avataarCallback inside webView context.
        webView.addJavascriptInterface(jsBridge, "avataarCallback");

        // The Avataar Experience URL to load.
        webView.loadUrl(
                "https://orion-dev.avataar.me/engine/TOUCHCHANGES1/AVTR_EXP_d41d8cd9/index.html?ar=0&tenantId=AVTR-TNT-t8mv4evu&productId="
                        + productId + "&env=local&onFloor=false");
    }
    
    public void close() {
        jsBridge.closeWebView();
    }
}