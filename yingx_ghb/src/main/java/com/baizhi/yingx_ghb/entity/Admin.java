package com.baizhi.yingx_ghb.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

  private String id;
  private String username;
  private String password;
  private String status;
  private String level;
  private String salt;

}
