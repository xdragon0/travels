package com.travel.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象
public class Province implements Serializable {
    private String id;
    private String name;
    private String tags;
    private Integer placecounts;

}
