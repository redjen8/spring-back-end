package com.redjen.softcamp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAuth {

    private int userNo;
    private String auth;
}
