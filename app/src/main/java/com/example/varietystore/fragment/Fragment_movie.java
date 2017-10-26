package com.example.varietystore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.varietystore.R;
import com.example.varietystore.adapter.DoubanFragmentAdapter;
import com.example.varietystore.views.MyPageTransform;


/**
 * Created by 鱼握拳 on 2017/8/7.
*/

public class Fragment_movie extends Fragment {
    private static final String TAG = "Fragment_movie";
    private ViewPager movieviewPager;
    private  TabLayout movietabLayout;
    private    DoubanFragmentAdapter movieadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie,null);
        //viewpage的动画，添加后感觉不好看
        //movieviewPager.setPageTransformer(true,new MyPageTransform());
        movieviewPager = (ViewPager) view.findViewById(R.id.movieviewPager);
        movieadapter = new DoubanFragmentAdapter(getFragmentManager());
        movieviewPager.setOffscreenPageLimit(3);
        movieviewPager.setAdapter(movieadapter);
        movietabLayout = (TabLayout) view.findViewById(R.id.movietabLayout);
        movietabLayout.setupWithViewPager(movieviewPager);
        return view;
    }

//打印fragment1的生命周期
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
