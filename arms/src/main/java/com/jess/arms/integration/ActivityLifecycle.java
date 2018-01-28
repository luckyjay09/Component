/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jess.arms.integration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.jess.arms.base.MvpBaseActivity;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.util.ActivityUtils;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;


@Singleton
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private Application mApplication;
    private Cache<String, Object> mExtras;
    private FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycle;

    @Inject
    public ActivityLifecycle(Application application, Cache<String, Object> extras) {
        this.mApplication = application;
        this.mExtras = extras;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.i("onActivityCreated: " + activity.getClass().getSimpleName());
        ActivityUtils.setTopActivityWeakRef(activity);
        //如果 intent 包含了此字段,并且为 true 说明不加入到 list 进行统一管理
        boolean isNotAdd = false;
        if (activity.getIntent() != null) {
            isNotAdd = activity.getIntent().getBooleanExtra(ArmsUtils.IS_NOT_ADD_ACTIVITY_LIST, false);
        }

        if (!isNotAdd) ActivityUtils.add(activity);

        registerFragmentCallbacks(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Timber.i("onActivityStarted: " + activity.getClass().getSimpleName());
        ActivityUtils.setTopActivityWeakRef(activity);

    }

    @Override
    public void onActivityResumed(Activity activity) {
        Timber.i("onActivityResumed: " + activity.getClass().getSimpleName());
        ActivityUtils.setTopActivityWeakRef(activity);

    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.i("onActivityPaused: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Timber.i("onActivityStopped: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.i("onActivitySaveInstanceState: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.i("onActivityDestroyed: " + activity.getClass().getSimpleName());
        ActivityUtils.remove(activity);
    }

    /**
     * 给每个 Activity 的所有 Fragment 设置监听其生命周期
     *
     * @param activity
     */
    private void registerFragmentCallbacks(Activity activity) {
        if (activity instanceof MvpBaseActivity) {

            if (mFragmentLifecycle == null) {
                mFragmentLifecycle = new FragmentLifecycle();
            }

            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(mFragmentLifecycle, true);

        }
    }

}
