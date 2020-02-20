package net.pois0nbread.fffintent;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

/**
 * <pre>
 *     author : Pois0nBread
 *     e-mail : pois0nbreads@gmail.com
 *     time   : 2020/02/20
 *     desc   : QuickStartService
 *     version: 2.0
 * </pre>
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class QuickStartService extends TileService {

    @Override
    public void onClick() {
        super.onClick();
        ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData data = cm.getPrimaryClip();
        ClipData.Item item = data.getItemAt(0);
        Uri mUri = Uri.parse(item.getText().toString());
        Intent localIntent = new Intent(this, ViewActivity.class);
        localIntent.setData(mUri);
        startActivityAndCollapse(localIntent);
    }
}
