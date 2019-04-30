package com.zhifa.gdou.resultEntity;

import java.io.Serializable;

public class RankingDTO implements Serializable {
    private String ranking;
    private String studentNum;
    private String total;


    public RankingDTO() {
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RankingDTO{" +
                "ranking='" + ranking + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
