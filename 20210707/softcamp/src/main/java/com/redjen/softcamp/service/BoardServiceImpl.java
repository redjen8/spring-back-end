package com.redjen.softcamp.service;

import java.util.List;

import com.redjen.softcamp.domain.Board;
import com.redjen.softcamp.repository.BoardRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    @Override
    @Transactional
    public void register(Board board) throws Exception {
        repository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Board read(long boardNo) throws Exception {
        Board readObj = repository.getById(boardNo);
        System.out.println("number = " + readObj.getBoardNo());
        System.out.println("title = " + readObj.getTitle());
        System.out.println("content = " + readObj.getContent());
        System.out.println("writer = " + readObj.getWriter());
        System.out.println("regdate = " + readObj.getRegDate());
        return readObj;
    }

    @Override
    @Transactional
    public void modify(Board board) throws Exception {
        Board boardEntity = repository.getById(board.getBoardNo());
        System.out.println("title changed " + boardEntity.getTitle() + "->" + board.getTitle());
        System.out.println("content changed " + boardEntity.getContent() + "->" + board.getContent());
        System.out.println("writer changed " + boardEntity.getWriter() + "->" + board.getWriter());
        boardEntity.setTitle(board.getTitle());
        boardEntity.setContent(board.getContent());
        boardEntity.setWriter(board.getWriter());
    }

    @Override
    @Transactional
    public void remove(long boardNo) throws Exception {
        repository.deleteById(boardNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> list() throws Exception {
        return repository.findAll(Sort.by(Direction.DESC, "boardNo"));
    }
 }