package com.huxl.zhidao.async.handler;

import com.huxl.zhidao.async.EventHandler;
import com.huxl.zhidao.async.EventModel;
import com.huxl.zhidao.async.EventType;
import com.huxl.zhidao.model.Message;
import com.huxl.zhidao.model.User;
import com.huxl.zhidao.service.MessageService;
import com.huxl.zhidao.service.UserService;
import com.huxl.zhidao.util.ZhidaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class LikeHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(ZhidaoUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());
        message.setContent("用户" + user.getUserName()
                + "赞了你的评论,http://127.0.0.1:8080/question/" + model.getExt("questionId"));

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
