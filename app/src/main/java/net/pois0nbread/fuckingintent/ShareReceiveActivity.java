package net.pois0nbread.fuckingintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;



public class ShareReceiveActivity extends AppCompatActivity {
    private static  String TAG = "ShareReceiveActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent recievedIntent = getIntent();
        Intent tiebaIntent = new Intent();

        if (Intent.ACTION_SEND.equals(recievedIntent.getAction()) && recievedIntent.getType() != null) {
            //Uri uri = intent.getParcelableExtra(Intent.EXTRA_TEXT);
            if ("text/plain".equals(recievedIntent.getType())) {
                // 处理发送来的url
                String recvievedText = recievedIntent.getClipData().getItemAt(0).getText().toString();
                Uri mUri = Uri.parse(recvievedText);
                Log.d(TAG, "onCreate: URI="+mUri);
                String  kz= mUri.getQueryParameter("kz"),
                        kw = mUri.getQueryParameter("kw");

                if (kz == null) kz = mUri.getLastPathSegment();
                try {
                    Float.parseFloat(kz);
                } catch (Exception e) {
                    kz = null;
                }

                if (kz != null) {
                    tiebaIntent.setData(Uri.parse("tbpb://tieba.baidu.com//tid="+kz));
                } else if (kw != null) {
                    tiebaIntent.setData(Uri.parse("tbfrs://tieba.baidu.com//kw="+kw));
                } else {
                    tiebaIntent.setData(Uri.parse("tbmaintab://tieba.baidu.com//"));
                }

                tiebaIntent.setAction(Intent.ACTION_VIEW);
                startActivity(tiebaIntent);
                finish();
            }
        }
    }
}
