package net.pois0nbread.fuckingintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextView1 = null;
    TextView mTextView2 = null;
    TextView mTextView3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
    }

    private void bindView() {
        mTextView1 = (TextView) findViewById(R.id.main_button1);
        mTextView2 = (TextView) findViewById(R.id.main_button2);
        mTextView3 = (TextView) findViewById(R.id.main_button3);
        //
        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mTextView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coolapk.com/u/2108563")));
                break;
            case R.id.main_button2:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Pois0nBreads/FuckingIntent")));
                break;
            case R.id.main_button3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://afdian.net/@Pois0nBread")));
                break;
        }
    }
}
