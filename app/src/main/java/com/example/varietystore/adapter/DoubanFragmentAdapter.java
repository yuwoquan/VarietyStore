package com.example.varietystore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.varietystore.fragment.Douban_MovieFragment;

/**
 * Created by 鱼握拳 on 2017/10/15.
 */

public class DoubanFragmentAdapter extends FragmentPagerAdapter {
    public final int MovieCOUNT=3;
    private String[] type=new String[]{"正在热映","即将上映","Top250"};

    public DoubanFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return Douban_MovieFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return MovieCOUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return type[position];
    }

}
