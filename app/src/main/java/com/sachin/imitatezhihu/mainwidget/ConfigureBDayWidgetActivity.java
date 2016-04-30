package com.sachin.imitatezhihu.mainwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class ConfigureBDayWidgetActivity extends AppCompatActivity {
    private final String TAG = "ConfigureBDayWidgetActivity";
    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;//设置一个无效的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_bday_widget);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpButton();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            //说明：widgetId是系统为部件创建的，我们不需要为部件创建widgetID
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
    }

    private void setUpButton() {
        Button b = (Button) this.findViewById(R.id.btn_update);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentButtonClicked(v);

            }
        });

    }

    private void parentButtonClicked(View view) {
        String name = getName();
        String date = getDate();
        if (!Utils.validateDate(date)) {
            this.setDate("wrong date:" + date);
            return;
        }

        if (this.mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            return;
        }
        updateAppWidgetLocal(name, date);
        Intent intent = new Intent();
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, intent);
        finish();

    }

    private String getName() {
        EditText nameText = (EditText) this.findViewById(R.id.et_input_name);
        String name = nameText.getText().toString();
        return name;
    }

    private String getDate() {
        EditText dateText = (EditText) this.findViewById(R.id.et_date);
        String dateString = dateText.getText().toString();
        System.out.println("dateString"+dateString);
        return dateString;

    }

    private void setDate(String errorDate) {

        EditText dateText = (EditText) this.findViewById(R.id.et_date);
        dateText.setText(errorDate);

        dateText.requestFocus();

    }

    private void updateAppWidgetLocal(String name, String dob) {

        BDayWidgetModel model = new BDayWidgetModel(mAppWidgetId, name, dob);
        updateAppWidget(this, AppWidgetManager.getInstance(this), model);
        model.savePreferences(this);
    }

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, BDayWidgetModel widgetModel) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_show_view_layout);
        remoteViews.setTextViewText(R.id.tv_name,widgetModel.getName()+":"+widgetModel.iid);
        remoteViews.setTextViewText(R.id.tv_date,widgetModel.getBday());
        remoteViews.setTextViewText(R.id.tv_remain_date,Long.toString(widgetModel.howManyDays()));


        Intent defineIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pt= PendingIntent.getActivities(context,0, new Intent[]{defineIntent},0);

        remoteViews.setOnClickPendingIntent(R.id.btn_for_buy,pt);
        appWidgetManager.updateAppWidget(widgetModel.iid,remoteViews);

    }


}
