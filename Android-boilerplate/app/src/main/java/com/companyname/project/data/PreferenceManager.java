package com.companyname.project.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.companyname.project.constants.AndroidBoilerConstants;
import com.companyname.project.injection.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceManager {

    private static PreferenceManager sInstance;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private PreferenceManager(Context ctx) {
        mPreferences = ctx.getSharedPreferences(AndroidBoilerConstants.USER_PREF_FILE, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    @Inject
    public PreferenceManager(Context context, @PreferenceInfo String prefFileName) {
        mPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static PreferenceManager instance(Context ctx) {
        if (sInstance == null)
            sInstance = new PreferenceManager(ctx);
        return sInstance;
    }

    public void ClearSharedPreferences() {
        mEditor.clear();
        mEditor.commit();
    }
}
