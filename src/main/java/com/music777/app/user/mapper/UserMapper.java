package com.music777.app.user.mapper;

import com.music777.app.user.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {



    @Select("SELECT COUNT(*) FROM USERS WHERE ID = #{id}")
    int checkUserIdExists(String id);

    @Select("SELECT COUNT(*) FROM USERS WHERE EMAIL = #{email}")
    int checkUserEmailExists(String email);

    @Insert("INSERT INTO USERS (USER_NO, ID, PASSWORD, NAME, EMAIL, PHONE_NUMBER) VALUES (USER_SEQ.NEXTVAL, #{id}, #{password}, #{name}, #{email}, #{phoneNumber})")
    void save(UserDTO userDTO);
}
