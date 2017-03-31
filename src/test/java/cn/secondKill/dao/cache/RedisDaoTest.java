package cn.secondKill.dao.cache;

import cn.secondKill.dao.SecKillDao;
import cn.secondKill.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    private long id = 1001;
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SecKillDao secKillDao;

    @Test
    public void testSecKill() throws Exception {
        //get and put
        SecKill secKill = redisDao.getSecKill(id);
        if (secKill == null) {
            secKill = secKillDao.queryById(id);
            if(secKill != null){
                String result = redisDao.putSecKill(secKill);
                System.out.println(result);
                secKill = redisDao.getSecKill(id);
                System.out.println(secKill);
            }

        }
    }
}