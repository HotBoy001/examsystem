package com.examSystem.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.examSystem.dao.ChoiceMapper;
import com.examSystem.dao.DchoiceMapper;
import com.examSystem.entity.Choice;
import com.examSystem.entity.Dchoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ChoiceService {
    private final Integer sum=15;
    //难度正常，简单表选择题目数
    private final Integer normal=10;
    //难度困难，简单表选择题目数
    private final Integer difficulty=5;

    private final ChoiceMapper choiceMapper;
    @Autowired
    DchoiceService dchoiceService;
    @Autowired
    public ChoiceService(ChoiceMapper choiceMapper) {
        this.choiceMapper = choiceMapper;
    }

    public List<Choice> getChoiceList(String[] choices){

        List<Choice> choiceList = new ArrayList<>();

        for(String choiceId : choices){
            choiceList.add(choiceMapper.selectByPrimaryKey(Integer.parseInt(choiceId)));
        }

        return choiceList;
    }
    //添加试卷得到选择题部分-----之难度简单
    public String addTestChoice(Integer grade) {
        if (grade==1){
            int i=0;
            String[] choice=new String[sum];//存放题编号
            List<Integer> listchoice = new ArrayList<Integer>();//存放选择题编号，控制题号不同
            //2.创建Random对象
            Random r = new Random();
            //循环将得到的随机数进行判断，如果随机数不存在于集合中，则将随机数放入集合中，如果存在，则将随机数丢弃不做操作，进行下一次循环，直到集合长度等于nums
            while (listchoice.size() != sum) {
                int num = r.nextInt(choiceMapper.selectAll().size()) + 1;
                if (!listchoice.contains(num)) {
                    choice[i] = Integer.toString(num);
                    i++;
                    listchoice.add(num);
                }
            }
            String joinchoice = ArrayUtil.join(choice, "/");
            System.out.println("选择题编号:"+joinchoice);
            return joinchoice;

        } else if(grade==2)
        {
            int i=0;
            String[] choice=new String[sum];//存放题编号
            List<Integer> listchoice = new ArrayList<Integer>();//存放选择题编号，控制题号不同
            List<Integer> listchoiceDifferent = new ArrayList<Integer>();//存放困难选择题编号，控制题号不同
            //2.创建Random对象
            Random r = new Random();
            //循环将得到的随机数进行判断，如果随机数不存在于集合中，则将随机数放入集合中，如果存在，则将随机数丢弃不做操作，进行下一次循环，直到集合长度等于nums
            //控制简单选择题题目----难度正常简单题目数为十道
            while (listchoice.size() != normal) {
                int num = r.nextInt(choiceMapper.selectAll().size()) + 1;
                if (!listchoice.contains(num)) {
                    choice[i] = Integer.toString(num);
                    i++;
                    listchoice.add(num);
                }
            }
            //控制困难选择题题目----难度正常困难题目数为五道
            while (listchoiceDifferent.size()!=(sum-normal)) {
                int num = r.nextInt(dchoiceService.selectAll().size()) + 1;
                if (!listchoiceDifferent.contains(num)) {
                    choice[i] = Integer.toString(num);
                    i++;
                    listchoiceDifferent.add(num);
                }
            }
            String joinchoice = ArrayUtil.join(choice, "/");
            System.out.println("选择题编号:"+joinchoice);
            return joinchoice;

        }else {
            int i=0;
            String[] choice=new String[sum];//存放题编号
            List<Integer> listchoice = new ArrayList<Integer>();//存放选择题编号，控制题号不同
            List<Integer> listchoiceDifferent = new ArrayList<Integer>();//存放困难选择题编号，控制题号不同
            //2.创建Random对象
            Random r = new Random();
            //循环将得到的随机数进行判断，如果随机数不存在于集合中，则将随机数放入集合中，如果存在，则将随机数丢弃不做操作，进行下一次循环，直到集合长度等于nums
            //控制简单选择题题目----难度困难简单题目数为五道
            while (listchoice.size() != difficulty) {
                int num = r.nextInt(choiceMapper.selectAll().size()) + 1;
                if (!listchoice.contains(num)) {
                    choice[i] = Integer.toString(num);
                    i++;
                    listchoice.add(num);
                }
            }
            //控制困难选择题题目----难度困难困难题目数为十道
            while (listchoiceDifferent.size()!=(sum-difficulty)) {
                int num = r.nextInt(dchoiceService.selectAll().size()) + 1;
                if (!listchoiceDifferent.contains(num)) {
                    choice[i] = Integer.toString(num);
                    i++;
                    listchoiceDifferent.add(num);
                }
            }
            String joinchoice = ArrayUtil.join(choice, "/");
            System.out.println("选择题编号:"+joinchoice);
            return joinchoice;
        }


    }
    //组装试卷，用于显示，选择题部分,参数为选择题编号；
    public List<Choice> makeChoice(String choice,Integer grade){
        List<Choice> list=new ArrayList<>();
        String[] a=choice.split("/");
        int[] choiceNo=new int[a.length];
        if(grade==1){
            //获取简单题目编号
            for (int i=0;i<sum;i++) {
                choiceNo[i] = Integer.parseInt(a[i]);
            }
            //通过简单题目编号查询简单表数据，并存到list集合中
            for (int k=0;k<sum;k++){
                list.add(choiceMapper.selectByPrimaryKey(choiceNo[k]));
            }
            return list;
        }else if(grade==2){
            //获取题目编号
            for (int i=0;i<sum;i++) {
                choiceNo[i] = Integer.parseInt(a[i]);
            }
            //通过简单题目编号查询简单表数据，并存到list集合中
            for (int k=0;k<normal;k++){
                list.add(choiceMapper.selectByPrimaryKey(choiceNo[k]));
            }
            //通过困难题目编号查询困难表数据，并存到list集合中,为了list使用方便，结构相同：将dchoice的值存到choice里
            for (int k=normal;k<sum;k++){
                Dchoice dchoice=dchoiceService.selectByPrimaryKey(choiceNo[k]);
                Choice choice1=new Choice();
                choice1.setCqId(dchoice.getDcId());
                choice1.setCqContent(dchoice.getDcContent());
                choice1.setCqA(dchoice.getDcA());
                choice1.setCqB(dchoice.getDcB());
                choice1.setCqC(dchoice.getDcC());
                choice1.setCqD(dchoice.getDcD());
                choice1.setCqCorrect(dchoice.getDcCorrect());
                list.add(choice1);
            }
            return list;
        }else {
            //获取简单题目编号
            for (int i=0;i<difficulty;i++) {
                choiceNo[i] = Integer.parseInt(a[i]);
            }
            //通过简单题目编号查询简单表数据，并存到list集合中
            for (int k=0;k<difficulty;k++){
                list.add(choiceMapper.selectByPrimaryKey(choiceNo[k]));
            }
            //获取困难题目编号
            for (int i=difficulty;i<sum;i++) {
                choiceNo[i] = Integer.parseInt(a[i]);
            }
            //通过困难题目编号查询困难表数据，并存到list集合中,为了list使用方便，结构相同：将dchoice的值存到choice里
            for (int k=difficulty;k<sum;k++){
                Dchoice dchoice=dchoiceService.selectByPrimaryKey(choiceNo[k]);
                Choice choice1=new Choice();
                choice1.setCqId(dchoice.getDcId());
                choice1.setCqContent(dchoice.getDcContent());
                choice1.setCqA(dchoice.getDcA());
                choice1.setCqB(dchoice.getDcB());
                choice1.setCqC(dchoice.getDcC());
                choice1.setCqD(dchoice.getDcD());
                choice1.setCqCorrect(dchoice.getDcCorrect());
                list.add(choice1);
            }
            return list;
        }

    }
}
