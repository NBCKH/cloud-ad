package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Created by chenkaihua on 2019-04-19 09:57
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)  //链式编程
@Builder
public class CommonResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
}
