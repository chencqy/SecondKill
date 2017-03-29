package cn.secondKill.service.impl;

import cn.secondKill.Exception.RepeatKillException;
import cn.secondKill.Exception.SecKillCloseException;
import cn.secondKill.Exception.SecKillException;
import cn.secondKill.dao.SecKillDao;
import cn.secondKill.dao.SuccessKilledDao;
import cn.secondKill.dto.Exposer;
import cn.secondKill.dto.SecKillExecution;
import cn.secondKill.entity.SecKill;
import cn.secondKill.entity.SuccessKilled;
import cn.secondKill.enums.SecKillStateEnum;
import cn.secondKill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SecKillServiceImpl implements SecKillService{

    private final String SLAT = "nfdosanfhao@(U$(*#(*#QHdihdbfi2348320957";//MD5盐值字符串用于混淆MD5

    private Logger logger = LoggerFactory.getLogger(this.getClass());//使用slf4j的日志管理

    @Autowired
    private SecKillDao secKillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Override
    public List<SecKill> getSecKillList() {
        return secKillDao.queryAll(0,4);
    }

    @Override
    public SecKill getById(long secKillId) {
        return secKillDao.queryById(secKillId);
    }

    @Override
    @Transactional
    public Exposer exportSecKillUrl(long secKillId) {
        SecKill secKill = secKillDao.queryById(secKillId);
        if (secKill == null){
            return new Exposer(false,secKillId);
        }
        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false,secKillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = getMD5(secKillId);
        return new Exposer(true,md5,secKillId);
    }

    @Override
    public SecKillExecution executeSecKill(long secKillId, long userPhone, String md5)
            throws SecKillException, RepeatKillException, SecKillCloseException {
        if (md5 == null || !md5.equals(getMD5(secKillId))){
            throw new SecKillException("secKill data rewrite");
        }

        //执行秒杀过程：减库存和记录购买行为
        Date nowTime = new Date();
        try {
            //减库存
            int updateCount = secKillDao.reduceNumber(secKillId, nowTime);
            if (updateCount <= 0) {
                //秒杀结束
                throw new SecKillCloseException("SecKill is closed");
            } else {
                //减库存成功，记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("secKill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(secKillId, userPhone);
                    return new SecKillExecution(secKillId, SecKillStateEnum.SUCCESS, successKilled);
                }
            }
        }catch (SecKillCloseException e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有编译期异常转化为运行期异常
            throw new SecKillException("secKill inner error:" +e.getMessage());
        }
    }

    private String getMD5(long secKillId){
        String base = secKillId + "/" + SLAT;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
