package com.redjen.softcamp.mapper;

import java.util.List;

import com.redjen.softcamp.domain.Board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {

    public void create(Board board) throws Exception;

    public Board read(long boardNo) throws Exception;

    public void update(Board board) throws Exception;

    public void delete(long boardNo) throws Exception;

    public List<Board> list() throws Exception;

    public List<Board> search(@Param("title") String title) throws Exception;
}