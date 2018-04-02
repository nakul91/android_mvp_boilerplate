package com.companyname.project.base;

public interface Presenter<V extends BaseView> {

    void attachView(V mvpView);

    void detachView();
}
