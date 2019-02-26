package id.kiosku.utils;

import android.content.Context;
import android.content.SharedPreferences;

import id.kiosku.utils.exceptions.NotInitializedObjectException;

/**
 * Created by Dodi on 2/24/2016.
 */
public class PreferencesHelper {
    private static PreferencesHelper anInstance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private String name;
    public PreferencesHelper(Context context,String name){
        this.context = context;
        this.name = name;
        this.preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public static void init(Context context, String name){
        anInstance = new PreferencesHelper(context,name);
    }
    public static PreferencesHelper with(Context context, String name){
        return new PreferencesHelper(context, name);
    }
    public static boolean isInitialized(){
        return anInstance!=null;
    }
    public static PreferencesHelper getInstance() throws NotInitializedObjectException{
        if(anInstance==null)throw new NotInitializedObjectException();
        return anInstance;
    }

    public Context getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    public SharedPreferences getPreferences(){
        return preferences;
    }
    public SharedPreferences.Editor getEditor(){
        return editor;
    }
}
