package com.example.webviewdemo;
import android.annotation.SuppressLint;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
    public void open(String productId, String tenantId, String arV) {
        WebSettings webSettings = webView.getSettings();
        // needed for debugging JS code inside webView.
        webView.setWebContentsDebuggingEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);

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
//        String urlString = "https://orion.avataar.me/engine/AVTR_TNT_JMJRmNZy/AVTR_EXP_4e666717/index.html?productId=AVTR-WRK-mX8LZFfL&env=preprod&addToCartEnabled=false";
//        String urlString = "https://0846-182-66-75-46.in.ngrok.io/?ar=0&mode=renderer&tenantId="+tenantId+"&productId="+productId+"&roomId=room_123&roomBackground=Background_Dominar";
        String urlString = "https://orion.avataar.me/engine/AVTR_TNT_JMJRmNZy/AVTR_EXP_4e666717/index.html?productId=AVTR-WRK-mX8LZFfL&env=preprod&addToCartEnabled=false";
        webView.loadUrl(urlString );
    }
    
    public void close() {
        jsBridge.closeWebView();
    }
}