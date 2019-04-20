package com.edu.transportation.intelligenttransportation.zhuYeClass;

import java.util.List;

public class NewsReply {
    /**
     * code : 200
     * data : {"list":[{"reply_id":1518988,"replier":"kgb110007","reply_cnt":"薛亮应该积极退赔清偿，良性清盘不应仅是你的高调口号，却是七八天不见人影！四百万投资者的近五百亿眼看就要打水漂了，苦不堪言！我们都是上有老下有小的，省吃俭用，现在是愁死了，厌食失眠便秘，这哪是善良老百姓的清平盛世日子啊！请政府及媒体朋友们持续关注聚财猫及薛亮后续吧！谢谢啦！","reply_time":1532588378},{"reply_id":1518989,"replier":"kgb110007","reply_cnt":"薛亮应该积极退赔清偿，良性清盘不应仅是你的高调口号，却是七八天不见人影！四百万投资者的近五百亿眼看就要打水漂了，苦不堪言！我们都是上有老下有小的，省吃俭用，现在是愁死了，厌食失眠便秘，这哪是善良老百姓的清平盛世日子啊！请政府及媒体朋友们持续关注聚财猫及薛亮后续吧！谢谢啦！","reply_time":1532588407}],"page":1,"psize":20,"count":2,"total":2}
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
         * list : [{"reply_id":1518988,"replier":"kgb110007","reply_cnt":"薛亮应该积极退赔清偿，良性清盘不应仅是你的高调口号，却是七八天不见人影！四百万投资者的近五百亿眼看就要打水漂了，苦不堪言！我们都是上有老下有小的，省吃俭用，现在是愁死了，厌食失眠便秘，这哪是善良老百姓的清平盛世日子啊！请政府及媒体朋友们持续关注聚财猫及薛亮后续吧！谢谢啦！","reply_time":1532588378},{"reply_id":1518989,"replier":"kgb110007","reply_cnt":"薛亮应该积极退赔清偿，良性清盘不应仅是你的高调口号，却是七八天不见人影！四百万投资者的近五百亿眼看就要打水漂了，苦不堪言！我们都是上有老下有小的，省吃俭用，现在是愁死了，厌食失眠便秘，这哪是善良老百姓的清平盛世日子啊！请政府及媒体朋友们持续关注聚财猫及薛亮后续吧！谢谢啦！","reply_time":1532588407}]
         * page : 1
         * psize : 20
         * count : 2
         * total : 2
         */

        private int page;
        private int psize;
        private int count;
        private int total;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPsize() {
            return psize;
        }

        public void setPsize(int psize) {
            this.psize = psize;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * reply_id : 1518988
             * replier : kgb110007
             * reply_cnt : 薛亮应该积极退赔清偿，良性清盘不应仅是你的高调口号，却是七八天不见人影！四百万投资者的近五百亿眼看就要打水漂了，苦不堪言！我们都是上有老下有小的，省吃俭用，现在是愁死了，厌食失眠便秘，这哪是善良老百姓的清平盛世日子啊！请政府及媒体朋友们持续关注聚财猫及薛亮后续吧！谢谢啦！
             * reply_time : 1532588378
             */

            private int reply_id;
            private String replier;
            private String reply_cnt;
            private int reply_time;

            public int getReply_id() {
                return reply_id;
            }

            public void setReply_id(int reply_id) {
                this.reply_id = reply_id;
            }

            public String getReplier() {
                return replier;
            }

            public void setReplier(String replier) {
                this.replier = replier;
            }

            public String getReply_cnt() {
                return reply_cnt;
            }

            public void setReply_cnt(String reply_cnt) {
                this.reply_cnt = reply_cnt;
            }

            public int getReply_time() {
                return reply_time;
            }

            public void setReply_time(int reply_time) {
                this.reply_time = reply_time;
            }
        }
    }
}
