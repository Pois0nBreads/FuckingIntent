package net.pois0nbread.fffintent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.net.URLDecoder;

/**
 * <pre>
 *     author : Pois0nBread & Leftshine
 *     e-mail : pois0nbreads@gmail.com
 *     time   : 2020/02/20
 *     desc   : ViewActivity
 *     version: 2.0
 * </pre>
 */

public class ViewActivity extends Activity {

    @Override
    protected void onNewIntent(Intent thisIntent) {
        super.onNewIntent(thisIntent);
        setIntent(thisIntent);
    }

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);

        try {
            //解析传来的URI
            Intent thisIntent = getIntent();
            Uri mUri;
            if (Intent.ACTION_SEND.equals(thisIntent.getAction())) {
                mUri = Uri.parse(thisIntent.getStringExtra("android.intent.extra.TEXT"));
                if (!mUri.getHost().equals("tieba.baidu.com") && !mUri.getHost().equals("m.baidu.com")) {
                    mUri = Uri.parse(thisIntent.getClipData().getItemAt(0).getText().toString());
                    if (!mUri.getHost().equals("tieba.baidu.com") && !mUri.getHost().equals("m.baidu.com")) throw new Exception("UnKnow Uri");
                }
            } else {
                mUri = thisIntent.getData();
            }
            //是否GBK解码
            if (mSharedPreferences.getBoolean("GBK", false))
                mUri = Uri.parse(URLDecoder.decode(mUri.toString(), "GBK"));
            //处理数据
            String kz = mUri.getQueryParameter("kz"), kw = mUri.getQueryParameter("kw");

            if (kz == null) kz = mUri.getLastPathSegment();
            try {
                Float.parseFloat(kz);
            } catch (Exception e) {
                kz = null;
            }
            //开始唤醒Tieba App
            Intent mIntent = new Intent();
            if (kz != null) {
                mIntent.setData(Uri.parse("tbpb://tieba.baidu.com//tid=" + kz));
            } else if (kw != null) {
                mIntent.setData(Uri.parse("tbfrs://tieba.baidu.com//kw=" + kw));
            } else {
                throw new Exception("UnKnow Uri");
            }
            mIntent.setAction(Intent.ACTION_VIEW);
            try {
                startActivity(mIntent);
            } catch (Exception e) {
                Toast.makeText(this, "找不到贴吧APP\n有时应用多开和太极也会造成这种问题", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            finish();
            Toast.makeText(this, "错误的链接", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
