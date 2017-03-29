package cn.secondKill.dao;


import cn.secondKill.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SecKillDao {

    /**
     * @Description :减库存
     * @param :secKillId  killTime
     * @return :如果影响行数>1，表示更新库存的记录行数
     * @Date : 16:59 2017/3/22
     */
    int reduceNumber(@Param("secKillId") long secKillId, @Param("killTime") Date killTime);

    /**
     * @Description :根据Id查询秒杀的商品信息
     * @param :secKillId
     * @return :
     * @Date : 17:02 2017/3/22
     */
    SecKill queryById(long secKillId);

    /**
     * @Description :根据偏移量查询秒杀商品列表
     * @param :offset  limit
     * @return :
     * @Date : 17:05 2017/3/22
     */
    List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
