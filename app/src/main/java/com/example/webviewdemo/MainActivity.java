package com.example.webviewdemo;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.PermissionRequest;
public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_CODE = 101;
    Button cameraBtn;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraBtn = findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
    }

    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_CODE);
        } else {
            openWebView();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openWebView();
            } else {
                Toast.makeText(this, "Camera Permission Required!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  void openWebView() {
        cameraBtn.setVisibility(View.GONE);
        webView = findViewById(R.id.webview);
        webView.setVisibility(View.VISIBLE);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                return false;
            }

            // for testing added click event listener
            @Override
            public void onPageFinished(WebView view, String ulr) {
                String script = "document.getElementsByTagName('body')[0]"+".addEventListener("+
                "'click', function () {  js.ShowToast('Calling native class method form js code');})";
                webView.evaluateJavascript(script, null);
            }

        });
        webView.addJavascriptInterface(new JSBridge(this), "js");
        webView.loadUrl("https://orion-dev.avataar.me/engine/AVTR-TNT-t8mv4evu/AVTR_EXP_d41d8cd9/"+
                "index.html?ar=1&mode=renderer&tenantId=AVTR-TNT-t8mv4evu&productId=168");
    }
}
