package com.myboot.plus.messageconvoter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zyf
 * @Date: 2025/2/7 09:20
 * @Description: TODO
 */
public class String2ResponseConvoter implements HttpMessageConverter {
    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        //判断返回的是不是String这个类
        return aClass.isAssignableFrom(String.class);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.emptyList();
    }

    @Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        JSONObject json = new JSONObject();
        json.put("data", JSON.toJSONString(o));
        json.put("code", 200);
        httpOutputMessage.getBody().write(json.toJSONString().getBytes());
    }
}
