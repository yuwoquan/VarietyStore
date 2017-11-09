package com.example.varietystore.activity;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.varietystore.R;
import com.example.varietystore.fragment.BookFragment;
import com.example.varietystore.fragment.Fragment_About;
import com.example.varietystore.fragment.Fragment_Mesage_Board;
import com.example.varietystore.fragment.Fragment_gank;
import com.example.varietystore.fragment.Fragment_movie;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView toolbartitle;
    private NavigationView navigationView;
    private boolean isDrawer=false;
    private int transaction;
    private ImageView  sky;
    private Fragment fragmentmovie,fragmentbook,fragmentgank,fragmentmessageboard,fragmentabout;
    private Fragment mFragment;//当前显示的Fragment
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        FragmentManager fragmentManage = getSupportFragmentManager();
        fragmentManage.beginTransaction().add(R.id.replacefragment,fragmentmovie).commit();
        mFragment=fragmentmovie;


//cooedinatorlayout的监听
        coordinatorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isDrawer) {
                    return navigationView.dispatchTouchEvent(event);
                } else {
                    return false;
                }
            }
        });


        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer=true;
                WindowManager manager= (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display=manager.getDefaultDisplay();
                coordinatorLayout.layout(navigationView.getRight(),0,navigationView.getRight()+display.getWidth(),display.getHeight());
            }
            public void onDrawerOpened(View drawerView) {}
            public void onDrawerClosed(View drawerView) {}
            public void onDrawerStateChanged(int newState){}
        });

//切换fragment
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.movie:
                        switchFragment(fragmentmovie);
                        break;
                    case R.id.gank:
                        switchFragment(fragmentgank);
                        break;
                    case R.id.messageboard:
                        switchFragment(fragmentmessageboard);
                        break;
                    case R.id.book:
                        switchFragment(fragmentbook);
                        break;
                    case R.id.about:
                        switchFragment(fragmentabout);
//                        transaction = getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.replacefragment,new Fragment_About())
//                                .commit();
                        break;
                }
                menuItem.setChecked(true);
                toolbartitle.setText(menuItem.getTitle());
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initViews() {
        //初始化控件
        navigationView= (NavigationView) findViewById(R.id.nav_view);
        toolbartitle= (TextView) findViewById(R.id.toolbar_title);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer);
        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.toright);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //沉淀式通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        //设置标题
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.home1);
        }
        fragmentbook=new BookFragment();
        fragmentgank=new Fragment_gank();
        fragmentmessageboard=new Fragment_Mesage_Board();
        fragmentmovie=new Fragment_movie();
        fragmentabout=new Fragment_About();
    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.replacefragment, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }
    //侧滑菜单的打开
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
