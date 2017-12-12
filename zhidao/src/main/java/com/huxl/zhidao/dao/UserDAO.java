package com.huxl.zhidao.dao;

import com.huxl.zhidao.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author huxingl
 * @Date 2017/11/6
 * 用户Dao接口
 */
@Repository
@Mapper
public interface UserDAO {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " user_name, password, salt, head_url";
    String SELECT_FIELDS = " user_id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME,"(",INSERT_FIELDS,") values (#{userName},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where user_id = #{id}"})
    User selectById(int id);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where user_name = #{userName}"})
    User selectByName(String userName);

    @Update({"update", TABLE_NAME, "set password = #{password} where user_id = #{userId}"})
    void updatePassword(User user);

    @Update({"update", TABLE_NAME, "set head_url = #{headUrl} where user_id = #{userId}"})
    void updateHeadUrl(User user);

    @Delete({"delete from", TABLE_NAME, "where user_id = #{id}"})
    void deleteById(int id);
}
