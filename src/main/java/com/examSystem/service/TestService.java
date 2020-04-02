package com.examSystem.service;

import com.examSystem.dao.ArrangeMapper;
import com.examSystem.dao.TestMapper;
import com.examSystem.entity.Answer;
import com.examSystem.entity.School;
import com.examSystem.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private final TestMapper testMapper;
    @Autowired
    ArrangeMapper arrangeMapper;

    @Autowired
    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public Test getTest(int testId){
        return testMapper.selectByPrimaryKey(testId);
    }
    //添加试卷
    public int inserttest(String testname, String Choice, String TrueFalse, String Short,Integer grade){
    Test test=new Test();
    test.setTestName(testname);
    test.setCqId(Choice);
    test.setTfqId(TrueFalse);
    test.setSaqId(Short);
    test.setGrade(grade);
    return testMapper.insert(test);
    }
    //查询所有试卷
    public List<Test> getAllTest(){
        return testMapper.selectAll();
    }
    //删除试卷
    public int deltest(int testId){
        arrangeMapper.deleteByPrimaryKeyTest(testId);
        return  testMapper.deleteByPrimaryKey(testId);
    }

    public List<Test> selectAll() {
        return testMapper.selectAll();
    }
    public List<Test> getTestName(List<Answer> answers){
        List<Test> list=new ArrayList<>();
        for(Answer answer:answers){
            list.add(testMapper.selectByPrimaryKey(answer.getTestId()));
        }
        return list;
    }
}
