package com.examSystem.dao;

import com.examSystem.entity.Dchoice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DchoiceMapper {
    int deleteByPrimaryKey(Integer dcId);

    int insert(Dchoice record);

    Dchoice selectByPrimaryKey(Integer dcId);

    List<Dchoice> selectAll();

    int updateByPrimaryKey(Dchoice record);
}