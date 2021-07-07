package com.redjen.softcamp.service;

import java.util.List;

import com.redjen.softcamp.domain.Board;
import com.redjen.softcamp.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private final BoardRepository repository;

    @Override
    @Transactional
    public void register(Board board) throws Exception {
        repository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Board read(long boardNo) throws Exception {
        return repository.getById(boardNo);
    }

    @Override
    @Transactional
    public void modify(Board board) throws Exception {
        Board boardEntity = repository.getById(board.getBoardNo());

        boardEntity.setTitle(board.getTitle());
        boardEntity.setContent(board.getContent());
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