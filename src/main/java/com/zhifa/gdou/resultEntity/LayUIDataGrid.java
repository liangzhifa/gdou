package com.zhifa.gdou.resultEntity;

import com.zhifa.gdou.model.Teacher;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LayUIDataGrid implements Serializable {

    /**
     * code : 0
     * msg :
     * count : 1000
     * data : [null,"\u2026"]
     */

    private int code;
    private String msg;
    private Long count;
    private List data;

    public LayUIDataGrid() {
    }

    public LayUIDataGrid(long count, List data) {
        this.count = count;
        this.data = data;
        this.code=0;
        this.msg="请求成功！";
    }
}
