package com.companyname.project;

import android.app.Application;
import android.content.Context;

import com.companyname.project.injection.component.AppComponent;
import com.companyname.project.injection.component.DaggerAppComponent;
import com.companyname.project.injection.module.AppModule;

public class AndroidBoilerApplication extends Application {

    private AppComponent appComponent;

    public static AndroidBoilerApplication get(Context context) {
        return (AndroidBoilerApplication) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        if (appComponent == null) {

         appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
