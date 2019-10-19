package com.betbtc.app.tools;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;

import com.betbtc.app.ui.activity.common.WebActivity;

public class IntentUtils {

    public static void startWebActivity(Activity activity, String title, String url) {
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(Constant.TITLE, title);
        intent.putExtra(Constant.URL, url);
        activity.startActivity(intent);
    }

    public static void startOutWebActivity(Activity activity, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startWechatApp(Activity activity) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startWeiboApp(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("sinaweibo://splash"));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

}
