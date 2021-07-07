package com.redjen.daopractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.redjen.daopractice.dao.BoardDAO;
import com.redjen.daopractice.domain.Board;

@Service
public class BoardServiceImpl implements BoardService{
    
    @Autowired
    private BoardDAO dao;

    @Override
    public void register(Board board) throws Exception {
        dao.create(board);
    }

    @Override
    public Board read(Integer boardNo) throws Exception {
        return dao.read(boardNo);
    }

    @Override
    public void modify(Board board) throws Exception {
        dao.update(board);
    }

    @Override
    public void remove(Integer boardNo) throws Exception {
        dao.delete(boardNo);
    }

    @Override
    public List<Board> list() throws Exception {
        return dao.list();
    }
}
