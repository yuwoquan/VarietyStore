package com.example.varietystore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.varietystore.R;
import com.example.varietystore.activity.GanhuoActivity;
import com.example.varietystore.activity.MainActivity;
import com.example.varietystore.adapter.GankAdapter;
import com.example.varietystore.api.ApiService;
import com.example.varietystore.bean.Ganhuo;
import com.example.varietystore.http.GankRetrofit;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 鱼握拳 on 2017/9/10.
 */

public class Fragment_gank extends Fragment implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private static final String[] cities = { "Android", "iOS", "休息视频","拓展资源", "前端", "瞎推荐" };
    private EasyRecyclerView recyclerView;
    private List<Ganhuo.ResultsBean> ganHuoList;
    private GankAdapter gankAdapter;
    private Handler handler = new Handler();
    private int page=1;
    private Toolbar toolbar;
    private String type;
    private FloatingActionsMenu child;
    private com.getbase.floatingactionbutton.FloatingActionButton one,two,three,four,five,six;
    private CardView cardview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gank,null);
        initView(view);
        onRefresh();
        type=cities[0];

        Toast.makeText(getContext(), "当前新闻的类型是："+type, Toast.LENGTH_SHORT).show();
        cardview= (CardView)view.findViewById(R.id.cardview);
        recyclerView = (EasyRecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setRefreshListener(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        gankAdapter = new GankAdapter(getContext());
        recyclerView.setAdapter(gankAdapter);
        gankAdapter.setMore(R.layout.load_more_layout,this);
        gankAdapter.setNoMore(R.layout.no_more_layout);
        gankAdapter.setError(R.layout.error_layout);
        gankAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getContext(),GanhuoActivity.class);
                intent.putExtra("url",gankAdapter.getItem(position).getUrl());
                intent.putExtra("title",gankAdapter.getItem(position).getDesc());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initView(View view) {
        child= (FloatingActionsMenu) view.findViewById(R.id.fab_menu);
        one= (com.getbase.floatingactionbutton.FloatingActionButton)view.findViewById(R.id.fab_1);
        two= (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.fab_2);
        three= (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.fab_3);
        four= (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.fab_4);
        five= (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.fab_5);
        six= (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.fab_6);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
    }

    private void getData(String type,int count,int page) {
        GankRetrofit.getRetrofit()
                .create(ApiService.class)
                .getGanHuo(type,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Ganhuo>() {
                    public void onCompleted() {
                    }
                    public void onError(Throwable e) {
                        Log.d(String.valueOf(MainActivity.class),e.getMessage());
                    }
                    public void onNext(Ganhuo ganHuo) {
                        ganHuoList = ganHuo.getResults();
                        gankAdapter.addAll(ganHuoList);
                    }
                });
    }

    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            public void run() {
                gankAdapter.clear();
                getData(type,20,1);
                page=2;

            }
        },100);
    }
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(type,20,page);
                page++;
            }
        },100);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_1:
                type=cities[0];
                break;
            case R.id.fab_2:
                type=cities[1];
                break;
            case R.id.fab_3:
                type=cities[2];
                break;

            case R.id.fab_4:
                type=cities[3];
                break;

            case R.id.fab_5:
                type=cities[4];
                break;

            case R.id.fab_6:
                type=cities[5];
                break;
            default:
                break;
        }
        onRefresh();
        child.collapse();
        Toast.makeText(getContext(), "当前的新闻类型是："+type, Toast.LENGTH_SHORT).show();
    }
}


