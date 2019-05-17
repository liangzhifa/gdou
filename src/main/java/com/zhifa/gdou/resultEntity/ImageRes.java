package com.zhifa.gdou.resultEntity;

import java.io.Serializable;
import java.util.List;

public class ImageRes implements Serializable {

    /**
     * result : [{"score":0.993642,"root":"商品-农用物资","keyword":"花卉"},{"score":0.717173,"root":"植物-花","keyword":"矮牵牛"},{"score":0.410576,"root":"植物-花","keyword":"粉色牵牛花"},{"score":0.204574,"root":"植物-花","keyword":"牵牛花"},{"score":0.005164,"root":"植物-藜芦科","keyword":"棋盘花"}]
     * log_id : 7051531077509683311
     * result_num : 5
     */
    private List<ResultEntity> result;
    private long log_id;
    private int result_num;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public long getLog_id() {
        return log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public class ResultEntity {
        /**
         * score : 0.993642
         * root : 商品-农用物资
         * keyword : 花卉
         */
        private double score;
        private String root;
        private String keyword;

        public void setScore(double score) {
            this.score = score;
        }

        public void setRoot(String root) {
            this.root = root;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public double getScore() {
            return score;
        }

        public String getRoot() {
            return root;
        }

        public String getKeyword() {
            return keyword;
        }

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "score=" + score +
                    ", root='" + root + '\'' +
                    ", keyword='" + keyword + '\'' +
                    '}';
        }
    }
}
