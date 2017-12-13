package com.huxl.zhidao.interceptor;

import com.huxl.zhidao.dao.LoginTicketDAO;
import com.huxl.zhidao.dao.UserDAO;
import com.huxl.zhidao.model.HostHolder;
import com.huxl.zhidao.model.LoginTicket;
import com.huxl.zhidao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author JasonHu
 * @date 2017-12-13
 * 登录拦截器
 */
@Component
public class PasswordInterceptor implements HandlerInterceptor {
    @Autowired
    LoginTicketDAO loginTicketDAO;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserDAO userDAO;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //cookie中ticket的值
        String ticket = null;
        //获取cookies
        if (httpServletRequest.getCookies() != null) {
            //遍历Cookies
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if ("ticket".equals(cookie.getName())) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket != null) {
            //根据ticket查询loginticket，（userId与ticket的对应关系）
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            //判断是否有loginTicket，并且没有过期，状态为有效状态
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
                return true;//todo 待完善
            }
            User user = userDAO.selectById(loginTicket.getUserId());
            hostHolder.setUSer(user);//将该用户信息存到上下文中
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //添加到model中，可以直接在velocity中使用user对象
        if (modelAndView != null && hostHolder.getUser() != null) {
            modelAndView.addObject("user",hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //结束请求后要清理上下文
        hostHolder.clear();
    }
}
