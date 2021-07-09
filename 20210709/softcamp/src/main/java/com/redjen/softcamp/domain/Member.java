package com.redjen.softcamp.domain;

import java.time.LocalDateTime;
import java.util.List;

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
    private boolean enabled;

    private LocalDateTime regDate;
    private LocalDateTime updDate;

    private List<MemberAuth> authList;
}
