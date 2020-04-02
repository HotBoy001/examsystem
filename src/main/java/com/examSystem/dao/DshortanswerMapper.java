package com.examSystem.dao;

import com.examSystem.entity.Dshortanswer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DshortanswerMapper {
    int deleteByPrimaryKey(Integer dsId);

    int insert(Dshortanswer record);

    Dshortanswer selectByPrimaryKey(Integer dsId);

    List<Dshortanswer> selectAll();

    int updateByPrimaryKey(Dshortanswer record);
}