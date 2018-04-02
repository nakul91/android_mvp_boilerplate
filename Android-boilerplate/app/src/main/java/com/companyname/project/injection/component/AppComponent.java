package com.companyname.project.injection.component;

import android.app.Application;
import android.content.Context;

import com.companyname .project.AndroidBoilerApplication;
import com.companyname.project.data.PreferenceManager;
import com.companyname.project.injection.module.AppModule;
import com.companyname.project.services.ApiService;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

    void inject(AndroidBoilerApplication app);

    Context context();

    Application application();

    PreferenceManager getPreferenceManager();

    ApiService apiService();

}
