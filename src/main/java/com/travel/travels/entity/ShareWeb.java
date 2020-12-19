package com.travel.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ShareWeb {
    private String wid;
    private String waddress;
    private String wname;
    private String message;
    private String cover;
    private Integer typeid;
    private String date;
    private String uid;
//    private String wid;
    private Integer is_delete;
}
