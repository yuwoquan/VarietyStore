package com.example.varietystore.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.varietystore.R;
import com.example.varietystore.adapter.MovieDetailAdapter;
import com.example.varietystore.api.ApiService;
import com.example.varietystore.bean.MovieDetailBean;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MovieDetailActivity extends AppCompatActivity {
    private ImageView image, viewo;
    private TextView author,type,cast,country,time,rating,viewcontent;
    private List<MovieDetailBean.CastsBean> listCast;
    private MovieDetailAdapter adapter;
    private EasyRecyclerView easyRecyclerView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private static final String TAG = "MovieDetailActivity";
    private Toolbar toolbar;
    private String baseUrl = "http://api.douban.com/v2/movie/subject/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        settoolbar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#00000000"));
        }
        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.viewrecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        easyRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MovieDetailAdapter(this);
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(MovieDetailActivity.this,Detail_content_Activity.class);
                intent.putExtra("weburl",adapter.getItem(position).getAlt());
                intent.putExtra("titlename",adapter.getItem(position).getName());
                startActivity(intent);
            }
        });
        String picgeturp=getIntent().getStringExtra("picurl");
        String id = getIntent().getStringExtra("Id");
        initView();
        initdata(id);
        initbg(picgeturp);


    }

    private void initView() {
        image= (ImageView) findViewById(R.id.image_view);
        viewo= (ImageView) findViewById(R.id.image);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        author= (TextView) findViewById(R.id.author);
        time= (TextView) findViewById(R.id.time);
        cast= (TextView) findViewById(R.id.cast);
        type= (TextView) findViewById(R.id.type);
        country= (TextView) findViewById(R.id.country);
        viewcontent= (TextView) findViewById(R.id.viewcontent);
        rating= (TextView) findViewById(R.id.rating);
    }

    private void initdata(String id) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "initdata: " + id);
        ApiService service = retrofit.create(ApiService.class);
        service.getNumber(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetailBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "成功了");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "失败了" + e.getMessage());

                    }

                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
//                        listMovie.add(movieDetailBean);
//                        adapter.addAll(listMovie);
                        listCast=movieDetailBean.getCasts();
                        adapter.addAll(listCast);
                        initSetContentView(movieDetailBean);
                        collapsingToolbarLayout.setTitle(movieDetailBean.getTitle());
                        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#000000"));
                        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
                    }
                });
    }

    private void initSetContentView(final MovieDetailBean movieDetailBean) {
        List<MovieDetailBean.DirectorsBean> listAuthor=movieDetailBean.getDirectors();
        for (MovieDetailBean.DirectorsBean authorName:listAuthor){
            author.setText("导演："+authorName.getName());
        }
        List<MovieDetailBean.CastsBean> listcast=movieDetailBean.getCasts();
        StringBuffer str = new StringBuffer();
        int i = 1;
        for (MovieDetailBean.CastsBean subcast:listcast){
            String cast=subcast.getName();
            if(listcast.size()==i){
                str.append(cast);
            }else{
                str.append(cast+"，");
            }
            i++;
        }
        cast.setText("主演："+str);
        List<String> arr=movieDetailBean.getGenres();
        StringBuilder strr=new StringBuilder();
        for (i = 0; i <arr.size() ; i++) {
            if (i==arr.size()-1){
                strr.append(arr.get(i));
            }else {
                strr.append(arr.get(i));
                strr.append(",");
            }
            type.setText("类型："+strr);
        }


        List<String> arrCountry=movieDetailBean.getCountries();
        StringBuilder strrCountry=new StringBuilder();
        for ( i = 0; i <arrCountry.size() ; i++) {
            if (i==arrCountry.size()-1){
                strrCountry.append(arrCountry.get(i));
            }else {
                strrCountry.append(arrCountry.get(i));
                strrCountry.append(",");
            }
            country.setText("制片国家/地区："+strrCountry);
        }
        time.setText("上映日期："+movieDetailBean.getYear()+"年");
        rating.setText("评分："+movieDetailBean.getRating().getAverage()+"分"+"        ⊙"+movieDetailBean.getRatings_count()+"人参与评分");
        viewcontent.setText(movieDetailBean.getSummary());
        viewo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent(MovieDetailActivity.this,Detail_content_Activity.class);
                intent.putExtra("weburl",movieDetailBean.getAlt());
                intent.putExtra("titlename",movieDetailBean.getTitle());
                startActivity(intent);
                return false;
            }

        });
    }


    private void initbg(String picgeturp) {
        Glide.with(this)
                .load(picgeturp)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(getApplicationContext(), 14, 3))
                .into(image);
        Glide.with(this)
                .load(picgeturp)
                .into(viewo);
    }

        private void settoolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    android.support.v7.app.ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
    }
}
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
