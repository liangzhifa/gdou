package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.ScoreSheet;
import com.zhifa.gdou.utils.ScoreTempEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ScoreSheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreSheet record);

    int insertSelective(ScoreSheet record);

    ScoreSheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreSheet record);

    int updateByPrimaryKey(ScoreSheet record);

    List<String> findStuNumsByDate(@Param("testTime") Date testTime);

    List<ScoreSheet>findByTestTime(@Param("testTime") Date testTime);

    Date getNewTestDate();

    /*
    * 老师下面的学生，按照考试时间，学生，分组查询,xianzhi限制条件是该老师班的学生
    * */
    List<ScoreTempEntity>getGroupByTestTime(@Param("testTime") Date testTime, @Param("stuNums") List<String> stuNums);

    List<ScoreSheet> findByNumAndTime(@Param("studentnum") String studentnum, @Param("testTime") Date testTime);

    int updateScoreByNumAndCorseAndTestTime(@Param("testTime") Date testTime, @Param("studentNum") String studentNum, @Param("course") String course, @Param("score") Integer score);
}