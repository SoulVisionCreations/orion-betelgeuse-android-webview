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
import static com.example.webviewdemo.Constants.*;

public class MainActivity extends AppCompatActivity {
    Button avataarExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avataarExperience = findViewById(R.id.camBtn);
        avataarExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewActivity();
            }
        });
    }

    /** Open the Avataar WebView in a separate Activity */
    public void openWebViewActivity() {
        Intent intent = new Intent(this, SampleWebViewActivity.class);
        //Send Product ID and Variant ID to the WebViewActivity
        Bundle params = new Bundle();
        params.putString(PRODUCT_ID, "117");
        params.putString(TENANT_ID, "AVTR-TNT-t8mv4evu");
        params.putString(AR, "0");
        intent.putExtras(params);
        startActivity(intent);
    }
}
