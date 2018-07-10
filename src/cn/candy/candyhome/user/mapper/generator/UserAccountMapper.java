package cn.candy.candyhome.user.mapper.generator;

import cn.candy.candyhome.user.po.generator.UserAccount;
import cn.candy.candyhome.user.po.generator.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper {
    long countByExample(UserAccountExample example);

    int deleteByExample(UserAccountExample example);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    List<UserAccount> selectByExample(UserAccountExample example);

    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);
}