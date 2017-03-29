package cn.secondKill.dao;

import cn.secondKill.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SecKillDaoTest {

    @Resource
    private SecKillDao secKillDao;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = secKillDao.reduceNumber(1000L,killTime);
        System.out.println(updateCount);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        SecKill secKill = secKillDao.queryById(id);
        System.out.println(secKill);

        HashMap<String ,String> map = new HashMap<>();

    }

    @Test
    public void queryAll() throws Exception {
        List<SecKill> list = secKillDao.queryAll(0,100);
        for (SecKill seckill: list) {
            System.out.println(seckill);
        }

    }

}