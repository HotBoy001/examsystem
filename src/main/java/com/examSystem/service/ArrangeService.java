package com.examSystem.service;

import com.examSystem.dao.ArrangeMapper;
import com.examSystem.entity.Answer;
import com.examSystem.entity.Arrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArrangeService {

    private final ArrangeMapper arrangeMapper;

    @Autowired
    public ArrangeService(ArrangeMapper arrangeMapper) {
        this.arrangeMapper = arrangeMapper;
    }

    public List<Arrange> getArrangeBySchool(String schoolId) {
        return arrangeMapper.selectBySchoolId(schoolId);
    }

    public Arrange getArrange(int arrId) {
        return arrangeMapper.selectByPrimaryKey(arrId);
    }

    //添加学生,只添加arrange表
    public int addarrange(Arrange arrange) {
        return arrangeMapper.insert(arrange);
    }
    //带批改试卷显示Arrange名字
    public List<Arrange> getArrangeName(List<Answer> answers){
        List<Arrange> list=new ArrayList<>();
        for(Answer answerList:answers){
            list.add(arrangeMapper.selectByPrimaryKey(answerList.getArrId()));
        }
        return list;
    }
}
