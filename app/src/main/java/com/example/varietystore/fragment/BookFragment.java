package com.example.varietystore.fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.varietystore.R;
import com.example.varietystore.adapter.BookFragmentAdapter;
public class BookFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, null);
        ViewPager bookviewpager = (ViewPager) view.findViewById(R.id.bookviewpager);
        BookFragmentAdapter bookadapter = new BookFragmentAdapter(getFragmentManager());
        bookviewpager.setAdapter(bookadapter);
        bookviewpager.setOffscreenPageLimit(6);
//        bookviewpager.setPageTransformer(true,new MyPageTransform());
        TabLayout bookablayout = (TabLayout) view.findViewById(R.id.booktablayout);
        bookablayout.setupWithViewPager(bookviewpager);
        return view;

    }
}