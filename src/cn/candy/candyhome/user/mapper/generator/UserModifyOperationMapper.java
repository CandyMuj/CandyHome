package cn.candy.candyhome.user.mapper.generator;

import cn.candy.candyhome.user.po.generator.UserModifyOperation;
import cn.candy.candyhome.user.po.generator.UserModifyOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserModifyOperationMapper {
    long countByExample(UserModifyOperationExample example);

    int deleteByExample(UserModifyOperationExample example);

    int deleteByPrimaryKey(String uid);

    int insert(UserModifyOperation record);

    int insertSelective(UserModifyOperation record);

    List<UserModifyOperation> selectByExample(UserModifyOperationExample example);

    UserModifyOperation selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") UserModifyOperation record, @Param("example") UserModifyOperationExample example);

    int updateByExample(@Param("record") UserModifyOperation record, @Param("example") UserModifyOperationExample example);

    int updateByPrimaryKeySelective(UserModifyOperation record);

    int updateByPrimaryKey(UserModifyOperation record);
}