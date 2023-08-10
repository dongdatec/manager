package com.kazuha.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kazuha.pojo.SaleOrder;

public interface SaleOrderService extends IService<SaleOrder> {
//    List<SaleOrder> getPage(PageParam pageParam, ReqSaleOrder reqSaleOrder);
    IPage<SaleOrder> getPage(int currentPage, int pageSize);
    IPage<SaleOrder> getPage(int currentPage, int pageSize, SaleOrder saleOrder);
}
