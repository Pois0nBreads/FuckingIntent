package net.pois0nbread.fuckingintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri mUri = getIntent().getData();
        Intent intent = new Intent();
        String  kz= mUri.getQueryParameter("kz"),
                kw = mUri.getQueryParameter("kw");

        if (kz == null) kz = mUri.getLastPathSegment();
        try {
            Float.parseFloat(kz);
        } catch (Exception e) {
            kz = null;
        }

        if (kz != null) {
            intent.setData(Uri.parse("tbpb://tieba.baidu.com//tid="+kz));
        } else if (kw != null) {
            intent.setData(Uri.parse("tbfrs://tieba.baidu.com//kw="+kw));
        } else {
            intent.setData(Uri.parse("tbmaintab://tieba.baidu.com//"));
        }
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
        finish();
    }
}
