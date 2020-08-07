package com.jaybon.jwtEx01.model;

import javax.persistence.*;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private String roles = "";

    // ENUM으로 안하고 , 콤마로 구분해서 ROLE을 입력해서 파싱
    // 콤마를 이용해서 입력 - DB 원자성 의도적으로 파괴
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}

