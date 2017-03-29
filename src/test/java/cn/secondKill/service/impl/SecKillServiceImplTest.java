package cn.secondKill.service.impl;

import cn.secondKill.Exception.RepeatKillException;
import cn.secondKill.Exception.SecKillCloseException;
import cn.secondKill.dto.Exposer;
import cn.secondKill.dto.SecKillExecution;
import cn.secondKill.entity.SecKill;
import cn.secondKill.service.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SecKillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @Test
    public void getSecKillList() throws Exception {
        List<SecKill> list = secKillService.getSecKillList();
        logger.info("list ={}" ,list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000L;
        SecKill secKill = secKillService.getById(id);
        logger.info("secKill={}",secKill);
    }

    @Test
    public void exportSecKillUrl() throws Exception {
        long id = 1002L;
        Exposer exposer = secKillService.exportSecKillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long userPhone = 18875362567L;
            String md5 = exposer.getMd5();
            try{
                SecKillExecution secKillExecution = secKillService.executeSecKill(id,userPhone,md5);
                logger.info("result={}",secKillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SecKillCloseException e){
                logger.error(e.getMessage());
            }
        }

    }
}