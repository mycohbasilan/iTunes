package com.basilanrm.itunes.data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHelper {

    public ApiInterface apiInterface;

    @Inject
    ApiHelper(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

}
