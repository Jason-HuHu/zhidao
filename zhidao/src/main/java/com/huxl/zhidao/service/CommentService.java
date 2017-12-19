package com.huxl.zhidao.service;

import com.huxl.zhidao.dao.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huxl
 * @createDate 2017/12/16 14:53
 */
@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;
    public int getUserCommentCount(int userId) {
        return  commentDAO.getUserCommentCount(userId);
    }
}
