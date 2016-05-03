package com.app.dameiren.adapter.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.dameiren.R;
import com.app.dameiren.ui.live.LiveMainFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * Created by liujiacheng on 2016/4/28.
 */
public class ViewPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private String[] tabNames = { "直播间", "视频", "社区","商城", "我" };
    private int[] tabIcons = { R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
            R.drawable.maintab_4_selector,R.drawable.maintab_5_selector };
    private LayoutInflater inflater;

    public ViewPagerAdapter(FragmentManager fragmentManager ,Context mContext) {
        super(fragmentManager);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = (TextView) inflater.inflate(R.layout.fragment_tab_main, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabNames[position]);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {

        LiveMainFragment mainFragment = new LiveMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LiveMainFragment.INTENT_STRING_TABNAME, tabNames[position]);
        bundle.putInt(LiveMainFragment.INTENT_INT_INDEX, position);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

}

