package com.example.varietystore.fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.varietystore.R;
import com.example.varietystore.activity.BookDetailActivity;
import com.example.varietystore.activity.MainActivity;
import com.example.varietystore.adapter.BookAdapter;
import com.example.varietystore.api.ApiService;
import com.example.varietystore.bean.DoubanBookBean;
import com.example.varietystore.bean.MovieDetailBean;
import com.example.varietystore.http.DoubanBookretrofit;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.varietystore.R.id.page;

/**
 * Created by 鱼握拳 on 2017/10/16.
 */

public class Douban_BookFragment extends Fragment implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    @Nullable

    private List<DoubanBookBean.BooksBean> listBook;
    public static final String PATH = "args_page";
    private BookAdapter adapter;
    private Handler handler = new Handler();
    private EasyRecyclerView  bookrecyclerView;
    private static final String TAG = "Douban_MovieFragment";
    private int start=0;
    private String part;
    private static String[] path={"文化","生活","流行","青春","奋斗","科技"};
    private String authorName;
    public static Douban_BookFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putString(PATH, path[position]);
        Douban_BookFragment fragment = new Douban_BookFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        onRefresh();
        super.onCreate(savedInstanceState);
        part = getArguments().getString(PATH);
        adapter=new BookAdapter(getActivity());
    }
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.douban_book_fragment, null);
        onRefresh();
        bookrecyclerView = (EasyRecyclerView)view.findViewById(R.id.bookrecycler);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        bookrecyclerView.setLayoutManager(staggeredGridLayoutManager);
        bookrecyclerView.setRefreshListener(this);
        adapter = new BookAdapter(getContext());
        bookrecyclerView.setAdapter(adapter);
        adapter.setMore(R.layout.load_more_layout,this);
        adapter.setNoMore(R.layout.no_more_layout);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getContext(),BookDetailActivity.class);
                List<String> list=adapter.getItem(position).getAuthor();
                for (String name:list) {
                    intent.putExtra("author",name);
                }
                intent.putExtra("title",adapter.getItem(position).getTitle());
                intent.putExtra("image",adapter.getItem(position).getImage());
                intent.putExtra("rating",adapter.getItem(position).getRating().getAverage());
                intent.putExtra("pubdate",adapter.getItem(position).getPubdate());
                intent.putExtra("publisher",adapter.getItem(position).getPublisher());
                intent.putExtra("page",adapter.getItem(position).getPages());
                intent.putExtra("binding",adapter.getItem(position).getBinding());
                intent.putExtra("price",adapter.getItem(position).getPrice());
                intent.putExtra("authordetail",adapter.getItem(position).getAuthor_intro());
                intent.putExtra("summary",adapter.getItem(position).getSummary());
                intent.putExtra("catalog",adapter.getItem(position).getCatalog());
                startActivity(intent);
                }
        });
        return view;
    }
    private void getData(String part, int start) {
        DoubanBookretrofit.getRetrofit()
                .create(ApiService.class)
                .getBook(part,start,12)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanBookBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "成功了 ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ProgressDialog  progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("豆瓣服务器返回数据出错，请稍后访问...");
                        Log.d(String.valueOf(getContext()), "错误"+e.getMessage());
                    }

                    @Override
                    public void onNext(DoubanBookBean doubansBean) {
                        listBook=doubansBean.getBooks();
                        adapter.addAll(listBook);
                    }
                });
    }
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(part,12+start*12);
                start++;
            }
        },100);
    }
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            public void run() {
                getData(part,0);
            }
        },100);
    }
}
