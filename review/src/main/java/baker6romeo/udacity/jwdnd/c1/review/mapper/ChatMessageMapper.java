package baker6romeo.udacity.jwdnd.c1.review.mapper;

import baker6romeo.udacity.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMessageMapper {

    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getMessages();

    @Insert("INSERT INTO MESSAGES (message, username) VALUES(#{message}, #{username})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(ChatMessage chatMessage);
}
