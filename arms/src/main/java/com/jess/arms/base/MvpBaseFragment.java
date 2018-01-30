package com.jess.arms.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.FragmentLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.util.ArmsUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * 因为 Java 只能单继承,所以如果要用到需要继承特定 @{@link Fragment} 的三方库,那你就需要自己自定义 @{@link Fragment}
 * 继承于这个特定的 @{@link Fragment},然后再按照 {@link MvpBaseFragment} 的格式,将代码复制过去,记住一定要实现{@link IFragment}
 */
public abstract class MvpBaseFragment<P extends IPresenter> extends Fragment implements IFragment, FragmentLifecycleable {
    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();
    private Cache<String, Object> mCache;
    private View mRootView;
    private boolean isFirstLoad = true; //第一次加载数据
    @Inject
    protected P mPresenter;

    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(getActivity()).cacheFactory().build(CacheType.FRAGMENT_CACHE);
        }
        return mCache;
    }


    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (initView(inflater, container, savedInstanceState) == null) {
            if (mRootView == null || !isReuseView()) {
                mRootView = inflater.inflate(getLayoutRes(), container, false);
            }
        } else {
            mRootView = initView(inflater, container, savedInstanceState);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        onLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mRootView == null) {
            return;
        }

        onLoadData();
    }

    private void onLoadData() {
        if (getUserVisibleHint() && isFirstLoad) {
            isFirstLoad = false;
            onLazyLoad();
        }
    }

    /**
     * 懒加载 空实现
     */
    protected void onLazyLoad() {
        Log.d(TAG, "onLazyLoad: ");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        mPresenter = null;
        mRootView = null;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    /**
     * 是否使用eventBus,默认为使用(false)，
     * @return
     */
    @Override
    public boolean useEventBus() {
        return false;
    }

    /**
     * 是否复用View 默认为true
     * @return
     */
    @Override
    public boolean isReuseView() {
        return true;
    }
}
