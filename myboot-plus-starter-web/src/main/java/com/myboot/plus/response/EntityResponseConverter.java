package com.myboot.plus.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zyf
 * @Date: 2025/2/7 11:27
 * @Description: TODO
 */
public class EntityResponseConverter extends AbstractGenericHttpMessageConverter<Object> {


    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_JSON);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        JSONObject json = new JSONObject();
        json.put("data", JSON.toJSONString(object));
        json.put("code", 200);
        httpOutputMessage.getBody().write(json.toJSONString().getBytes());
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.writeInternal(object, outputMessage);
    }

    @Override
    protected ResponseDTO readInternal(Class<? extends Object> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public ResponseDTO read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
}
