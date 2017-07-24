package cn.secondKill.dao;

import cn.secondKill.entity.HumanModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2017/7/22.
 * human接口
 */
public interface HumanDao {

    /**
     * @Description :根据Id查询秒杀的商品信息
     * @param :humanId
     * @return : HumanModel
     * @Date : 21:52 2017/7/22
     */
    HumanModel queryHumanById(int humanId);

    /**
     * @Description : 查询所有human
     * @param :
     * @return : List<HumanModel>
     * @Date : 2017/7/23 20:02
     */
    List<HumanModel> queryAllHuman();

    /**
     * @Description : 添加用户
     * @param : humanName  gender  user_phone  password email
     * @return :
     * @Date : 2017/7/24 21:26
     */

    void addHuman(@Param("humanName")String humanName, @Param("gender")String gender, @Param("user_phone")String user_phone, @Param("password")String password, @Param("email")String email);

}
