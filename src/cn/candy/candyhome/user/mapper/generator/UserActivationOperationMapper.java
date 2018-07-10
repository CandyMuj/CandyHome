package cn.candy.candyhome.user.mapper.generator;

import cn.candy.candyhome.user.po.generator.UserActivationOperation;
import cn.candy.candyhome.user.po.generator.UserActivationOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivationOperationMapper {
    long countByExample(UserActivationOperationExample example);

    int deleteByExample(UserActivationOperationExample example);

    int insert(UserActivationOperation record);

    int insertSelective(UserActivationOperation record);

    List<UserActivationOperation> selectByExample(UserActivationOperationExample example);

    int updateByExampleSelective(@Param("record") UserActivationOperation record, @Param("example") UserActivationOperationExample example);

    int updateByExample(@Param("record") UserActivationOperation record, @Param("example") UserActivationOperationExample example);
}