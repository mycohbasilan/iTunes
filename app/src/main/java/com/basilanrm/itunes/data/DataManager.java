package com.basilanrm.itunes.data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {
    ApiHelper apiHelper;

    @Inject
    public DataManager(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }
}
