package com.myboot.plus.returnhandler;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;


/**
 * @Author: zyf
 * @Date: 2025/2/10 13:26
 * @Description: TODO
 */
public class ResponseEntityReturnValueHandler implements HandlerMethodReturnValueHandler {

    /**
     * 判断该处理器是否需要处理
     *
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        if (methodParameter.getParameterType().isAssignableFrom(ResponseEntity.class)) {
            return true;
        }
        return false;
    }

    /**
     * 处理返回值
     *
     * @param o
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        try {
            HttpServletResponse servletResponse = (HttpServletResponse) nativeWebRequest.getNativeResponse(HttpServletResponse.class);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            oos.flush();
            oos.close();
            servletResponse.getOutputStream().write(bos.toByteArray());
        } catch (Exception var8) {
            Exception e = var8;
            e.printStackTrace();
        }

    }
}