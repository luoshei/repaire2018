package org.repaire.mapper;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.repaire.pojo.UserAndUserInfo;
import org.repaire.pojo.Userinfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luo on 2018/5/19.
 */
public interface UserAndUserInfoMapper {
    @Select({
            "select",
            "*",
            "from user a,userinfo b",
            "where a.openId = b.openId and a.role = #{0,jdbcType=INTEGER}"
    })
    @ResultType(List.class)
    List<UserAndUserInfo> selectAllRepairmen(int role);
    //List<UserAndUserInfo> selectPerson(int role);
}
