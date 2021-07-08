package com.redjen.softcamp.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
    
    private int userNo;
    private String userId;
    private String userPw;
    private String userName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime regDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updDate;

    private List<MemberAuth> authList;
}
