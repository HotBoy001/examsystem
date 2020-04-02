package com.examSystem.dao;

import com.examSystem.entity.Dtruefalse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DtruefalseMapper {
    int deleteByPrimaryKey(Integer dtId);

    int insert(Dtruefalse record);

    Dtruefalse selectByPrimaryKey(Integer dtId);

    List<Dtruefalse> selectAll();

    int updateByPrimaryKey(Dtruefalse record);
}