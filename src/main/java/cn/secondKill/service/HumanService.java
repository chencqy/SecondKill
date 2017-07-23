package cn.secondKill.service;

import cn.secondKill.entity.HumanModel;

import java.util.List;

/**
 * Created by dell on 2017/7/23.
 */
public interface HumanService {

    /**
     * @Description : 查询单个human
     * @param : humanId
     * @return : HumanModel
     * @Date : 2017/7/23 19:56
     */
    HumanModel getHumanById(int humanId);

    /**
     * @Description : 查询所有human
     * @param :
     * @return : List<HumanModel>
     * @Date : 2017/7/23 20:04
     */

    List<HumanModel> getHumanAll();

}
