package com.huxl.zhidao.controller;

import com.huxl.zhidao.model.Question;
import com.huxl.zhidao.model.ViewObject;
import com.huxl.zhidao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jasonhu
 * @Date 2017-12-11
 * 主页控制器
 */
@Controller
public class HomeController {
    @Autowired
    QuestionService questionService;
    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model,
                        @RequestParam(value = "pop", defaultValue = "0") int pop) {
        model.addAttribute("vos", getQuestions(0, 0, 10));
        return "index";
    }
    private List<ViewObject> getQuestions(int userId, int offset, int limit) {
//        List<Question> questionList = questionService.getLatestQuestions(userId, offset, limit);
        List<Question> questionList = new ArrayList<>();
        List<ViewObject> vos = new ArrayList<>();
        for (Question question : questionList) {
            ViewObject vo = new ViewObject();
            vo.set("question", question);
//            vo.set("followCount", followService.getFollowerCount(EntityType.ENTITY_QUESTION, question.getId()));
//            vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

}
