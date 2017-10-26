package com.example.varietystore.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 鱼握拳 on 2017/9/13.
 */

public class User extends BmobObject {

        private String name;
    private String details;
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


}
