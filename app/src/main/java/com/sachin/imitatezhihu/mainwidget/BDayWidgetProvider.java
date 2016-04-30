package com.sachin.imitatezhihu.mainwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by lenovo on 2016/4/29.
 */
public class BDayWidgetProvider extends AppWidgetProvider {
    private final String Tag = "BDayWidgetProvider";
    private String name;


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        System.out.println("onUpdate(Context context, AppWidgetManager appWid");

        final int n = appWidgetIds.length;
        for (int i = 0; i < n; i++) {
            int appWidgetId = appWidgetIds[i];
            updateAppWidget(context, appWidgetManager, appWidgetId);

        }

    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        BDayWidgetModel model = BDayWidgetModel.retriveModel(context, appWidgetId);
        if (model == null) return;

    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        System.out.println("onDeleted(Context context, int[] appWidgetIds");
        super.onDeleted(context, appWidgetIds);
        final int n = appWidgetIds.length;
        for (int i = 0; i < n; i++) {
            BDayWidgetModel model = BDayWidgetModel.retriveModel(context, appWidgetIds[i]);
            model.removePrefs(context);
        }

    }

    @Override
    public void onEnabled(Context context) {
        System.out.println(" onEnabled(Context context)");
        super.onEnabled(context);
        BDayWidgetModel.clearAllPreferences(context);
        PackageManager pm = context.getPackageManager();
        //清楚部件模型中的所有持久数据；启用接收消息；设置组件的状态以及标示
        pm.setComponentEnabledSetting(new ComponentName("com.sachin.imitatezhihu.mainwidget", ".BDayWidgetProvider"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);


    }

    @Override
    public void onDisabled(Context context) {
        System.out.println(" onDisabled(Context context)");
        super.onDisabled(context);
        BDayWidgetModel.clearAllPreferences(context);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName("com.sachin.imitatezhihu.mainwidget", ".BDayWidgetProvider"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);


    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }
}
