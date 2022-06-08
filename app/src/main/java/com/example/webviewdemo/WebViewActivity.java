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
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
            // for testing added click event listener
            @Override
            public void onPageFinished(WebView view, String ulr) {
                String script = "document.getElementsByTagName('body')[0]"+".addEventListener("+
                        "'click', function () {  js.showToast('Calling native class method form js code');})";
                webView.evaluateJavascript(script, null);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
        });
        webView.addJavascriptInterface(new JSBridge(this), "js");
        webView.loadUrl("https://orion-dev.avataar.me/engine/AVTR-TNT-t8mv4evu/AVTR_EXP_d41d8cd9/index.html?ar=0&mode=renderer&tenantId=AVTR-TNT-t8mv4evu&productId=168");
    }
}