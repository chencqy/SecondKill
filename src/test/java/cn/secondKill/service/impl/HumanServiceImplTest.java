package cn.secondKill.service.impl;

import cn.secondKill.entity.HumanModel;
import cn.secondKill.service.HumanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by dell on 2017/7/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class HumanServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HumanService humanService;

    @Test
    public void getHumanById() throws Exception {
        int humanId = 1;
        HumanModel humanModel = humanService.getHumanById(humanId);
        logger.info("human = {}", humanModel);
    }

    @Test
    public void getHumanAll() throws Exception {
        List<HumanModel> humanModels = humanService.getHumanAll();
        logger.info("list = {}", humanModels);
    }

}