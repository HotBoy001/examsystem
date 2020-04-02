package com.examSystem.service;

import com.examSystem.dao.DtruefalseMapper;
import com.examSystem.entity.Dtruefalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtruefalseService {
    private final DtruefalseMapper dtruefalseMapper;
    @Autowired
    public DtruefalseService(DtruefalseMapper dtruefalseMapper) {
        this.dtruefalseMapper = dtruefalseMapper;
    }
    public List<Dtruefalse> selectAll(){
        return dtruefalseMapper.selectAll();
    }
    public Dtruefalse selectByPrimaryKey(Integer dtId){
        return dtruefalseMapper.selectByPrimaryKey(dtId);
    }
}
