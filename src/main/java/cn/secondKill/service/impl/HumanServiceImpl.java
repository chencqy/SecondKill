package cn.secondKill.service.impl;

import cn.secondKill.dao.HumanDao;
import cn.secondKill.entity.HumanModel;
import cn.secondKill.service.HumanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/7/23.
 */

@Service
public class HumanServiceImpl implements HumanService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());//使用slf4j的日志管理

    @Autowired
    private HumanDao humanDao;

    @Override
    public HumanModel getHumanById(int humanId) {
        return humanDao.queryHumanById(humanId);
    }

    @Override
    public List<HumanModel> getHumanAll(){
        return humanDao.queryAllHuman();
    }

}
