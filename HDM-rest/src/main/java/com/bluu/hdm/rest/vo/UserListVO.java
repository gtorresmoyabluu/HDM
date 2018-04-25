/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.vo;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;

/**
 *
 * @author Gonzalo Torres
 */
@JsonRootName(value = "users")
public class UserListVO extends ArrayList<UserVO> {

}
