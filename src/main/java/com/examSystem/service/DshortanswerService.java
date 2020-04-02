package com.examSystem.service;

import com.examSystem.dao.DshortanswerMapper;
import com.examSystem.entity.Dshortanswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DshortanswerService {
    private final DshortanswerMapper dshortanswerMapper;
    @Autowired
    public DshortanswerService(DshortanswerMapper dshortanswerMapper) {
        this.dshortanswerMapper = dshortanswerMapper;
    }

    public Dshortanswer selectByPrimaryKey(Integer dsId){
        return dshortanswerMapper.selectByPrimaryKey(dsId);
    }

    public List<Dshortanswer> selectAll(){
        return dshortanswerMapper.selectAll();
    }
}
