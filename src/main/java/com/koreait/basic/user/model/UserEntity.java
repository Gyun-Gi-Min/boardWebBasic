package com.koreait.basic.user.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private int gender;
    private String rdt;
    private String profileImg; //alter써서 추가된거임
}
