package net.pois0nbread.fffintent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * <pre>
 *     author : Pois0nBread
 *     e-mail : pois0nbreads@gmail.com
 *     time   : 2020/02/20
 *     desc   : UseInfoActivity
 *     version: 2.0
 * </pre>
 */

public class UseInfoActivity extends AppCompatActivity {

    private WebView m_WebView;
    private WebSettings m_WebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_info);
        m_WebView = findViewById(R.id.use_info_webview);
        m_WebSettings = m_WebView.getSettings();

        m_WebSettings.setLoadWithOverviewMode(true);
        m_WebSettings.setUseWideViewPort(true);

        m_WebView.setBackgroundColor(Color.TRANSPARENT);
        m_WebView.clearCache(true);
        m_WebView.loadUrl("file:///android_asset/html/index.html");
    }

    @Override
    protected void onDestroy() {
        m_WebView.destroy();
        super.onDestroy();
    }
}
