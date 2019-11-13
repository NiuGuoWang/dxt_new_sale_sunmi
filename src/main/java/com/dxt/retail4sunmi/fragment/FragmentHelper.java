package com.dxt.retail4sunmi.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;

import java.util.List;

public class FragmentHelper {

    public static void replaceFragment(FragmentManager manager, List<Fragment> list, @IdRes int
            tabIndex, int layoutId, @AnimRes int enter, @AnimRes int exit) {
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(layoutId, list.get(tabIndex));
        transaction.commit();
    }

    public static void switchFragment(FragmentManager manager, List<Fragment> list, int
            tabIndex) {
        FragmentTransaction transaction = manager.beginTransaction();
        //让当前显示的碎片进行隐藏
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isVisible()) {
                transaction.hide(list.get(i));
            }
        }

        Fragment toFragment = list.get(tabIndex);
        if (toFragment.isAdded()) {
            transaction.show(toFragment);
        } else {
            transaction.add(com.dxt.retail4sunmi.R.id.layout_container, toFragment);
        }
        transaction.commit();
    }
}
