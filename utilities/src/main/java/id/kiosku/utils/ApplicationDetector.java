package id.kiosku.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class ApplicationDetector {
    private Context context;
    private PackageManager packageManager;

    public ApplicationDetector(Context context){
        this.context = context;
        packageManager = context.getPackageManager();
    }

    public static ApplicationDetector with(Context context){
        return new ApplicationDetector(context);
    }

    public boolean detect (String packageName){
        try{
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }
    }

    public void access (String packageName){
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        if (intent != null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }
}
