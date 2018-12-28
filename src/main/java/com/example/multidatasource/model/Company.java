package com.example.multidatasource.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * @author wy
 * @date 2018-12-27 10:42:02
 * @description test
 */
@Getter
@Setter
@ToString
public class Company implements Serializable {
    private String coId;
    private String coName;
    private String coNick;
    private int coNum;
    private int coAGE;
    private String coAddress;
    private String coIntro;


}
