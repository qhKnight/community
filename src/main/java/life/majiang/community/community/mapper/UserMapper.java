package life.majiang.community.community.mapper;


import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by qhKnight on 2020/3/19
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

   /* @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);*/
}
