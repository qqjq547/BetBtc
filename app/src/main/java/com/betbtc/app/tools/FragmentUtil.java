package com.betbtc.app.tools;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtil {
    FragmentManager fragmentManager;
    private int viewId;
    private Fragment[] fragments;
    private int curPos=-1;

    public FragmentUtil(FragmentManager fragmentManager, int viewId, Fragment[] fragments) {
        this.fragmentManager = fragmentManager;
        this.viewId = viewId;
        this.fragments = fragments;
    }
    public void showFragment(int index) {
        if (curPos == index) {
            return;
        }
        if (curPos >= 0) {
            switchContent(fragments[curPos], fragments[index]);
        } else {
            switchContent(null, fragments[index]);
        }
        curPos = index;
    }

    public void switchContent(Fragment from, Fragment to) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (from == null) {
            transaction.add(viewId, to).commit();
        } else if (from != to) {
            if (!to.isAdded()) {
                transaction.hide(from).add(viewId, to).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }
}
