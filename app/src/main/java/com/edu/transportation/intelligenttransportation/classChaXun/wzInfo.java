package com.edu.transportation.intelligenttransportation.classChaXun;

import java.util.List;

public class wzInfo {

    private List<WzjlBean> wzjl;

    public List<WzjlBean> getWzjl() {
        return wzjl;
    }

    public void setWzjl(List<WzjlBean> wzjl) {
        this.wzjl = wzjl;
    }

    public static class WzjlBean {
        /**
         * cphm : 京A123456
         * list : [{"wfsj":"2019-02-03","wfdd":"北京市西城区小翔凤胡同7号，中国民生银行(德胜门支行)附近","wfxw":"机动车不按规定在相应车道行驶"},{"wfsj":"2019-03-15","wfdd":"北京市西城区小翔凤胡同7号，中国民生银行(德胜门支行)附近","wfxw":"机动车不按规定在相应车道行驶"}]
         */

        private String cphm;
        private List<ListBean> list;

        public String getCphm() {
            return cphm;
        }

        public void setCphm(String cphm) {
            this.cphm = cphm;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * wfsj : 2019-02-03
             * wfdd : 北京市西城区小翔凤胡同7号，中国民生银行(德胜门支行)附近
             * wfxw : 机动车不按规定在相应车道行驶
             */

            private String wfsj;
            private String wfdd;
            private String wfxw;

            public String getWfsj() {
                return wfsj;
            }

            public void setWfsj(String wfsj) {
                this.wfsj = wfsj;
            }

            public String getWfdd() {
                return wfdd;
            }

            public void setWfdd(String wfdd) {
                this.wfdd = wfdd;
            }

            public String getWfxw() {
                return wfxw;
            }

            public void setWfxw(String wfxw) {
                this.wfxw = wfxw;
            }
        }
    }
}
