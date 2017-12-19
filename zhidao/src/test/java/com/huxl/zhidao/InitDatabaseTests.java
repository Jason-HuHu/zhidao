package com.huxl.zhidao;

import com.huxl.zhidao.dao.QuestionDAO;
import com.huxl.zhidao.dao.UserDAO;
import com.huxl.zhidao.model.Question;
import com.huxl.zhidao.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.security.RunAs;
import java.util.Date;
import java.util.Random;

/**
 * @author huxl
 * @createDate 2017/12/16 11:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZhidaoApplication.class)
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;

    @Autowired
    QuestionDAO questionDAO;
//    public InitDatabaseTests(UserDAO userDAO,QuestionDAO questionDAO){
//       this.userDAO =userDAO;
//       this.questionDAO = questionDAO;
//
//    }
    public InitDatabaseTests(){

    }

    @Test
    public void testUserDAO() {
        User user = new User();
        for (int i = 0; i<100;i++) {
            user.setHeadUrl("");
            user.setUserName("123");
            user.setPassword("123");
            user.setSalt("123");
            userDAO.addUser(user);
        }

    }

    @Test
    public void testAddQuestion() {
        Question question = new Question();
        Random random = new Random();
        int ran = random.nextInt(10000);
        for (int i = 0; i <100; i ++) {
            question.setContent("这是第"+ i + "个问题" +  1000*3600*i);
            question.setTitle("这是第" + i + "个问题的标题");
            Date date = new Date();
            date.setTime(date.getTime() + ran);
            question.setCreatedDate(date);
            question.setUserId(i);
            question.setCommentCount(i * 100);
            questionDAO.addQuestion(question);
        }
    }
}
