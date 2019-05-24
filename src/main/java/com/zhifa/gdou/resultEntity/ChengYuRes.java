package com.zhifa.gdou.resultEntity;

import java.util.List;

public class ChengYuRes {

    /**
     * @Author: zhifa
     * @Date: 2019/5/23
     * @Description:
     *      error_code	int	返回码
     *  	reason	string	返回说明
     *  	result	string	返回结果集
     *  	bushou	string	首字部首
     *  	head	string	成语词头
     *  	pinyin	string	拼音
     *  	chengyujs	string	成语解释
     *  	from_	stirng	成语出处
     *  	example	string	举例
     *  	yufa	string	语法
     *  	ciyujs	string	词语解释
     *  	yinzhengjs	string	引证解释
     *  	tongyi	list	同义词
     *  	fanyi	list	反义词
     */

    /**
     * result : {"head":"杯","chengyujs":" 用一杯水去救一车着了火的柴草。比喻力量太小，解决不了问题。","pinyin":"bēi shuǐ chē xīn","tongyi":["人浮于事","粥少僧多","无济于事"],"from_":" 《孟子·告子上》：\u201c今之为人者，犹以一杯水救一车薪之火也。\u201d","yinzhengjs":"《孟子·告子上》：\u201c今之为仁者，犹以一杯水救一车薪之火也。\u201d后因以\u201c杯水车薪\u201d比喻力量微小，无济于事。 清 史致谔 《同治二年二月廿三日禀左宗棠》：\u201c其海关常税月只二三千金不等，本属杯水车薪，无益於用。\u201d 沙汀 《代理县长》：\u201c一天平均拿十五个人计算吧！一个人五角，五得五，五五二块五。老科长叹息道：\u2018杯水车薪呵！\u2019\u201d亦作\u201c 杯水舆薪 \u201d。 宋 曹辅 《唐颜文忠公新庙记》：\u201c杯水舆薪，势且莫抗。\u201d","fanyi":["集腋成裘","绰绰有余"],"ciyujs":"[a cup of water can't put out the fire on a carload of wood;try to put out a burning cartload of faggots with a cup of water\u2014an utterly inadequate measure] 用一杯水去扑灭一车燃烧的柴草。比喻力量太小,无济于事","bushou":"木","example":" 有新债未动毫分的，除了承许夏鼎三十两外，大有～之状。 清·李绿园《歧路灯》第七十四回","yufa":" 复句式；作谓语、宾语、分句；表示力量微小不能解决问题"}
     * reason : success
     * error_code : 0
     */
    private ResultEntity result;
    private String reason;
    private int error_code;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public class ResultEntity {
        /**
         * head : 杯
         * chengyujs :  用一杯水去救一车着了火的柴草。比喻力量太小，解决不了问题。
         * pinyin : bēi shuǐ chē xīn
         * tongyi : ["人浮于事","粥少僧多","无济于事"]
         * from_ :  《孟子·告子上》：“今之为人者，犹以一杯水救一车薪之火也。”
         * yinzhengjs : 《孟子·告子上》：“今之为仁者，犹以一杯水救一车薪之火也。”后因以“杯水车薪”比喻力量微小，无济于事。 清 史致谔 《同治二年二月廿三日禀左宗棠》：“其海关常税月只二三千金不等，本属杯水车薪，无益於用。” 沙汀 《代理县长》：“一天平均拿十五个人计算吧！一个人五角，五得五，五五二块五。老科长叹息道：‘杯水车薪呵！’”亦作“ 杯水舆薪 ”。 宋 曹辅 《唐颜文忠公新庙记》：“杯水舆薪，势且莫抗。”
         * fanyi : ["集腋成裘","绰绰有余"]
         * ciyujs : [a cup of water can't put out the fire on a carload of wood;try to put out a burning cartload of faggots with a cup of water—an utterly inadequate measure] 用一杯水去扑灭一车燃烧的柴草。比喻力量太小,无济于事
         * bushou : 木
         * example :  有新债未动毫分的，除了承许夏鼎三十两外，大有～之状。 清·李绿园《歧路灯》第七十四回
         * yufa :  复句式；作谓语、宾语、分句；表示力量微小不能解决问题
         */
        private String head;
        private String chengyujs;
        private String pinyin;
        private List<String> tongyi;
        private String from_;
        private String yinzhengjs;
        private List<String> fanyi;
        private String ciyujs;
        private String bushou;
        private String example;
        private String yufa;

        public void setHead(String head) {
            this.head = head;
        }

        public void setChengyujs(String chengyujs) {
            this.chengyujs = chengyujs;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public void setTongyi(List<String> tongyi) {
            this.tongyi = tongyi;
        }

        public void setFrom_(String from_) {
            this.from_ = from_;
        }

        public void setYinzhengjs(String yinzhengjs) {
            this.yinzhengjs = yinzhengjs;
        }

        public void setFanyi(List<String> fanyi) {
            this.fanyi = fanyi;
        }

        public void setCiyujs(String ciyujs) {
            this.ciyujs = ciyujs;
        }

        public void setBushou(String bushou) {
            this.bushou = bushou;
        }

        public void setExample(String example) {
            this.example = example;
        }

        public void setYufa(String yufa) {
            this.yufa = yufa;
        }

        public String getHead() {
            return head;
        }

        public String getChengyujs() {
            return chengyujs;
        }

        public String getPinyin() {
            return pinyin;
        }

        public List<String> getTongyi() {
            return tongyi;
        }

        public String getFrom_() {
            return from_;
        }

        public String getYinzhengjs() {
            return yinzhengjs;
        }

        public List<String> getFanyi() {
            return fanyi;
        }

        public String getCiyujs() {
            return ciyujs;
        }

        public String getBushou() {
            return bushou;
        }

        public String getExample() {
            return example;
        }

        public String getYufa() {
            return yufa;
        }
    }
}
