package com.companyname.project.base;

import com.companyname.project.data.PreferenceManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class BasePresenter<T extends BaseView> implements Presenter<T> {

    PreferenceManager prefrenceManager;
    private T mvpView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenter() {

    }

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    public PreferenceManager getPrefManger() {
        return prefrenceManager;
    }

    private boolean isViewAttached() {
        return mvpView != null;
    }

    protected T getView() {
        return mvpView;
    }

    protected void checkViewAttached() {
        if (!isViewAttached())
            throw new MvpViewNotAttachedException();
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super(
                    "Please call Presenter.attachView(MvpView) before"
                            + " requesting data to the Presenter");
        }
    }
}
