package cn.candy.candyhome.user.mapper.generator;

import cn.candy.candyhome.user.po.generator.UserAccountOperation;
import cn.candy.candyhome.user.po.generator.UserAccountOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountOperationMapper {
    long countByExample(UserAccountOperationExample example);

    int deleteByExample(UserAccountOperationExample example);

    int deleteByPrimaryKey(String uid);

    int insert(UserAccountOperation record);

    int insertSelective(UserAccountOperation record);

    List<UserAccountOperation> selectByExample(UserAccountOperationExample example);

    UserAccountOperation selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") UserAccountOperation record, @Param("example") UserAccountOperationExample example);

    int updateByExample(@Param("record") UserAccountOperation record, @Param("example") UserAccountOperationExample example);

    int updateByPrimaryKeySelective(UserAccountOperation record);

    int updateByPrimaryKey(UserAccountOperation record);
}