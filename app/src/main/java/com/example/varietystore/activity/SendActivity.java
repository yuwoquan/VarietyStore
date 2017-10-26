package com.example.varietystore.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.varietystore.R;
import com.example.varietystore.bean.User;
import com.example.varietystore.views.LinedEditText;

import java.util.Random;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class SendActivity extends AppCompatActivity {
    private Button send;
    private EditText name;
    private LinedEditText detail;
    private CoordinatorLayout coordinatorLayout;
    String RandomName[]={"前女友的未接来电","劝分社社长","绿帽社社长","我真的是你的小可爱","我再睡会就起来","香蕉你个不呐呐",
            "大香蕉你个不呐呐","Oranish","以后我要当村长","你的小男友","你的小女友",
           "别问我是谁","别问我请撩我","鱼握拳的小粉丝","鱼握拳的小姐姐",
            "千年老巫妖","野性又迷人","小姐姐","一条单身狗","卖火柴的小女孩","我真的戴过绿帽","会飞的小丑鸭"
            ,"没有故事没有狗","酷酷的小仙女","城建余文乐","开平余文乐"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#546e7a"));
        }

        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.layout);
        name= (EditText) findViewById(R.id.name);
        detail= (LinedEditText) findViewById(R.id.detail);
        send= (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendMessage();
            }
        });
    }

    private void sendMessage() {
        String  bmob_name=name.getText().toString();
        String bmob_detail=detail.getText().toString();
        if (TextUtils.isEmpty(detail.getText().toString())){
            Snackbar.make(coordinatorLayout, "名字可以为空，但是内容不能为空哦", Snackbar.LENGTH_SHORT).show();

        }else{
            if (TextUtils.isEmpty(name.getText().toString())){
                Random random=new Random();
                int randomname=random.nextInt(31);
                bmob_name=RandomName[randomname];
            }
                send.setTextColor(Color.parseColor("#f24862"));
                User user =new User();
                user.setName(bmob_name);
                user.setDetails(bmob_detail);
          user.save(new SaveListener<String>() {
              @Override
              public void done(String s, BmobException e) {
                  if (e==null){
                      Toast.makeText(SendActivity.this, "成功咯", Toast.LENGTH_SHORT).show();
                  }else {
                      Toast.makeText(SendActivity.this, "失败啦", Toast.LENGTH_SHORT).show();
                  }
                  finish();
                 }
          });
      }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
