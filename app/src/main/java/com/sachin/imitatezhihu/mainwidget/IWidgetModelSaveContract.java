package com.sachin.imitatezhihu.mainwidget;

import java.util.Map;

/**
 * Created by lenovo on 2016/4/30.
 */
public interface IWidgetModelSaveContract {

    public String getPreName();
    public void setValueForPref(String key,String value);
    public Map<String,String> getPrefsToSave();
    public void init();



}
