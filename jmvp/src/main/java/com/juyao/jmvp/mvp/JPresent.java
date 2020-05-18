package com.juyao.jmvp.mvp;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by juyao on 2017/11/22.
 */

public class JPresent<V extends IView> implements IPresent<V> {
    String TAG = "JPresent";
    private WeakReference<V> v;

    @Override
    public void attachV(V view) {
        v = new WeakReference<V>(view);
    }

    @Override
    public void detachV() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    protected V getV() {
        if (v == null || v.get() == null) {
            Log.e(TAG, "v can not be null视图已销毁,注意内存泄漏");
//            throw new IllegalStateException("v can not be null");
        }
        return v.get();
    }
}

