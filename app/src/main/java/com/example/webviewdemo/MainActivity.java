package com.example.webviewdemo;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Button;
import android.webkit.WebChromeClient;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_CODE = 101;
    WebView webView;
    Button cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraBtn = findViewById(R.id.camBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
    }

    public void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_CODE);
        } else {
            openWebView();
        }
    }

    public void openWebView() {
        webView = findViewById(R.id.webView);
        cameraBtn.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
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
                        "'click', function () {  js.ShowToast('Calling native class method form js code');})";
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
