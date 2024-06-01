package com.example.chapter14.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {
    // Boolean 타입 -> String 타입 변환

    // 엔티티의 데이터를 데이터베이스 컬럼에 저장할 데이터로 변환한다.
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    // 데이터베이스에서 조회한 컬럼 데이터를 엔티티의 데이터로 변환한다.
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}
