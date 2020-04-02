package com.examSystem.service;

import cn.hutool.core.util.ArrayUtil;
import com.examSystem.dao.TrueFalseMapper;
import com.examSystem.entity.Dtruefalse;
import com.examSystem.entity.TrueFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class TrueFalseService {
    private final Integer sum=15;
    //难度正常，简单表选择题目数
    private final int normal = 10;
    //难度困难，简单表选择题目数
    private final int difficulty = 5;
    private final TrueFalseMapper trueFalseMapper;
    @Autowired
    DtruefalseService dtruefalseService;
    @Autowired
    public TrueFalseService(TrueFalseMapper trueFalseMapper) {
        this.trueFalseMapper = trueFalseMapper;
    }

    public List<TrueFalse> getTrueFalseList(String[] trueFalses){

        List<TrueFalse> trueFalseList = new ArrayList<>();

        for (String trueFalseId : trueFalses){
            trueFalseList.add(trueFalseMapper.selectByPrimaryKey(Integer.parseInt(trueFalseId)));
        }

        return trueFalseList;
    }
    //添加试卷，判断题部分
    public String addTestTrueFalse(Integer grade){
        int i=0;
        String[] turefalse=new String[sum];//存放判断题编号
        List<Integer> listtrfa = new ArrayList<Integer>();//存放判断题编号，控制题号不同
        List<Integer> listtrfaDif = new ArrayList<Integer>();//存放困难判断题编号，控制题号不同
        if(grade==1){
            //2.创建Random对象
            Random r2 = new Random();
            //循环将得到的随机数进行判断，如果随机数不存在于集合中，则将随机数放入集合中，如果存在，则将随机数丢弃不做操作，进行下一次循环，直到集合长度等于nums
            while (listtrfa.size() != sum) {
                int num = r2.nextInt(trueFalseMapper.selectAll().size()) + 1;
                if (!listtrfa.contains(num)) {
                    turefalse[i] = Integer.toString(num);
                    i++;
                    listtrfa.add(num);
                }
            }
            String jointurefalse = ArrayUtil.join(turefalse, "/");
            System.out.println("判断题编号:"+jointurefalse);
            return jointurefalse;
        }else if(grade==2){
            //2.创建Random对象
            Random r2 = new Random();
            //循环将得到的随机数进行判断，如果随机数不存在于集合中，则将随机数放入集合中，如果存在，则将随机数丢弃不做操作，进行下一次循环，直到集合长度等于nums
            while (listtrfa.size() !=normal) {
                int num = r2.nextInt(trueFalseMapper.selectAll().size()) + 1;
                if (!listtrfa.contains(num)) {
                    turefalse[i] = Integer.toString(num);
                    i++;
                    listtrfa.add(num);
                }
            }
            //获取困难选择题题目
            while (listtrfaDif.size() !=sum-normal ) {
                int num = r2.nextInt(dtruefalseService.selectAll().size()) + 1;
                if (!listtrfaDif.contains(num)) {
                    turefalse[i] = Integer.toString(num);
                    i++;
                    listtrfaDif.add(num);
                }
            }
            String jointurefalse = ArrayUtil.join(turefalse, "/");
            System.out.println("判断题编号:"+jointurefalse);
            return jointurefalse;
        }else{
            //2.创建Random对象
            Random r2 = new Random();
            //循环将得到的随机数进行判断，如果随机数不存在于集合中，则将随机数放入集合中，如果存在，则将随机数丢弃不做操作，进行下一次循环，直到集合长度等于nums
            while (listtrfa.size() !=difficulty) {
                int num = r2.nextInt(trueFalseMapper.selectAll().size()) + 1;
                if (!listtrfa.contains(num)) {
                    turefalse[i] = Integer.toString(num);
                    i++;
                    listtrfa.add(num);
                }
            }
            //获取困难选择题题目
            while (listtrfaDif.size() !=sum-difficulty ) {
                int num = r2.nextInt(dtruefalseService.selectAll().size()) + 1;
                if (!listtrfaDif.contains(num)) {
                    turefalse[i] = Integer.toString(num);
                    i++;
                    listtrfaDif.add(num);
                }
            }
            String jointurefalse = ArrayUtil.join(turefalse, "/");
            System.out.println("判断题编号:"+jointurefalse);
            return jointurefalse;
        }

    }
    //组装试卷，用于显示，判断题部分,参数为判断题编号；
    public List<TrueFalse> makeTrueFalse(String truefalse,Integer grade){
        if(grade==1){
            List<TrueFalse> tfList=new ArrayList<>();
            String[] b=truefalse.split("/");
            int[] truefalseNo=new int[b.length];
            for (int i=0;i<b.length;i++) {
                truefalseNo[i] = Integer.parseInt(b[i]);
            }
            for (int k=0;k<b.length;k++){
                tfList.add(trueFalseMapper.selectByPrimaryKey(truefalseNo[k]));
            }
            return tfList;
        }else if(grade==2){
            List<TrueFalse> tfList=new ArrayList<>();
            String[] b=truefalse.split("/");
            int[] truefalseNo=new int[b.length];
            for (int i=0;i<b.length;i++) {
                truefalseNo[i] = Integer.parseInt(b[i]);
            }
            for (int k=0;k<normal;k++){
                tfList.add(trueFalseMapper.selectByPrimaryKey(truefalseNo[k]));
            }
            for (int m=normal;m<sum;m++){
                Dtruefalse dtruefalse=dtruefalseService.selectByPrimaryKey(truefalseNo[m]);
                TrueFalse trueFalse=new TrueFalse();
                trueFalse.setTfqId(dtruefalse.getDtId());
                trueFalse.setTfqContent(dtruefalse.getDtContent());
                trueFalse.setTfqCorrect(dtruefalse.getDtCorrect());
                tfList.add(trueFalse);
            }
            return tfList;
        }else {
            List<TrueFalse> tfList=new ArrayList<>();
            String[] b=truefalse.split("/");
            int[] truefalseNo=new int[b.length];
            for (int i=0;i<b.length;i++) {
                truefalseNo[i] = Integer.parseInt(b[i]);
            }
            for (int k=0;k<difficulty;k++){
                tfList.add(trueFalseMapper.selectByPrimaryKey(truefalseNo[k]));
            }
            for (int m=difficulty;m<sum;m++){
                Dtruefalse dtruefalse=dtruefalseService.selectByPrimaryKey(truefalseNo[m]);
                TrueFalse trueFalse=new TrueFalse();
                trueFalse.setTfqId(dtruefalse.getDtId());
                trueFalse.setTfqContent(dtruefalse.getDtContent());
                trueFalse.setTfqCorrect(dtruefalse.getDtCorrect());
                tfList.add(trueFalse);
            }
            return tfList;
        }

    }
}
