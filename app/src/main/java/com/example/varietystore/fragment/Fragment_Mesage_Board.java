package com.example.varietystore.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.varietystore.R;
import com.example.varietystore.activity.SendActivity;
import com.example.varietystore.adapter.BmodAdapter;
import com.example.varietystore.bean.User;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class Fragment_Mesage_Board extends Fragment implements  SwipeRefreshLayout.OnRefreshListener {
    private BmodAdapter adapter;
    private EasyRecyclerView recyclerView;
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;
    String lastTime = null;
    private TextView timer,detailtext,namer;
    List<User> user=new ArrayList<User>();

    private CardView cardview;
    private CoordinatorLayout coordinatorLayout;
    private android.os.Handler handler = new android.os.Handler();
    private android.support.design.widget.FloatingActionButton button;
    String[] cardcolor={"#ffebee","#fce4ec","#f3e5f5","#ede7f6","#e8eaf6","#e3f2fd","#e1f5fe","#e0f2f1","#e8f5e9","#f1f8e9","#f9fbe7","#fffde7","#eceff1","#fafafa","#efebe9"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__mesage__board, null);
        Bmob.initialize(getContext(), "4c787de345efe34c979a88785fb90b02");
        getData(0, STATE_REFRESH);
        button= (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SendActivity.class);
                startActivity(intent);
            }
        });
        coordinatorLayout= (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        recyclerView = (EasyRecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setRefreshListener(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new BmodAdapter(getContext());
        adapter.setNoMore(R.layout.no_more_layout);
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                getData(curPage,STATE_MORE);
            }

            @Override
            public void onMoreClick() {

            }

        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Random random=new Random();
                int c=random.nextInt(14);
                LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View detailcontent=inflater.inflate(R.layout.dialog_detail,null);
                cardview= (CardView)detailcontent.findViewById(R.id.cardview);
                cardview.setCardBackgroundColor(Color.parseColor(cardcolor[c]));
                detailtext= (TextView)detailcontent.findViewById(R.id.detailtext);
                timer= (TextView) detailcontent.findViewById(R.id.timer);
                namer= (TextView) detailcontent.findViewById(R.id.namer);
                detailtext.setText((CharSequence) user.get(position).getDetails());
                timer.setText(user.get(position).getCreatedAt());
                namer.setText(user.get(position).getName());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setView(detailcontent);
                         builder.show();

            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void getData(int page,final int actionType) {
        BmobQuery<User> query=new BmobQuery<>();
        query.order("-createdAt");
        if (actionType == STATE_MORE) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
                Log.i("0414", date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            query.setSkip(limit*curPage-10);
        } else {
            page = 0;
            query.setSkip(page);
        }
        query.setLimit(limit);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (list.size()>0) {
                    if (actionType == STATE_REFRESH) {
                        curPage = 0;
                        user.clear();
                        lastTime = list.get(list.size() - 1).getCreatedAt();
                    }
                    for (User td : list) {
                        user.add(td);
                    }
                    curPage++;
                } else if (actionType == STATE_MORE) {
                    Snackbar.make(coordinatorLayout, "没有更多留言啦~", Snackbar.LENGTH_SHORT).show();
                } else if (actionType == STATE_REFRESH) {
                    Toast.makeText(getContext(), "没有", Toast.LENGTH_SHORT).show();
                }
                adapter.addAll(list);
            }
        });
    }
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            public void run() {
                adapter.clear();
                getData(0,STATE_REFRESH);
            }
        },100);
    }

    @Override
    public void onResume() {
        onRefresh();
        super.onResume();
    }

}