package com.huxl.zhidao.controller;

import com.huxl.zhidao.model.HostHolder;
import com.huxl.zhidao.model.Question;
import com.huxl.zhidao.service.QuestionService;
import com.huxl.zhidao.util.ZhidaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author huxl
 * @createDate 2017/12/16 15:26
 */
@Controller
public class QuestionComntroller {
    private static final Logger logger = LoggerFactory.getLogger(QuestionComntroller.class);

    @Autowired
    QuestionService questionService;
    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/question/add"},method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title,
                              @RequestParam("content") String content) {
        try {
            Question question = new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setCreatedDate(new Date());
            if (hostHolder.getUser() == null) {
                question.setUserId(ZhidaoUtil.ANOYMOUS_USERID);
            } else {
                question.setUserId(hostHolder.getUser().getUserId());
            }

            if (questionService.addQuestion(question) > 0) {
                return ZhidaoUtil.getJSONString(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加问题失败",e.getMessage());
        }

        return ZhidaoUtil.getJSONString(1, "添加失败");

    }
}
