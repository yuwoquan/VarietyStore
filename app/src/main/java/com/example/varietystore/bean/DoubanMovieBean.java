package com.example.varietystore.bean;

import java.util.List;

/**
 * Created by 鱼握拳 on 2017/10/2.
 */

public class DoubanMovieBean {

    /**
     * count : 1
     * start : 1
     * total : 250
     * subjects : [{"rating":{"max":10,"average":9.5,"stars":"50","min":0},"genres":["剧情","爱情","同性"],"title":"霸王别姬","casts":[{"alt":"https://movie.douban.com/celebrity/1003494/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/67.jpg","large":"http://img3.doubanio.com/img/celebrity/large/67.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/67.jpg"},"name":"张国荣","id":"1003494"},{"alt":"https://movie.douban.com/celebrity/1050265/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/46345.jpg","large":"http://img7.doubanio.com/img/celebrity/large/46345.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/46345.jpg"},"name":"张丰毅","id":"1050265"},{"alt":"https://movie.douban.com/celebrity/1035641/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/1399268395.47.jpg","large":"http://img3.doubanio.com/img/celebrity/large/1399268395.47.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/1399268395.47.jpg"},"name":"巩俐","id":"1035641"}],"collect_count":805709,"original_title":"霸王别姬","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1023040/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/750.jpg","large":"http://img7.doubanio.com/img/celebrity/large/750.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/750.jpg"},"name":"陈凯歌","id":"1023040"}],"year":"1993","images":{"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p1910813120.webp","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p1910813120.webp","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p1910813120.webp"},"alt":"https://movie.douban.com/subject/1291546/","id":"1291546"}]
     * title : 豆瓣电影Top250
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":9.5,"stars":"50","min":0}
         * genres : ["剧情","爱情","同性"]
         * title : 霸王别姬
         * casts : [{"alt":"https://movie.douban.com/celebrity/1003494/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/67.jpg","large":"http://img3.doubanio.com/img/celebrity/large/67.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/67.jpg"},"name":"张国荣","id":"1003494"},{"alt":"https://movie.douban.com/celebrity/1050265/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/46345.jpg","large":"http://img7.doubanio.com/img/celebrity/large/46345.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/46345.jpg"},"name":"张丰毅","id":"1050265"},{"alt":"https://movie.douban.com/celebrity/1035641/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/1399268395.47.jpg","large":"http://img3.doubanio.com/img/celebrity/large/1399268395.47.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/1399268395.47.jpg"},"name":"巩俐","id":"1035641"}]
         * collect_count : 805709
         * original_title : 霸王别姬
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1023040/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/750.jpg","large":"http://img7.doubanio.com/img/celebrity/large/750.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/750.jpg"},"name":"陈凯歌","id":"1023040"}]
         * year : 1993
         * images : {"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p1910813120.webp","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p1910813120.webp","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p1910813120.webp"}
         * alt : https://movie.douban.com/subject/1291546/
         * id : 1291546
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<DirectorsBean> directors;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 9.5
             * stars : 50
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p1910813120.webp
             * large : http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p1910813120.webp
             * medium : http://img7.doubanio.com/view/movie_poster_cover/spst/public/p1910813120.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1003494/
             * avatars : {"small":"http://img3.doubanio.com/img/celebrity/small/67.jpg","large":"http://img3.doubanio.com/img/celebrity/large/67.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/67.jpg"}
             * name : 张国荣
             * id : 1003494
             */

            private String alt;
            private AvatarsBean avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : http://img3.doubanio.com/img/celebrity/small/67.jpg
                 * large : http://img3.doubanio.com/img/celebrity/large/67.jpg
                 * medium : http://img3.doubanio.com/img/celebrity/medium/67.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1023040/
             * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/750.jpg","large":"http://img7.doubanio.com/img/celebrity/large/750.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/750.jpg"}
             * name : 陈凯歌
             * id : 1023040
             */

            private String alt;
            private AvatarsBeanX avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : http://img7.doubanio.com/img/celebrity/small/750.jpg
                 * large : http://img7.doubanio.com/img/celebrity/large/750.jpg
                 * medium : http://img7.doubanio.com/img/celebrity/medium/750.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
