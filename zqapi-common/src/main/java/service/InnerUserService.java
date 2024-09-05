package service;


import com.baomidou.mybatisplus.extension.service.IService;

import entity.model.entity.User;


/**
 * 用户服务
 *
 */
public interface InnerUserService  {

    /**
     * 数据库中查是否以分配给用户密钥
     * @param accessKey
     * @param secretKey
     * @return
     */
    User getInvokeUser(String accessKey, String secretKey);

}
