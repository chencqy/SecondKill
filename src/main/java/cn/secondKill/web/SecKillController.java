package cn.secondKill.web;


import cn.secondKill.Exception.RepeatKillException;
import cn.secondKill.Exception.SecKillCloseException;
import cn.secondKill.dto.Exposer;
import cn.secondKill.dto.SecKillExecution;
import cn.secondKill.dto.SecKillResult;
import cn.secondKill.entity.SecKill;
import cn.secondKill.enums.SecKillStateEnum;
import cn.secondKill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/secKill")//url:/模块/资源/{}//细分
public class SecKillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SecKillService secKillService;

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String list(Model model){
        //获取列表页
        List<SecKill> list = secKillService.getSecKillList();
        model.addAttribute("list" , list);
        return "list";
    }

    @RequestMapping(value = "/{secKillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("secKillId") Long secKillId, Model model){
        if (secKillId == null){
            return "redirect:/secKill/list";
        }
        SecKill secKill = secKillService.getById(secKillId);
        if (secKill == null){
            return "forward:/secKill/list";
        }
        model.addAttribute("secKill",secKill);
        return "detail";
    }

    @RequestMapping(value = "/{secKillId}/exposer",
                    method = RequestMethod.POST,
                    produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SecKillResult<Exposer> exposer(@PathVariable("secKillId") Long secKillId){
        SecKillResult<Exposer> result;
        try {
            Exposer exposer = secKillService.exportSecKillUrl(secKillId);
            result = new SecKillResult<>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SecKillResult<>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{secKillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SecKillResult<SecKillExecution> execute(@PathVariable("secKillId") Long secKillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone",required = false) Long phone){
        if (phone == null){
            return new SecKillResult<SecKillExecution>(false,"未注册");
        }
        SecKillResult<SecKillExecution> result;
        try {
            SecKillExecution execution = secKillService.executeSecKill(secKillId,phone,md5);
            return new SecKillResult<SecKillExecution>(true,execution);
        }catch (RepeatKillException e){
            SecKillExecution execution = new SecKillExecution(secKillId, SecKillStateEnum.REPEAT_KILL);
            return new SecKillResult<SecKillExecution>(true,execution);
        }catch (SecKillCloseException e){
            SecKillExecution execution = new SecKillExecution(secKillId, SecKillStateEnum.END);
            return new SecKillResult<SecKillExecution>(true,execution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SecKillExecution execution = new SecKillExecution(secKillId, SecKillStateEnum.INNER_ERROR);
            return new SecKillResult<SecKillExecution>(true,execution);
        }
    }

    //获取系统当前时间
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SecKillResult<Long> time(){
        Date now = new Date();
        return new SecKillResult<Long>(true,now.getTime());
    }
}
