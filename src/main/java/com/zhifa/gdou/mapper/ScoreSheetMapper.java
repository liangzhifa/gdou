package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.ScoreSheet;
import com.zhifa.gdou.resultEntity.RankingDTO;
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

    /**
     * genju根据学号和日期查询成绩
     * @param studentnum
     * @param testTime
     * @return
     */

    List<ScoreSheet> findByNumAndTime(@Param("studentnum") String studentnum, @Param("testTime") Date testTime);

    int updateScoreByNumAndCorseAndTestTime(@Param("testTime") Date testTime, @Param("studentNum") String studentNum, @Param("course") String course, @Param("score") Integer score);

    /**
     * 查询该学生下面所有的考试日期
     * @param studentnum
     * @return
     */
    List<String>findTestDate(@Param("studentnum") String studentnum);

    List<Date>findTestDateFormat(@Param("studentnum") String studentnum);
    /**
     * genju根据学号和日期查询成绩
     * @param studentnum
     * @param testTime
     * @return
     */

    List<ScoreSheet>getScoreByDate(@Param("studentnum") String studentnum, @Param("testTime") Date  testTime);

    /**
     * 获取该学生最近一次考试
     * @param studentnum
     * @return
     */
    Date getNearOneTime(@Param("studentnum") String studentnum);

    List<Date>findAllTestDate();

    /*获取所有课程*/
    List<String> getCourseAll(@Param("studentnum") String studentnum);
    String getCourseAllLimit1(@Param("studentnum") String studentnum);


    List<ScoreSheet>getScoreByCourse(@Param("studentnum") String studentnum,@Param("course") String course);

    /**
     * 某次考试中 得到全班人的总分排名
     * @param studentnum
     * @param testTime
     * @return
     */

    List<RankingDTO> getRanking(@Param("studentnum") String studentnum, @Param("testTime") Date  testTime);

}