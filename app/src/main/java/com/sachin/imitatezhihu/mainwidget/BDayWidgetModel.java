package com.sachin.imitatezhihu.mainwidget;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/30.
 */
public class BDayWidgetModel extends APrefWidgetModel{

    private String tag="BDayWidgetModel";
    private static String BDAY_WIDGET_PROVIDER_NAME="com.androidbook.BDtwidget.BDayWidgetProvider";

    public String getName() {
        return name;
    }

    private String name="anon";

    private static String F_NAME="name";

    public String getBday() {
        return bday;
    }

    private String bday="1/1/2001";
    private static  String F_BDAY="bday";

    public BDayWidgetModel(int instancedid) {
        super(instancedid);
    }
    public BDayWidgetModel(int instancedid,String inName,String inBdya) {

        super(instancedid);
        this.name=inName;
        this.bday=inBdya;


    }

    @Override
    public String getPreName() {

        return BDAY_WIDGET_PROVIDER_NAME;
    }

    @Override
    public void init(){ }
    public void setName(String name){this.name=name;}
    public void setBday(String inBday){
        this.bday=inBday;
    }




    public long howManyDays(){
       return Utils.howFraINDays(Utils.getDate(this.bday));
    }



    public void setValueForPref(String key,String value){
        if(key.equals(getStoreKeyForFieldName(BDayWidgetModel.F_NAME))){
            this.name=value;
            return;
        }
        if(key.equals(getStoreKeyForFieldName(BDayWidgetModel.F_BDAY))){
            this.bday=value;
            return;

        }
    }

    @Override
    public Map<String, String> getPrefsToSave() {
        Map<String ,String >map=new HashMap<>();
        map.put(BDayWidgetModel.F_NAME,this.name);
        map.put(BDayWidgetModel.F_BDAY,this.bday);
        return map;



    }
    public String toString(){

        StringBuffer sb=new StringBuffer();
        sb.append("iid:"+iid);
        sb.append("name:"+this.name);
        sb.append("bday:"+this.bday);
        return sb.toString();

    }

    public static void clearAllPreferences(Context ctx){
        APrefWidgetModel.clearALlPreferences(ctx,BDayWidgetModel.BDAY_WIDGET_PROVIDER_NAME);
    }
    public static BDayWidgetModel retriveModel(Context ctx,int widgetID){
        BDayWidgetModel m=new BDayWidgetModel(widgetID);
        boolean found=m.retrievePrefs(ctx);
        return found?m:null;
    }
}
