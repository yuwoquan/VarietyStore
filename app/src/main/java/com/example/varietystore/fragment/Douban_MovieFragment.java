package com.example.varietystore.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.varietystore.R;
import com.example.varietystore.activity.MovieDetailActivity;
import com.example.varietystore.adapter.MvoieAdapter;
import com.example.varietystore.api.ApiService;
import com.example.varietystore.bean.DoubanMovieBean;
import com.example.varietystore.http.DoubanBookretrofit;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Douban_MovieFragment extends Fragment implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    public static final String PATH = "args_page";
    private List<DoubanMovieBean.SubjectsBean> doubanList;
    private MvoieAdapter adapter;
    private Handler handler = new Handler();
    private EasyRecyclerView recyclerView;
    private static final String TAG = "Douban_MovieFragment";
    private int start=0;
    private String part;
    private static String[] path={"in_theaters","coming_soon","top250"};
    public static Douban_MovieFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putString(PATH, path[position]);
        Douban_MovieFragment fragment = new Douban_MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        part = getArguments().getString(PATH);
        adapter=new MvoieAdapter(getActivity());
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douban__movie, null);
        onRefresh();
        recyclerView = (EasyRecyclerView)view.findViewById(R.id.recycler);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new MvoieAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setRefreshListener(this);
        adapter.setMore(R.layout.load_more_layout,this);
        adapter.setNoMore(R.layout.no_more_layout);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getContext(),MovieDetailActivity.class);
                intent.putExtra("picurl",adapter.getItem(position).getImages().getLarge());
                intent.putExtra("Id",adapter.getItem(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    public void getMovieData(final String part, final int start) {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                //打印retrofit日志
//                Log.d(TAG, "豆瓣电影链接："+message);
//
//            }
//        });
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .readTimeout(10,TimeUnit.SECONDS)
//                .build();
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.douban.com/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(client)
//                .build();
//        final ApiService service=retrofit.create(ApiService.class);
//        service.getTopMovie(part,start,10)
        DoubanBookretrofit.getRetrofit()
                .create(ApiService.class)
                .getTopMovie(part,start,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "当前栏目："+part);
                        Log.d(String.valueOf(getContext()), "错误： "+e.getMessage());
                        ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("豆瓣服务器返回数据出错，请稍后访问...");
                    }

                    @Override
                    public void onNext(DoubanMovieBean doubansBean) {
                        doubanList= doubansBean.getSubjects();
                        adapter.addAll(doubanList);
                    }
                });

    }

    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getMovieData(part,10+start*10);
                start++;
            }
        },100);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            public void run() {
                getMovieData(part,0);
            }
        },100);
    }
    public void onStart() {
        super.onStart();
        Log.d(TAG, "电影："+"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "电影："+"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "电影："+"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"电影："+ "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "电影："+"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "电影："+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "电影："+"onDetach");
    }
}
