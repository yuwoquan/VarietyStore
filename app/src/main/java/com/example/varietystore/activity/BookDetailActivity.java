package com.example.varietystore.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.varietystore.R;
import com.example.varietystore.bean.DoubanBookBean;
import com.example.varietystore.bean.MovieDetailBean;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class BookDetailActivity extends AppCompatActivity {
        private Toolbar toolbar;
        private ImageView imageView,imageViewbg;
        private TextView book_author,book_rating,book_pubdate,book_publisher,book_page,book_binding,book_price,author_detail,summary_detail,catalog_detail,toolbartitle;
        private  String picurl,rating,pubdate,publisher,page,binding,price,authordetail,summary,catalog,title;
        private String author;
        private static final String TAG = "BookDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        inittoolbar();
        initView();
        initFindView();
        initImage();
        initSettext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#00000000"));
        }
    }

    private void initSettext() {
        Log.d(TAG, "作者"+author.toString());
        book_rating.setText("评分："+rating+" 分");
        book_pubdate.setText("出版时间："+pubdate);
        book_page.setText("页数："+page+"页");
        book_publisher.setText("出版社："+publisher);
        book_price.setText("价格："+price);
        book_author.setText("作者:"+author);
        book_binding.setText("装帧："+binding);
        author_detail.setText(authordetail);
        summary_detail.setText(summary);
        catalog_detail.setText(catalog);
        toolbartitle.setText(title);
    }

    private void initView() {
        title=getIntent().getStringExtra("title");
        picurl=getIntent().getStringExtra("image");
        rating=getIntent().getStringExtra("rating");
        author=getIntent().getStringExtra("author");
        pubdate=getIntent().getStringExtra("pubdate");
        publisher=getIntent().getStringExtra("publisher");
        page=getIntent().getStringExtra("page");
        binding=getIntent().getStringExtra("binding");
        price=getIntent().getStringExtra("price");
        authordetail=getIntent().getStringExtra("authordetail");
        summary=getIntent().getStringExtra("summary");
        catalog=getIntent().getStringExtra("catalog");
    }
    private void initImage() {
        Glide.with(this)
                .load(picurl)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(getApplicationContext(), 14, 3))
                .into(imageViewbg);
        Glide.with(this)
                .load(picurl)
                .into(imageView);
    }

    private void initFindView() {
        toolbartitle= (TextView) findViewById(R.id.title);
        imageViewbg= (ImageView) findViewById(R.id.image_bg);
        imageView= (ImageView) findViewById(R.id.image);
        book_rating= (TextView) findViewById(R.id.rating);
        book_pubdate= (TextView) findViewById(R.id.pubdate);
        book_page= (TextView) findViewById(R.id.page);
        book_publisher= (TextView) findViewById(R.id.publisher);
        book_price= (TextView) findViewById(R.id.price);
        book_author= (TextView) findViewById(R.id.author);
        book_binding= (TextView) findViewById(R.id.binding);
        author_detail= (TextView) findViewById(R.id.author_detail);
        summary_detail= (TextView) findViewById(R.id.summary);
        catalog_detail= (TextView) findViewById(R.id.catalog);
    }
    private void inittoolbar() {

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setTitle(title);
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
