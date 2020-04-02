package com.examSystem.service;

import cn.hutool.core.util.ArrayUtil;
import com.examSystem.dao.ShortAnswerMapper;
import com.examSystem.entity.Dshortanswer;
import com.examSystem.entity.ShortAnswer;
import com.examSystem.entity.TrueFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ShortAnswerService {
    private final Integer sum=2;
    //难度正常，简单简答题目数
    private final int normal = 1;
    //难度困难，简单简答表目数
    private final int difficulty =0;
    @Autowired
    DshortanswerService dshortanswerService;

    private final ShortAnswerMapper shortAnswerMapper;

    @Autowired
    public ShortAnswerService(ShortAnswerMapper shortAnswerMapper) {
        this.shortAnswerMapper = shortAnswerMapper;
    }

    public List<ShortAnswer> getShortAnswerList(String[] shortAnswers){

        List<ShortAnswer> trueFalseList = new ArrayList<>();

        for (String shortAnswerId : shortAnswers){
            trueFalseList.add(shortAnswerMapper.selectByPrimaryKey(Integer.parseInt(shortAnswerId)));
        }

        return trueFalseList;
    }
    //添加试卷，简答题部分
    public String addTestShort(Integer grade){
        if (grade==1){
            int i=0;
            String[] Short=new String[sum];//存放简答题编号
            List<Integer> listShort = new ArrayList<Integer>();//存放简答题编号，控制题号不同
            List<Integer> listShortDif = new ArrayList<Integer>();//存放困难简答题编号，控制题号不同
            Random r3 = new Random();
            while (listShort.size() != sum) {
                int num = r3.nextInt(shortAnswerMapper.selectAll().size()) + 1;
                if (!listShort.contains(num)) {
                    Short[i] = Integer.toString(num);
                    i++;
                    listShort.add(num);
                }
            }
            String joinShort = ArrayUtil.join(Short, "/");
            return joinShort;
        }else if (grade==2){
            int i=0;
            String[] Short=new String[sum];//存放简答题编号
            List<Integer> listShort = new ArrayList<Integer>();//存放简答题编号，控制题号不同
            List<Integer> listShortDif = new ArrayList<Integer>();//存放困难简答题编号，控制题号不同
            Random r3 = new Random();
            while (listShort.size() != normal ) {
                int num = r3.nextInt(shortAnswerMapper.selectAll().size()) + 1;
                if (!listShort.contains(num)) {
                    Short[i] = Integer.toString(num);
                    i++;
                    listShort.add(num);
                }
            }
            while (listShortDif.size() != sum-normal ) {
                int num = r3.nextInt(dshortanswerService.selectAll().size()) + 1;
                if (!listShortDif.contains(num)) {
                    Short[i] = Integer.toString(num);
                    i++;
                    listShortDif.add(num);
                }
            }
            String joinShort = ArrayUtil.join(Short, "/");
            return joinShort;
        }else{
            int i=0;
            String[] Short=new String[sum];//存放简答题编号
            List<Integer> listShort = new ArrayList<Integer>();//存放简答题编号，控制题号不同
            List<Integer> listShortDif = new ArrayList<Integer>();//存放困难简答题编号，控制题号不同
            Random r3 = new Random();
            while (listShort.size() != difficulty ) {
                int num = r3.nextInt(shortAnswerMapper.selectAll().size()) + 1;
                if (!listShort.contains(num)) {
                    Short[i] = Integer.toString(num);
                    i++;
                    listShort.add(num);
                }
            }
            while (listShortDif.size() != sum-difficulty ) {
                int num = r3.nextInt(dshortanswerService.selectAll().size()) + 1;
                if (!listShortDif.contains(num)) {
                    Short[i] = Integer.toString(num);
                    i++;
                    listShortDif.add(num);
                }
            }
            String joinShort = ArrayUtil.join(Short, "/");
            return joinShort;
        }

     }
    //组装试卷，用于显示，简答题部分,参数为简答题编号；
    public List<ShortAnswer> makeShortAnswer(String Short,Integer grade){
        List<ShortAnswer> shortList=new ArrayList<>();
        String[] c=Short.split("/");
        int[] ShortNo=new int[c.length];
        if(grade==1){
            for (int i=0;i<c.length;i++) {
                ShortNo[i] = Integer.parseInt(c[i]);
            }
            for (int k=0;k<c.length;k++){
                shortList.add(shortAnswerMapper.selectByPrimaryKey(ShortNo[k]));
            }
            return shortList;
        }else if (grade==2){
            for (int i=0;i<c.length;i++) {
                ShortNo[i] = Integer.parseInt(c[i]);
            }
            for (int k=0;k<normal;k++){
                shortList.add(shortAnswerMapper.selectByPrimaryKey(ShortNo[k]));
            }
            for (int k=normal;k<sum;k++){
                Dshortanswer dshortanswer=dshortanswerService.selectByPrimaryKey(ShortNo[k]);
                ShortAnswer shortAnswer=new ShortAnswer();
                shortAnswer.setSaqId(dshortanswer.getDsId());
                shortAnswer.setSaqContent(dshortanswer.getDsContent());
                shortAnswer.setSaqCorrect(dshortanswer.getDsCorrect());
                shortList.add(shortAnswer);
            }
            return shortList;
        }else{
            for (int i=0;i<c.length;i++) {
                ShortNo[i] = Integer.parseInt(c[i]);
            }
            for (int k=0;k<difficulty;k++){
                shortList.add(shortAnswerMapper.selectByPrimaryKey(ShortNo[k]));
            }
            for (int k=difficulty;k<sum;k++){
                Dshortanswer dshortanswer=dshortanswerService.selectByPrimaryKey(ShortNo[k]);
                ShortAnswer shortAnswer=new ShortAnswer();
                shortAnswer.setSaqId(dshortanswer.getDsId());
                shortAnswer.setSaqContent(dshortanswer.getDsContent());
                shortAnswer.setSaqCorrect(dshortanswer.getDsCorrect());
                shortList.add(shortAnswer);
            }
            return shortList;
        }

    }
}
