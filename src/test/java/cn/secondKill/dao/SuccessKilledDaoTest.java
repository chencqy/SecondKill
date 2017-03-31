package cn.secondKill.dao;

import cn.secondKill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1001L;
        long userPhone = 13983559695L;
        int count = successKilledDao.insertSuccessKilled(id,userPhone);
        System.out.println(count);
    }

    @Test
    public void queryByIdWithSecKill() throws Exception {
        long id = 1003;
        long userPhone = 13983559581L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(id,userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSecKill());

    }

}