package com.huxl.zhidao.dao;

import com.huxl.zhidao.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huxingl
 * @Date 2017/11/6
 * 用户Dao接口
 */
@Mapper
public interface UserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " user_name, password, salt, head_url";
    String SELECT_FIELDS = " user_id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME,"(",INSERT_FIELDS,") values (#{userName},#{password}),#{salt},#{headUrl}" })
    int addUser(User user);
}
