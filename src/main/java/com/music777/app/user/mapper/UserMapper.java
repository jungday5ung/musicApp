package com.music777.app.user.mapper;

import com.music777.app.user.dto.UserDTO;
import org.apache.ibatis.annotations.*;

// MyBatis 매퍼 인터페이스를 나타내는 애노테이션입니다.
@Mapper
public interface UserMapper {

    // 주어진 ID가 USERS 테이블에 존재하는지 확인하는 SQL 쿼리입니다.
    @Select("SELECT COUNT(*) FROM USERS WHERE ID = #{id}")
    int checkUserIdExists(String id); // ID가 존재하면 1, 존재하지 않으면 0을 반환합니다.

    // 주어진 EMAIL이 USERS 테이블에 존재하는지 확인하는 SQL 쿼리입니다.
    @Select("SELECT COUNT(*) FROM USERS WHERE EMAIL = #{email}")
    int checkUserEmailExists(String email); // 이메일이 존재하면 1, 존재하지 않으면 0을 반환합니다.

    // USERS 테이블에 새로운 사용자를 추가하는 SQL 쿼리입니다.
    @Insert("INSERT INTO USERS (USER_NO, ID, PASSWORD, NAME, EMAIL, PHONE_NUMBER) VALUES (USER_SEQ.NEXTVAL, #{id}, #{password}, #{name}, #{email}, #{phoneNumber})")
    void register(UserDTO userDTO); // UserDTO 객체의 데이터를 USERS 테이블에 삽입합니다.




    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입


    // 주어진 ID와 비밀번호로 사용자를 찾는 SQL 쿼리입니다.
    @Select("SELECT * FROM USERS WHERE ID = #{id} AND PASSWORD = #{password}")
    UserDTO findByIdAndPassword(@Param("id") String id, @Param("password") String password); // ID와 비밀번호가 일치하는 사용자를 반환합니다.

    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인

    @Update("UPDATE USERS SET PASSWORD = #{password} WHERE ID = #{id}")
    void updatePassword(UserDTO userDTO);

    @Update("UPDATE USERS SET PHONE_NUMBER = #{phoneNumber} WHERE ID = #{id}")
    void updatePhoneNumber(UserDTO userDTO);




    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정

    // 비밀번호를 확인하는 SQL 쿼리입니다.
    @Select("SELECT PASSWORD FROM USERS WHERE ID = #{id}")
    String getPasswordById(String id);

    // 사용자를 삭제하는 SQL 쿼리입니다.
    @Delete("DELETE FROM USERS WHERE ID = #{id}")
    void deleteUserById(String id);


}
