package service;


import com.baomidou.mybatisplus.extension.service.IService;


import org.springframework.boot.autoconfigure.security.SecurityProperties;


/**
 * 用户服务
 *
 */
public interface InnerUserService  {

    /**
     * 数据库中查是否以分配给用户密钥
     * @param accessKey
     * 去掉secretKey是因为secretKey不应作为公共参数传入，应该直接从数据库中获取
     * @return
     */
    User getInvokeUser(String accessKey);

}
