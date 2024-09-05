package service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.model.entity.InterfaceInfo;


/**
 * 接口信息服务
 *
 */
public interface InnerInterfaceInfoService   {
    /**
     * 从数据库中查询模拟接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path,String method);
    /**
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
