package com.example.webviewdemo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import static com.example.webviewdemo.Constants.*;

/**
 * This class contains code to load the Avataar WebView as a separate Activity
 */
public class SampleWebViewActivity extends AppCompatActivity {
    AvataarWebView avataarWebView;
    public static String productId = "";
    public static String tenantId = "";
    public static String ar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set up the Close button to close AvataarWebView
        Button buttonOne = (Button) findViewById(R.id.close_button);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (null != avataarWebView) {
                    avataarWebView.close();
                    avataarWebView = null;
                }
            }
        });

        //Create the AvataarWebView object
        WebView webView = findViewById(R.id.webView);
        AvataarJSBridgeInterface jsBridge = new SampleJSBridge(this);
        avataarWebView = new AvataarWebView(webView, jsBridge);

        //Trigger the Avataar experience with the product and variant Ids sent by the MainActivity
        Bundle params = getIntent().getExtras();
        productId = params.getString(PRODUCT_ID);
        tenantId = params.getString(TENANT_ID);
        ar = params.getString(AR);
        avataarWebView.open(productId, tenantId, ar);
    }

    /** Camera permission is needed to trigger Avataar's experience
     * This sample code asks for camera permission as soon as 3D experience is triggered irrespective of whether 3D experience
     * or AR experience is opened first.
     * This will be a one time permission for an app user.
     * */
    public void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            ar = "1";
            avataarWebView.open(productId, tenantId, ar);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ar = "1";
                avataarWebView.open(productId, tenantId, ar);
            }
            else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}