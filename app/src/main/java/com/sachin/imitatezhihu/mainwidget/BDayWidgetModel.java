package com.sachin.imitatezhihu.mainwidget;

/**
 * Created by lenovo on 2016/4/30.
 */
public class BDayWidgetModel extends APrefWidgetModel{

    private String tag="BDayWidgetModel";
    private static String BDAY_WIDGET_PROVIDER_NAME="com.androidbook.BDtwidget.BDayWidgetProvider";
    private String name="anon";

    private static String F_NAME="name";
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
        final int i = 0;
        return i;
    }

}
