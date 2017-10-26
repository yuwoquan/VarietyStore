package com.example.varietystore.bean;

import java.util.List;

/**
 * Created by 鱼握拳 on 2017/8/12.
 */

public class Ganhuo {
    /**
     * error : false
     * results : [{"_id":"59798512421aa90ca209c4f9","createdAt":"2017-07-27T14:15:46.258Z","desc":"安卓音频截取及转换","publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"App","url":"https://github.com/google/ringdroid","used":true,"who":"jp1017"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59798512421aa90ca209c4f9
         * createdAt : 2017-07-27T14:15:46.258Z
         * desc : 安卓音频截取及转换
         * publishedAt : 2017-08-09T13:49:27.260Z
         * source : chrome
         * type : App
         * url : https://github.com/google/ringdroid
         * used : true
         * who : jp1017
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
