package com.companyname.project.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;

import com.companyname.project.AndroidBoilerApplication;
import com.companyname.project.injection.component.ActivityComponent;
import com.companyname.project.injection.component.ConfigPersistentComponent;
import com.companyname.project.injection.component.DaggerConfigPersistentComponent;
import com.companyname.project.injection.module.ActivityModule;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent> componentsArray = new LongSparseArray<>();
    private Activity mContext;
    private long activityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;


        ConfigPersistentComponent configPersistentComponent;

        if (componentsArray.get(activityId) == null) {

            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(AndroidBoilerApplication.get(this).getComponent())
                    .build();
            componentsArray.put(activityId, configPersistentComponent);
        } else {

            configPersistentComponent = componentsArray.get(activityId);
        }
        ActivityComponent activityComponent =
                configPersistentComponent.activityComponent(new ActivityModule(this));
        inject(activityComponent);
        attachView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected abstract int getLayout();

    protected abstract void inject(ActivityComponent activityComponent);

    protected abstract void attachView();

    protected abstract void detachPresenter();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            componentsArray.remove(activityId);
        }
        detachPresenter();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContext = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

