package com.redjen.softcamp.service;

import java.util.List;

import com.redjen.softcamp.domain.Board;
import com.redjen.softcamp.mapper.BoardMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    @Override
    public void register(Board board) throws Exception {
        mapper.create(board);
    }

    @Override
    public Board read(long boardNo) throws Exception {
        Board readObj = mapper.read(boardNo);
        System.out.println("number = " + readObj.getBoardNo());
        System.out.println("title = " + readObj.getTitle());
        System.out.println("content = " + readObj.getContent());
        System.out.println("writer = " + readObj.getWriter());
        System.out.println("regdate = " + readObj.getRegDate());
        return readObj;
    }

    @Override
    public void modify(Board board) throws Exception {
        mapper.update(board);
    }

    @Override
    public void remove(long boardNo) throws Exception {
        mapper.delete(boardNo);
    }

    @Override
    public List<Board> list() throws Exception {
        return mapper.list();
    }
 }