package com.example.varietystore.adapter;


import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.varietystore.R;
import com.example.varietystore.bean.DoubanMovieBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.List;


/**
 * Created by 鱼握拳 on 2017/9/10.
 */

public class MovieViewHolder extends BaseViewHolder<DoubanMovieBean.SubjectsBean> {
    private TextView title,author,year,avarage,type,name;
    private static final String TAG = "MovieViewHolder";
    private ImageView imageView;
    public MovieViewHolder(ViewGroup parent) {
        super(parent, R.layout.movie_item);
        title=$(R.id.movie_title);
        year=$(R.id.movie_year);
        author=$(R.id.movie_author);
        avarage=$(R.id.movie_average);
        type=$(R.id.movie_type);
        name=$(R.id.movie_name);
        imageView=$(R.id.movie_photo);
    }
    @Override
    public void setData(DoubanMovieBean.SubjectsBean data) {
        super.setData(data);
        title.setText(data.getTitle());
        year.setText("上映日期："+data.getYear());
        List<String> arr=data.getGenres();
        StringBuilder strr=new StringBuilder();
        for (int i = 0; i <arr.size() ; i++) {
            if (i==arr.size()-1){
                strr.append(arr.get(i));
            }else {
                strr.append(arr.get(i));
                strr.append(",");
            }
            type.setText("类型："+strr);
        }
        List<DoubanMovieBean.SubjectsBean.DirectorsBean> listauthor=data.getDirectors();
        for (DoubanMovieBean.SubjectsBean.DirectorsBean subauthor:listauthor){
            author.setText("导演："+subauthor.getName());
        }
        List<DoubanMovieBean.SubjectsBean.CastsBean> listcast=data.getCasts();
        StringBuffer str = new StringBuffer();
        int i = 1;
        for (DoubanMovieBean.SubjectsBean.CastsBean subcast:listcast){
            String cast=subcast.getName();
            if(listcast.size()==i){
                str.append(cast);
            }else{
                str.append(cast+"，");
           }
            i++;
        }
        name.setText("主演："+str);
        avarage.setText("豆瓣评分："+String.valueOf(data.getRating().getAverage()));
         Glide.with(getContext())
               .load(data.getImages().getMedium())
                .error(R.drawable.errorpicture)
                 .animate(R.anim.item_alpha)
                .into(imageView);
    }
}
