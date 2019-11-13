package com.dxt.retail4sunmi.activity;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.fragment.FragmentHelper;
import com.dxt.retail4sunmi.fragment.MemberAddFragment;
import com.dxt.retail4sunmi.fragment.MemberFindFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MemberActivity extends BaseActivity {

    private List<Fragment> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        initView();
        initData();
    }

    private void initView() {
        TextView textBack = findViewById(R.id.text_back);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TabLayout tablayout = findViewById(R.id.tabLayout_change);
        setIndicator(tablayout);
        tablayout.addTab(tablayout.newTab().setText("会员查找"));
        tablayout.addTab(tablayout.newTab().setText("会员添加"));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentHelper.switchFragment(getFragmentManager(), mList, tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initData() {
        String type = getIntent().getStringExtra("type");
        MemberFindFragment memberFind = new MemberFindFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        memberFind.setArguments(bundle);
        MemberAddFragment memberAdd = new MemberAddFragment();
        memberAdd.setArguments(bundle);
        mList.add(memberFind);
        mList.add(memberAdd);
        FragmentHelper.switchFragment(getFragmentManager(), mList, 0);
    }

    private void setIndicator(TabLayout tabs) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (tabStrip != null) {
            tabStrip.setAccessible(true);
        }
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) (tabStrip != null ? tabStrip.get(tabs) : null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, Resources.getSystem().getDisplayMetrics());

        if (llTab != null) {
            for (int i = 0; i < llTab.getChildCount(); i++) {
                View child = llTab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            }
        }
    }
}
