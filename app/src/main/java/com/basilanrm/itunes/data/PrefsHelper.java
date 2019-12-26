package com.basilanrm.itunes.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.basilanrm.itunes.constants.Constants;
import com.basilanrm.itunes.constants.Keys;
import com.basilanrm.itunes.di.AppContext;
import com.basilanrm.itunes.di.PrefsInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PrefsHelper {

    private final SharedPreferences mPrefs;

    @Inject
    public PrefsHelper(@AppContext Context context, @PrefsInfo String prefName) {
        mPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public String getLastVisitedDate() {
        String storedLastVisitedDate = mPrefs.getString(Keys.PREFS_LAST_VISITED_DATE, "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        String date = simpleDateFormat.format(new Date());
        if (storedLastVisitedDate != null && !storedLastVisitedDate.isEmpty()) {
            return storedLastVisitedDate;
        }
        return date;
    }

    public void setLastVisitedDate(String date) {
        mPrefs.edit()
                .putString(Keys.PREFS_LAST_VISITED_DATE, date)
                .commit();
    }
}
