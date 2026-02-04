package com.aidaml.cc.demo.util;

import org.springframework.core.convert.converter.Converter;

import com.aidaml.cc.demo.model.dto.OrderBy;

public class StringToEnumConverter implements Converter<String, OrderBy> {

    @Override
    public OrderBy convert(String source) {
        return OrderBy.valueOf(source.toUpperCase().replace("_", ""));
    }

}
