package com.example.webviewdemo;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 101;
    public static final String PRODUCT_ID = "productId";
    public static final String VARIANT_ID = "variantId";

    Button avataarExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avataarExperience = findViewById(R.id.camBtn);
        avataarExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
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
            openWebViewActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openWebViewActivity();
            }
            else {
                Toast.makeText(MainActivity.this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /** Open the Avataar WebView in a separate Activity */
    public void openWebViewActivity() {
        Intent intent = new Intent(this, SampleWebViewActivity.class);

        //Send Product ID and Variant ID to the WebViewActivity
        Bundle params = new Bundle();
        params.putString(PRODUCT_ID, "158");
        params.putString(VARIANT_ID, "168");
        intent.putExtras(params);
        startActivity(intent);
    }
}
