package cn.secondKill.service;


import cn.secondKill.Exception.RepeatKillException;
import cn.secondKill.Exception.SecKillCloseException;
import cn.secondKill.Exception.SecKillException;
import cn.secondKill.dto.Exposer;
import cn.secondKill.dto.SecKillExecution;
import cn.secondKill.entity.SecKill;

import java.util.List;

public interface SecKillService {

    /**
     * @Description :查询所有秒杀记录
     * @param :
     * @return :List<SecKill>
     * @Date : 15:44 2017/3/26
     */
    List<SecKill> getSecKillList();

    /**
     * @Description :根据Id查询单个秒杀记录
     * @param :secKillId
     * @return :SecKill
     * @Date : 15:45 2017/3/26
     */
    SecKill getById(long secKillId);

    /**
     * @Description :若秒杀开启则输出秒杀地址，否则输出系统当前时间和秒杀开始时间
     * @param :secKillId
     * @return :Exposer
     * @Date : 16:01 2017/3/26
     */
    Exposer exportSecKillUrl(long secKillId);

    /**
     * @Description :执行秒杀操作
     * @param :secKillId userPhone md5
     * @return :SecKillExecution
     * @Date : 16:12 2017/3/26
     */
    SecKillExecution executeSecKill(long secKillId, long userPhone, String md5)
            throws SecKillException,RepeatKillException,SecKillCloseException;

}
