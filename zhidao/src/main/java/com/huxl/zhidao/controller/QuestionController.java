package com.huxl.zhidao.controller;

import com.huxl.zhidao.async.EventModel;
import com.huxl.zhidao.async.EventProducer;
import com.huxl.zhidao.async.EventType;
import com.huxl.zhidao.model.*;
import com.huxl.zhidao.service.*;
import com.huxl.zhidao.util.ZhidaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huxl
 * @since  2017/12/16 15:26
 */
@Controller
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(value = "/question/{qid}",method = {RequestMethod.GET})
    public String getQuestionDetail(Model model, @PathVariable int qid){
        Question question = questionService.getById(qid);
        model.addAttribute("question",question);

        List<Comment> commentList = commentService.getCommentsByEntity(qid, EntityType.ENTITY_QUESTION);
        List<ViewObject> comments = new ArrayList<>();
        for (Comment comment : commentList) {
            ViewObject vo = new ViewObject();
            vo.set("comment",comment);
            int liked = 0;
            //登录用户，查询该用户的点赞状态
            if (hostHolder.getUser() != null) {
                liked = likeService.getLikeStatus(hostHolder.getUser().getUserId(),EntityType.ENTITY_COMMENT,comment.getId());
            }
            vo.set("liked",liked);
            //统计评论点赞数
            vo.set("likeCount",likeService.getLikeCount(EntityType.ENTITY_COMMENT,comment.getId()));
            vo.set("user",userService.getUser(comment.getUserId()));
            comments.add(vo);
        }
        model.addAttribute("comments",comments);

        List<ViewObject> followUsers= new ArrayList<>();
        //获取关注用户的信息
        List<Integer> users = followService.getFollowers(EntityType.ENTITY_QUESTION,qid,20);
        for (Integer userId : users) {
            ViewObject vo =new ViewObject();
            User user = userService.getUser(userId);
            if (user == null) {
                continue;
            }

            vo.set("name",user.getUserName());
            vo.set("headUrl",user.getHeadUrl());
            vo.set("id",user.getUserId());
            followUsers.add(vo);
        }
        model.addAttribute("followUsers",followUsers);

        //登录用户，查询该用户的关注状态
        boolean followed = false;
        if (hostHolder.getUser() != null) {
            followed = followService.isFollower(hostHolder.getUser().getUserId(),EntityType.ENTITY_QUESTION,qid);
        }

        model.addAttribute("followed",followed);

        return "detail";
    }

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
                question.setUserId(ZhidaoUtil.ANONYMOUS_USERID);
            } else {
                question.setUserId(hostHolder.getUser().getUserId());
            }

            if (questionService.addQuestion(question) > 0) {
                EventModel eventModel = new EventModel(EventType.ADD_QUESTION);

                eventModel.setActorId(question.getUserId()).setEntityId(question.getQuestionId())
                .setExt("title",question.getTitle()).setExt("content",question.getContent());

                boolean ret = eventProducer.fireEvent(eventModel);
                if (!ret) {
                    logger.warn("触发添加问题事件失败");
                }

                return ZhidaoUtil.getJSONString(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加问题失败",e.getMessage());
        }

        return ZhidaoUtil.getJSONString(1, "添加失败");

    }
}
