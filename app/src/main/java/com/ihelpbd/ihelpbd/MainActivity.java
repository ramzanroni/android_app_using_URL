package com.ihelpbd.ihelpbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.ZoomControls;

public class MainActivity extends AppCompatActivity {

    ProgressBar superProgressBar;
    WebView superWebView;
    ZoomControls zoomControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superProgressBar = findViewById(R.id.myProgressBar);
        superWebView = findViewById(R.id.myWebView);

        superProgressBar.setMax(100);

//        String link = "https://dialersoft.com/iagent-responsive/link.php";
        String link = "http://103.204.81.13/hatim_with_ticket/admin-app/";
        superWebView.getSettings().setAppCacheEnabled(false);
        superWebView.clearCache(true);
        superWebView.getSettings().setJavaScriptEnabled(true);
//        superWebView.getSettings().setSupportZoom(true);
//        superWebView.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
//        superWebView.getSettings().setDisplayZoomControls(true);
        if (18 < Build.VERSION.SDK_INT ){
            //18 = JellyBean MR2, KITKAT=19
            superWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        superWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        superWebView.loadUrl(link);
        superWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        superWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superProgressBar.setProgress(newProgress);
            }

        });

        superWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri myUri = Uri.parse(url);
                Intent superIntent = new Intent(Intent.ACTION_VIEW);
                superIntent.setData(myUri);
                startActivity(superIntent);
            }
        });
    }
}