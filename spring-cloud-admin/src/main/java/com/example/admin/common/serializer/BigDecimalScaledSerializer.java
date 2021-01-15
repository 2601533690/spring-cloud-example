package com.example.admin.common.serializer;

import com.example.admin.common.constant.DigitConstant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalScaledSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal decimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(decimal
                .setScale(DigitConstant.DEFAULT_PRECISION, BigDecimal.ROUND_DOWN)
                .toPlainString());
    }
}
