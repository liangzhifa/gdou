package com.zhifa.gdou.resultEntity;

public class ScoreVo {
    private String name;

    private Double percent;
    private String a;

    public ScoreVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public ScoreVo(String name, Double percent) {
        this.name = name;
        this.a = "1";
        this.percent = percent;
    }
}
