package com.example.interfaces;

/**
 * Created by Administrator on 2016/6/24.
 */

public class DataCenter {

    /**
     * 数据来源
     */
    public enum PROVIDER {
        NET, DB, MOCK;
    }

    private PROVIDER dataProvider = PROVIDER.NET;

    public void setDataProvider(PROVIDER dataProvider) {
        this.dataProvider = dataProvider;
    }

    
}
