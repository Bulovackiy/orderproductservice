package com.bulovackiy.ops.utils;

import org.springframework.beans.BeanUtils;

public class MappingHelper {

    public static <T, R> T copyClass(R from, T to) {
        BeanUtils.copyProperties(from, to);
        return to;
    }
}
