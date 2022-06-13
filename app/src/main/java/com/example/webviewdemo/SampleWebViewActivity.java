package com.example.webviewdemo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

/**
 * This class contains code to load the Avataar WebView as a separate Activity
 */
public class SampleWebViewActivity extends AppCompatActivity {
    public static final String PRODUCT_ID = "productId";
    public static final String VARIANT_ID = "variantId";

    AvataarWebView avataarWebView;

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
        String productId = params.getString(PRODUCT_ID);
        String variantId = params.getString(VARIANT_ID);
        avataarWebView.open(productId, variantId);
    }
}