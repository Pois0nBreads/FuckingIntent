package net.pois0nbread.fffintent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <pre>
 *     author : Pois0nBread
 *     e-mail : pois0nbreads@gmail.com
 *     time   : 2020/02/20
 *     desc   : MainActivity
 *     version: 2.0
 * </pre>
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener {

    TextView useinfo_TextView = null;
    TextView developerinfo_TextView = null;
    TextView otherdeveloperinfo_TextView = null;
    TextView pypay_TextView = null;
    TextView github_TextView = null;
    Switch enableGbk_Switch = null;

    private SharedPreferences mSharedPreferences = null;

    AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        mSharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        if (mSharedPreferences.getBoolean("first_open", true)) {
            new AlertDialog.Builder(this)
                    .setTitle("来自作者的留言 ⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄")
                    .setMessage("制作这个软件花费我不少时间，如果你喜欢这个项目，可以赞助我买瓶可乐\n⊙▽⊙")
                    .setNegativeButton("给作者打钱", (dialog, which) -> alertDialog.show())
                    .setPositiveButton("下次再说", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
            mSharedPreferences.edit().putBoolean("first_open", false).apply();
        }
        enableGbk_Switch.setChecked(mSharedPreferences.getBoolean("enable_gbk", false));
    }

    private void bindView() {
        useinfo_TextView = findViewById(R.id.main_useinfo_button);
        otherdeveloperinfo_TextView = findViewById(R.id.main_otherdeveloperinfo_button);
        developerinfo_TextView = findViewById(R.id.main_developerinfo_button);
        pypay_TextView = findViewById(R.id.main_pypay_button);
        github_TextView = findViewById(R.id.main_github_button);
        enableGbk_Switch = findViewById(R.id.main_gbkenable_switch);
        //
        useinfo_TextView.setOnClickListener(this);
        developerinfo_TextView.setOnClickListener(this);
        otherdeveloperinfo_TextView.setOnClickListener(this);
        pypay_TextView.setOnClickListener(this);
        pypay_TextView.setOnClickListener(this);
        github_TextView.setOnClickListener(this);
        enableGbk_Switch.setOnCheckedChangeListener(this);
        //
        View view = View.inflate(this, R.layout.dialog_layout, null);
        view.findViewById(R.id.dialog_button1).setOnClickListener(this);
        view.findViewById(R.id.dialog_button2).setOnClickListener(this);
        view.findViewById(R.id.dialog_button3).setOnClickListener(this);
        alertDialog = new AlertDialog.Builder(this).setView(view).setTitle("请选择赞助渠道 ⊙▽⊙").create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.main_useinfo_button:
                startActivity(new Intent(this, UseInfoActivity.class));
                return;
            case R.id.main_developerinfo_button:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coolapk.com/u/2108563")));
                return;
            case R.id.main_otherdeveloperinfo_button:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coolapk.com/u/530430")));
                return;
            case R.id.main_github_button:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Pois0nBreads/FuckingIntent")));
                return;
            case R.id.main_pypay_button:
                alertDialog.show();
                return;
        }
        switch (v.getId()) {
            case R.id.dialog_button1:
                String intentFullUrl = "intent://platformapi/startapp?saId=10000007&" +
                        "clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2Ffkx00694rmzfhta8chwcc3c%3F_s" +
                        "%3Dweb-other&_t=1472443966571#Intent;" +
                        "scheme=alipayqr;package=com.eg.android.AlipayGphone;end";
                try {
                    getPackageManager().getApplicationInfo("com.eg.android.AlipayGphone", PackageManager.GET_UNINSTALLED_PACKAGES);
                    Intent intent = Intent.parseUri(intentFullUrl, Intent.URI_INTENT_SCHEME);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(this, "您似乎没有安装支付宝", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dialog_button2:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/pois0nbread")));
                break;
            case R.id.dialog_button3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://pois0nbreads.github.io/Breads/")));
                break;
        }
        alertDialog.dismiss();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.main_gbkenable_switch:
                mSharedPreferences.edit().putBoolean("enable_gbk", isChecked).apply();
                break;
        }
    }
}
