package com.sachin.imitatezhihu.mainwidget;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by lenovo on 2016/4/30.
 */
public abstract class APrefWidgetModel implements IWidgetModelSaveContract {
    private static String tag = "APrefWidgetModel";

    public int iid;

    public APrefWidgetModel(int instancedid) {
        iid = instancedid;
    }

    @Override
    public abstract String getPreName();

    @Override
    public abstract void init();


    @Override
    public void setValueForPref(String key, String value) {
        return;

    }

    /**
     * 该方法被子类重写，子类会返回一个map结构《fileName，value》
     *
     * @return
     */
    @Override
    public Map<String, String> getPrefsToSave() {
        return null;
    }

    private String getStoreKeyForFieldName(String filedName) {
        return filedName + "_" + iid;

    }

    private void savePref(SharedPreferences.Editor editor, String key, String value) {
        String newKey = getStoreKeyForFieldName(key);
        editor.putString(key, value);


    }


    public void savePreferences(Context context) {
        Map<String, String> keyValuesPairs = getPrefsToSave();
        if (keyValuesPairs == null) return;

        SharedPreferences.Editor prefEditor = context.getSharedPreferences(getPreName(), 0).edit();
        for (String key : keyValuesPairs.keySet()) {
            String value = keyValuesPairs.get(key);
            savePref(prefEditor, key, value);

        }
        prefEditor.commit();
    }


    private void removePref(SharedPreferences.Editor editor, String key) {
        String newkey = getStoreKeyForFieldName(key);
        editor.remove(newkey);
        //  editor.commit();
        // editor.apply();
    }


    public void removePrefs(Context ctx) {
        Map<String, String> keyValuePairs = getPrefsToSave();
        if (keyValuePairs == null) return;
        SharedPreferences.Editor editor = ctx.getSharedPreferences(getPreName(), 0).edit();
        for (String key : keyValuePairs.keySet()) {
            removePref(editor, key);
        }
        editor.apply();
    }

    public boolean retrievePrefs(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(getPreName(), 0);
        Map<String, ?> keyValuePairs = prefs.getAll();
        boolean preFound = false;
        for (String key : keyValuePairs.keySet()) {
            if (isItMyPrefs(key)) {
                String value = (String) keyValuePairs.get(key);
                setValueForPref(key, value);
                preFound = true;
            }
        }
        return preFound;
    }

    public static void clearALlPreferences(Context context, String prefName) {
        SharedPreferences prefs = context.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

    }

    private boolean isItMyPrefs(String keyName) {
        if (keyName.indexOf("_" + iid) > 0) {
            return true;
        }
        return false;
    }

}
