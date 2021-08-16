package baker6romeo.udacity.jwdnd.c1.review.mapper;

import baker6romeo.udacity.jwdnd.c1.review.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (firstname, lastname, password, salt, username) VALUES(#{firstName}, #{lastName}, #{password}, #{salt}, #{username})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}
