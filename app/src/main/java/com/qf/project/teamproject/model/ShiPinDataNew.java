package com.qf.project.teamproject.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class ShiPinDataNew {

    /**
     * ret : 1
     * data : {"list":[{"id":4567492,"mid":268380,"content":"中国有史以来最卓越的奇迹工程，令人赞叹！","reviews":61,"likes":282,"up":295,"ct":1466842526,"imgs":[{"id":15599888,"w":480,"h":480,"fmt":"jpeg","video":1,"dancnt":69}],"videos":{"15599888":{"thumb":15599888,"url":"http://tbapi.ixiaochuan.cn/urlresolver/get_video_real_url/vid/Y9stXsysBuGtrnspZDnHuQ","dur":0,"priority":1,"webinfo":{"url":"http://www.miaopai.com/show/Y9stXsysBuGtrnspZDnHuQ__.htm","topheight":70,"height":359,"aspect_ratio":1},"urlsrc":"http://tbvideo.ixiaochuan.cn/zyvd/ab/31/b1bd157479ace518eb9a765f19bf","urlext":"http://tbapi.ixiaochuan.cn/urlresolver/get_video_real_url/vid/Y9stXsysBuGtrnspZDnHuQ"}},"status":2,"share":18,"member":{"id":268380,"isreg":1,"name":"自己滴女王","gender":2,"avatar":345269,"sign":"主宰自己的人生，做自己的女王，我们要高调的活着！","is_assessor":0},"topic":{"id":101762,"topic":"#当时我就震惊了#","cover":13868205,"atts_title":"受惊者","posts":44443,"partners":892416,"atts":892416,"addition":"892416个受惊者","share":-1,"ut":1474509579},"god_reviews":[{"_id":17882473,"ct":1466845326,"down":7,"godcheck":0,"godt":1466856628,"id":17882473,"isgod":1,"likes":285,"mid":1843216,"pid":4567492,"review":"牛逼，前辈们真心牛逼，很抱歉文化水平不足，我只能说这个词才能表达我的敬佩之情","source":"user","up":292,"views":51737,"mname":"杨人渣。","avatar":16405933,"gender":1}]}]}
     */

    private int ret;
    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4567492
         * mid : 268380
         * content : 中国有史以来最卓越的奇迹工程，令人赞叹！
         * reviews : 61
         * likes : 282
         * up : 295
         * ct : 1466842526
         * imgs : [{"id":15599888,"w":480,"h":480,"fmt":"jpeg","video":1,"dancnt":69}]
         * videos : {"15599888":{"thumb":15599888,"url":"http://tbapi.ixiaochuan.cn/urlresolver/get_video_real_url/vid/Y9stXsysBuGtrnspZDnHuQ","dur":0,"priority":1,"webinfo":{"url":"http://www.miaopai.com/show/Y9stXsysBuGtrnspZDnHuQ__.htm","topheight":70,"height":359,"aspect_ratio":1},"urlsrc":"http://tbvideo.ixiaochuan.cn/zyvd/ab/31/b1bd157479ace518eb9a765f19bf","urlext":"http://tbapi.ixiaochuan.cn/urlresolver/get_video_real_url/vid/Y9stXsysBuGtrnspZDnHuQ"}}
         * status : 2
         * share : 18
         * member : {"id":268380,"isreg":1,"name":"自己滴女王","gender":2,"avatar":345269,"sign":"主宰自己的人生，做自己的女王，我们要高调的活着！","is_assessor":0}
         * topic : {"id":101762,"topic":"#当时我就震惊了#","cover":13868205,"atts_title":"受惊者","posts":44443,"partners":892416,"atts":892416,"addition":"892416个受惊者","share":-1,"ut":1474509579}
         * god_reviews : [{"_id":17882473,"ct":1466845326,"down":7,"godcheck":0,"godt":1466856628,"id":17882473,"isgod":1,"likes":285,"mid":1843216,"pid":4567492,"review":"牛逼，前辈们真心牛逼，很抱歉文化水平不足，我只能说这个词才能表达我的敬佩之情","source":"user","up":292,"views":51737,"mname":"杨人渣。","avatar":16405933,"gender":1}]
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private int mid;
            private String content;
            private int reviews;
            private int likes;
            private int up;
            private int ct;
            private int status;
            private int share;
            /**
             * id : 268380
             * isreg : 1
             * name : 自己滴女王
             * gender : 2
             * avatar : 345269
             * sign : 主宰自己的人生，做自己的女王，我们要高调的活着！
             * is_assessor : 0
             */

            private MemberBean member;
            /**
             * id : 101762
             * topic : #当时我就震惊了#
             * cover : 13868205
             * atts_title : 受惊者
             * posts : 44443
             * partners : 892416
             * atts : 892416
             * addition : 892416个受惊者
             * share : -1
             * ut : 1474509579
             */

            private TopicBean topic;
            /**
             * id : 15599888
             * w : 480
             * h : 480
             * fmt : jpeg
             * video : 1
             * dancnt : 69
             */

            private List<ImgsBean> imgs;
            /**
             * _id : 17882473
             * ct : 1466845326
             * down : 7
             * godcheck : 0
             * godt : 1466856628
             * id : 17882473
             * isgod : 1
             * likes : 285
             * mid : 1843216
             * pid : 4567492
             * review : 牛逼，前辈们真心牛逼，很抱歉文化水平不足，我只能说这个词才能表达我的敬佩之情
             * source : user
             * up : 292
             * views : 51737
             * mname : 杨人渣。
             * avatar : 16405933
             * gender : 1
             */

            private List<GodReviewsBean> god_reviews;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getReviews() {
                return reviews;
            }

            public void setReviews(int reviews) {
                this.reviews = reviews;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public int getUp() {
                return up;
            }

            public void setUp(int up) {
                this.up = up;
            }

            public int getCt() {
                return ct;
            }

            public void setCt(int ct) {
                this.ct = ct;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getShare() {
                return share;
            }

            public void setShare(int share) {
                this.share = share;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public TopicBean getTopic() {
                return topic;
            }

            public void setTopic(TopicBean topic) {
                this.topic = topic;
            }

            public List<ImgsBean> getImgs() {
                return imgs;
            }

            public void setImgs(List<ImgsBean> imgs) {
                this.imgs = imgs;
            }

            public List<GodReviewsBean> getGod_reviews() {
                return god_reviews;
            }

            public void setGod_reviews(List<GodReviewsBean> god_reviews) {
                this.god_reviews = god_reviews;
            }

            public static class MemberBean {
                private int id;
                private int isreg;
                private String name;
                private int gender;
                private int avatar;
                private String sign;
                private int is_assessor;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIsreg() {
                    return isreg;
                }

                public void setIsreg(int isreg) {
                    this.isreg = isreg;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }

                public int getAvatar() {
                    return avatar;
                }

                public void setAvatar(int avatar) {
                    this.avatar = avatar;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public int getIs_assessor() {
                    return is_assessor;
                }

                public void setIs_assessor(int is_assessor) {
                    this.is_assessor = is_assessor;
                }
            }

            public static class TopicBean {
                private int id;
                private String topic;
                private int cover;
                private String atts_title;
                private int posts;
                private int partners;
                private int atts;
                private String addition;
                private int share;
                private int ut;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTopic() {
                    return topic;
                }

                public void setTopic(String topic) {
                    this.topic = topic;
                }

                public int getCover() {
                    return cover;
                }

                public void setCover(int cover) {
                    this.cover = cover;
                }

                public String getAtts_title() {
                    return atts_title;
                }

                public void setAtts_title(String atts_title) {
                    this.atts_title = atts_title;
                }

                public int getPosts() {
                    return posts;
                }

                public void setPosts(int posts) {
                    this.posts = posts;
                }

                public int getPartners() {
                    return partners;
                }

                public void setPartners(int partners) {
                    this.partners = partners;
                }

                public int getAtts() {
                    return atts;
                }

                public void setAtts(int atts) {
                    this.atts = atts;
                }

                public String getAddition() {
                    return addition;
                }

                public void setAddition(String addition) {
                    this.addition = addition;
                }

                public int getShare() {
                    return share;
                }

                public void setShare(int share) {
                    this.share = share;
                }

                public int getUt() {
                    return ut;
                }

                public void setUt(int ut) {
                    this.ut = ut;
                }
            }

            public static class ImgsBean {
                private int id;
                private int w;
                private int h;
                private String fmt;
                private int video;
                private int dancnt;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getW() {
                    return w;
                }

                public void setW(int w) {
                    this.w = w;
                }

                public int getH() {
                    return h;
                }

                public void setH(int h) {
                    this.h = h;
                }

                public String getFmt() {
                    return fmt;
                }

                public void setFmt(String fmt) {
                    this.fmt = fmt;
                }

                public int getVideo() {
                    return video;
                }

                public void setVideo(int video) {
                    this.video = video;
                }

                public int getDancnt() {
                    return dancnt;
                }

                public void setDancnt(int dancnt) {
                    this.dancnt = dancnt;
                }
            }

            public static class GodReviewsBean {
                private int _id;
                private int ct;
                private int down;
                private int godcheck;
                private int godt;
                private int id;
                private int isgod;
                private int likes;
                private int mid;
                private int pid;
                private String review;
                private String source;
                private int up;
                private int views;
                private String mname;
                private int avatar;
                private int gender;

                public int get_id() {
                    return _id;
                }

                public void set_id(int _id) {
                    this._id = _id;
                }

                public int getCt() {
                    return ct;
                }

                public void setCt(int ct) {
                    this.ct = ct;
                }

                public int getDown() {
                    return down;
                }

                public void setDown(int down) {
                    this.down = down;
                }

                public int getGodcheck() {
                    return godcheck;
                }

                public void setGodcheck(int godcheck) {
                    this.godcheck = godcheck;
                }

                public int getGodt() {
                    return godt;
                }

                public void setGodt(int godt) {
                    this.godt = godt;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIsgod() {
                    return isgod;
                }

                public void setIsgod(int isgod) {
                    this.isgod = isgod;
                }

                public int getLikes() {
                    return likes;
                }

                public void setLikes(int likes) {
                    this.likes = likes;
                }

                public int getMid() {
                    return mid;
                }

                public void setMid(int mid) {
                    this.mid = mid;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public String getReview() {
                    return review;
                }

                public void setReview(String review) {
                    this.review = review;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getUp() {
                    return up;
                }

                public void setUp(int up) {
                    this.up = up;
                }

                public int getViews() {
                    return views;
                }

                public void setViews(int views) {
                    this.views = views;
                }

                public String getMname() {
                    return mname;
                }

                public void setMname(String mname) {
                    this.mname = mname;
                }

                public int getAvatar() {
                    return avatar;
                }

                public void setAvatar(int avatar) {
                    this.avatar = avatar;
                }

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }
            }
        }
    }
}
