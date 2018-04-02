package com.companyname.project.injection.component;

import com.companyname.project.ui.activity.HomeActivity;
import com.companyname.project.injection.PerActivity;
import com.companyname.project.injection.module.ActivityModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(HomeActivity homeActivity);


}
