package cn.secondKill.dao;

import cn.secondKill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Richard Chen on 2017/3/22.
 */
@Repository
public interface SuccessKilledDao {

    /**
     * @Description :插入购买明细,可过滤重复
     * @param :secKillId  userPhone
     * @return :
     * @Date : 17:10 2017/3/22
     */
    int insertSuccessKilled(@Param("secKillId") long secKillId, @Param("userPhone") long userPhone);

    /**
     * @Description :根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了SecKill秒杀产品对象)
     * @param :secKillId
     * @return :
     * @Date : 17:10 2017/3/22
     */
    SuccessKilled queryByIdWithSecKill(@Param("secKillId") long secKillId, @Param("userPhone") long userPhone);
}
