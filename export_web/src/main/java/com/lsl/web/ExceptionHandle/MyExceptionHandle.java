package com.lsl.web.ExceptionHandle;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionHandle implements HandlerExceptionResolver {
    /**
     *统一异常处理
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg","对不起，我错误了："+ex.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
