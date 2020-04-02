package com.examSystem.service;

import com.examSystem.dao.DchoiceMapper;
import com.examSystem.entity.Dchoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DchoiceService {
    private final DchoiceMapper dchoiceMapper;
    @Autowired
    public DchoiceService(DchoiceMapper dchoiceMapper) {
        this.dchoiceMapper = dchoiceMapper;
    }

    public List<Dchoice> selectAll(){
        return dchoiceMapper.selectAll();
    }

    public Dchoice selectByPrimaryKey(Integer dcId){
        return dchoiceMapper.selectByPrimaryKey(dcId);
    }
}
