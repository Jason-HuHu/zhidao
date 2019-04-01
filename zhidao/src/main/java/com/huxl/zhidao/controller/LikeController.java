package com.huxl.zhidao.controller;

import com.huxl.zhidao.async.EventModel;
import com.huxl.zhidao.async.EventProducer;
import com.huxl.zhidao.async.EventType;
import com.huxl.zhidao.model.Comment;
import com.huxl.zhidao.model.EntityType;
import com.huxl.zhidao.model.HostHolder;
import com.huxl.zhidao.service.CommentService;
import com.huxl.zhidao.service.LikeService;
import com.huxl.zhidao.util.ZhidaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 点赞控制器
 * @author huxl
 * @since 2019-04-01
 */
@Controller
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentService commentService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(path = {"/like"}, method = {RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return ZhidaoUtil.getJSONString(999);
        }

        Comment comment = commentService.getCommentById(commentId);

        eventProducer.fireEvent(new EventModel(EventType.LIKE)
                .setActorId(hostHolder.getUser().getUserId()).setEntityId(commentId)
                .setEntityType(EntityType.ENTITY_COMMENT).setEntityOwnerId(comment.getUserId())
                .setExt("questionId", String.valueOf(comment.getEntityId())));

        long likeCount = likeService.like(hostHolder.getUser().getUserId(), EntityType.ENTITY_COMMENT, commentId);
        return ZhidaoUtil.getJSONString(0, String.valueOf(likeCount));
    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return ZhidaoUtil.getJSONString(999);
        }

        long likeCount = likeService.disLike(hostHolder.getUser().getUserId(), EntityType.ENTITY_COMMENT, commentId);
        return ZhidaoUtil.getJSONString(0, String.valueOf(likeCount));
    }
}
