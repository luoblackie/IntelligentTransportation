package com.edu.transportation.intelligenttransportation.zhuYeClass;

import java.util.List;

public class NewsUnit {

    /**
     * code : 200
     * data : {"list":[{"aid":"MTEzMTg5NzA","title":"上交所召开第一届科创板股票上市委员会成立大会","headpic":"http://cms-bucket.ws.126.net/2019/03/13/bf641a7d346a4f7b85c74b04d008091e.png","writer":"","source":"上交所","source_url":"http://money.163.com/19/0416/17/ECTAFRHK002580S6.html","reply_count":0,"click_count":0,"pub_time":1555405323},{"aid":"MTEzMTg5Njk","title":"尚德机构因虚假宣传被处罚 2018年净收入19.7亿","headpic":"http://cms-bucket.ws.126.net/2019/04/16/c3eb85c82e634905875f74fc218e3257.png","writer":"","source":"网易财经综合","source_url":"http://money.163.com/19/0416/17/ECTDN83N0025812C.html","reply_count":0,"click_count":0,"pub_time":1555408711},{"aid":"MTEzMTg5Njg","title":"五大行年赚超万亿 超过19个中石油、28个茅台","headpic":"http://cms-bucket.ws.126.net/2019/04/16/ab340c1fa6634551ac2a0ba7fb96af6d.jpeg","writer":"","source":"中国经济周刊","source_url":"http://money.163.com/19/0416/18/ECTFIDM200258105.html","reply_count":19,"click_count":0,"pub_time":1555410650},{"aid":"MTEzMTg5Njc","title":"股价创下历史新高 LV总裁成欧洲首富","headpic":"http://cms-bucket.ws.126.net/2019/04/16/1021ab4b6bba454c9e33dfd10ac0ee8b.png","writer":"","source":"经济观察网","source_url":"http://money.163.com/19/0416/11/ECSNDCSM002580S6.html","reply_count":0,"click_count":0,"pub_time":1555384920},{"aid":"MTEzMTg5NjE","title":"上交所明确科创板保荐机构跟投比例为2%-5%","headpic":"http://cms-bucket.ws.126.net/2019/04/11/c843a6233d2f473d81438288405ddeb5.png","writer":"","source":"上交所","source_url":"http://money.163.com/19/0416/17/ECTC158E002580S6.html","reply_count":0,"click_count":0,"pub_time":1555406938},{"aid":"MTEzMTg5NjA","title":"融创中国15.9亿股股票质押现已解除","headpic":"http://cms-bucket.ws.126.net/2019/03/29/42f5b810c3674365af9df3fa42f9f805.png","writer":"","source":"第一财经日报","source_url":"http://money.163.com/19/0416/17/ECTD15LA00258105.html","reply_count":12,"click_count":0,"pub_time":1555407987},{"aid":"MTEzMTg5NTk","title":"马云，请理直气壮地告诉大家：996是员工的福气！","headpic":"http://cms-bucket.ws.126.net/2019/04/16/ea4901aa080a4d9baa8c7dc427481b35.jpeg","writer":"","source":"网易研究局","source_url":"http://money.163.com/19/0416/18/ECTFBU1300258J1R.html","reply_count":0,"click_count":0,"pub_time":1555410437},{"aid":"MTEzMTg5NTg","title":"\"吴晓波频道\"去年掉粉56万 花40万买42万粉","headpic":"http://cms-bucket.ws.126.net/2019/04/16/c3b8c742b0c647e1b51986a08044b0a3.png","writer":"","source":"每日经济新闻","source_url":"http://money.163.com/19/0416/08/ECSBMIE100258152.html","reply_count":5,"click_count":0,"pub_time":1555373037},{"aid":"MTEzMTg5MzU","title":"国资委：第四批混改名单已初步确定 将超过100户","headpic":"http://cms-bucket.ws.126.net/2019/04/16/5f67ff34c14645498c2484c05ecbfdbe.png","writer":"","source":"中国网","source_url":"http://money.163.com/19/0416/13/ECSUTFJS002580S6.html","reply_count":0,"click_count":0,"pub_time":1555392342},{"aid":"MTEzMTg5MzQ","title":"国务院：确保2022年人人享有养老服务","headpic":"http://cms-bucket.ws.126.net/2019/04/16/eccdc1fe26084a4a8ee53a6366c309f6.png","writer":"","source":"中国政府网","source_url":"http://money.163.com/19/0416/16/ECT72S4Q00258105.html","reply_count":0,"click_count":0,"pub_time":1555401751}],"page":1,"psize":10,"count":10,"total":1444}
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
         * list : [{"aid":"MTEzMTg5NzA","title":"上交所召开第一届科创板股票上市委员会成立大会","headpic":"http://cms-bucket.ws.126.net/2019/03/13/bf641a7d346a4f7b85c74b04d008091e.png","writer":"","source":"上交所","source_url":"http://money.163.com/19/0416/17/ECTAFRHK002580S6.html","reply_count":0,"click_count":0,"pub_time":1555405323},{"aid":"MTEzMTg5Njk","title":"尚德机构因虚假宣传被处罚 2018年净收入19.7亿","headpic":"http://cms-bucket.ws.126.net/2019/04/16/c3eb85c82e634905875f74fc218e3257.png","writer":"","source":"网易财经综合","source_url":"http://money.163.com/19/0416/17/ECTDN83N0025812C.html","reply_count":0,"click_count":0,"pub_time":1555408711},{"aid":"MTEzMTg5Njg","title":"五大行年赚超万亿 超过19个中石油、28个茅台","headpic":"http://cms-bucket.ws.126.net/2019/04/16/ab340c1fa6634551ac2a0ba7fb96af6d.jpeg","writer":"","source":"中国经济周刊","source_url":"http://money.163.com/19/0416/18/ECTFIDM200258105.html","reply_count":19,"click_count":0,"pub_time":1555410650},{"aid":"MTEzMTg5Njc","title":"股价创下历史新高 LV总裁成欧洲首富","headpic":"http://cms-bucket.ws.126.net/2019/04/16/1021ab4b6bba454c9e33dfd10ac0ee8b.png","writer":"","source":"经济观察网","source_url":"http://money.163.com/19/0416/11/ECSNDCSM002580S6.html","reply_count":0,"click_count":0,"pub_time":1555384920},{"aid":"MTEzMTg5NjE","title":"上交所明确科创板保荐机构跟投比例为2%-5%","headpic":"http://cms-bucket.ws.126.net/2019/04/11/c843a6233d2f473d81438288405ddeb5.png","writer":"","source":"上交所","source_url":"http://money.163.com/19/0416/17/ECTC158E002580S6.html","reply_count":0,"click_count":0,"pub_time":1555406938},{"aid":"MTEzMTg5NjA","title":"融创中国15.9亿股股票质押现已解除","headpic":"http://cms-bucket.ws.126.net/2019/03/29/42f5b810c3674365af9df3fa42f9f805.png","writer":"","source":"第一财经日报","source_url":"http://money.163.com/19/0416/17/ECTD15LA00258105.html","reply_count":12,"click_count":0,"pub_time":1555407987},{"aid":"MTEzMTg5NTk","title":"马云，请理直气壮地告诉大家：996是员工的福气！","headpic":"http://cms-bucket.ws.126.net/2019/04/16/ea4901aa080a4d9baa8c7dc427481b35.jpeg","writer":"","source":"网易研究局","source_url":"http://money.163.com/19/0416/18/ECTFBU1300258J1R.html","reply_count":0,"click_count":0,"pub_time":1555410437},{"aid":"MTEzMTg5NTg","title":"\"吴晓波频道\"去年掉粉56万 花40万买42万粉","headpic":"http://cms-bucket.ws.126.net/2019/04/16/c3b8c742b0c647e1b51986a08044b0a3.png","writer":"","source":"每日经济新闻","source_url":"http://money.163.com/19/0416/08/ECSBMIE100258152.html","reply_count":5,"click_count":0,"pub_time":1555373037},{"aid":"MTEzMTg5MzU","title":"国资委：第四批混改名单已初步确定 将超过100户","headpic":"http://cms-bucket.ws.126.net/2019/04/16/5f67ff34c14645498c2484c05ecbfdbe.png","writer":"","source":"中国网","source_url":"http://money.163.com/19/0416/13/ECSUTFJS002580S6.html","reply_count":0,"click_count":0,"pub_time":1555392342},{"aid":"MTEzMTg5MzQ","title":"国务院：确保2022年人人享有养老服务","headpic":"http://cms-bucket.ws.126.net/2019/04/16/eccdc1fe26084a4a8ee53a6366c309f6.png","writer":"","source":"中国政府网","source_url":"http://money.163.com/19/0416/16/ECT72S4Q00258105.html","reply_count":0,"click_count":0,"pub_time":1555401751}]
         * page : 1
         * psize : 10
         * count : 10
         * total : 1444
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
             * aid : MTEzMTg5NzA
             * title : 上交所召开第一届科创板股票上市委员会成立大会
             * headpic : http://cms-bucket.ws.126.net/2019/03/13/bf641a7d346a4f7b85c74b04d008091e.png
             * writer :
             * source : 上交所
             * source_url : http://money.163.com/19/0416/17/ECTAFRHK002580S6.html
             * reply_count : 0
             * click_count : 0
             * pub_time : 1555405323
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
        }
    }
}
