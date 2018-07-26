package cn.candy.candyhome.user.mapper.generator;

import cn.candy.candyhome.user.po.generator.UserActivationOperation;
import cn.candy.candyhome.user.po.generator.UserActivationOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivationOperationMapper {
    long countByExample(UserActivationOperationExample example);

    int deleteByExample(UserActivationOperationExample example);

    int deleteByPrimaryKey(String uid);

    int insert(UserActivationOperation record);

    int insertSelective(UserActivationOperation record);

    List<UserActivationOperation> selectByExample(UserActivationOperationExample example);

    UserActivationOperation selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") UserActivationOperation record, @Param("example") UserActivationOperationExample example);

    int updateByExample(@Param("record") UserActivationOperation record, @Param("example") UserActivationOperationExample example);

    int updateByPrimaryKeySelective(UserActivationOperation record);

    int updateByPrimaryKey(UserActivationOperation record);
}