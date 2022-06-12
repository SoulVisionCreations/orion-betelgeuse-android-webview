package com.example.webviewdemo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button buttonOne = (Button) findViewById(R.id.close_button);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new JSBridge().closeWebView();
            }
        });
        openWebView();
    }

    public void openWebView() {
        webView = findViewById(R.id.webView);
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
        webView.addJavascriptInterface(new JSBridge(this), "avataarCallback");

        // The sample URL to load.
        webView.loadUrl("https://orion-dev.avataar.me/engine/TOUCHCHANGES1/AVTR_EXP_d41d8cd9/index.html?ar=0&tenantId=AVTR-TNT-t8mv4evu&productId=158&env=local&onFloor=false");
    }
}