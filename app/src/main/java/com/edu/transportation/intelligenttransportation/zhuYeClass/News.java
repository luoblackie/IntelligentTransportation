package com.edu.transportation.intelligenttransportation.zhuYeClass;

public class News {

    /**
     * code : 200
     * data : {"aid":"MTEwODQ1NzA","title":"P2P聚财猫创始人薛亮被警方控制 曾承诺\"不跑路\"","headpic":"http://cms-bucket.nosdn.127.net/2018/07/26/a35d64cfce55476e8d10360f93002e15.png","writer":"","source":"财经杂志","source_url":"http://money.163.com/18/0726/14/DNLACR130025816P.html","reply_count":2,"click_count":0,"pub_time":1532588210,"summary":"仅仅根据出借人在第三方平台上自发组织的不完全统计，聚财猫的待收资金就达11.44亿元人民币，\u201c投资难民\u201d遍布31个省份，达6504人。这一数据还在不断攀升。聚财","content":"<div class=\"page js-page on\"><p>一直以\u201c模范平台\u201d示人的上海网贷平台聚财猫，在这一波P2P爆雷中也遭遇\u201c生死劫\u201d。<\/p><\/p>\n<p>\u201c聚财猫也是家人口碑推荐，某种意义上说，我们整个家族都被雷了，\u201d 蒋静说。<\/p>","imglist":"","taglist":""}
     * msg : success
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * aid : MTEwODQ1NzA
         * title : P2P聚财猫创始人薛亮被警方控制 曾承诺"不跑路"
         * headpic : http://cms-bucket.nosdn.127.net/2018/07/26/a35d64cfce55476e8d10360f93002e15.png
         * writer :
         * source : 财经杂志
         * source_url : http://money.163.com/18/0726/14/DNLACR130025816P.html
         * reply_count : 2
         * click_count : 0
         * pub_time : 1532588210
         * summary : 仅仅根据出借人在第三方平台上自发组织的不完全统计，聚财猫的待收资金就达11.44亿元人民币，“投资难民”遍布31个省份，达6504人。这一数据还在不断攀升。聚财
         * content : <div class="page js-page on"><p>一直以“模范平台”示人的上海网贷平台聚财猫，在这一波P2P爆雷中也遭遇“生死劫”。</p></p>
         <p>“聚财猫也是家人口碑推荐，某种意义上说，我们整个家族都被雷了，” 蒋静说。</p>
         * imglist :
         * taglist :
         */

        private String aid;
        private String title;
        private String headpic;
        private String writer;
        private String source;
        private String source_url;
        private int reply_count;
        private int click_count;
        private int pub_time;
        private String summary;
        private String content;
        private String imglist;
        private String taglist;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public String getWriter() {
            return writer;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public int getReply_count() {
            return reply_count;
        }

        public void setReply_count(int reply_count) {
            this.reply_count = reply_count;
        }

        public int getClick_count() {
            return click_count;
        }

        public void setClick_count(int click_count) {
            this.click_count = click_count;
        }

        public int getPub_time() {
            return pub_time;
        }

        public void setPub_time(int pub_time) {
            this.pub_time = pub_time;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImglist() {
            return imglist;
        }

        public void setImglist(String imglist) {
            this.imglist = imglist;
        }

        public String getTaglist() {
            return taglist;
        }

        public void setTaglist(String taglist) {
            this.taglist = taglist;
        }
    }
}
