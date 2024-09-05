package service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.model.entity.InterfaceInfo;
import entity.model.entity.User;
import entity.model.entity.UserInterfaceInfo;


/**
 * 用户接口信息服务
 *
 */
public interface InnerUserInterfaceInfoService  {




    /**
     *
     * @param userInterfaceInfo
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
