package com.lhb.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lhb.common.utils.CertStatus;

import java.io.IOException;

/**
 * 认证状态的序列化类.
 * Created by Kuang.Ru on 2015/12/14.
 */
public class CertStatusSerializer extends JsonSerializer<CertStatus> {
    @Override
    public void serialize(CertStatus status, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        generator.writeNumber(status.getValue());
    }
}
