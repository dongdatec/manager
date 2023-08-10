package com.kazuha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kazuha.mapper.SaleOrderMapper;
import com.kazuha.pojo.SaleOrder;
import com.kazuha.service.SaleOrderService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
    @author：kazuha
    @statement：
    @Autowired 和 @Resource 都是用来实现依赖注入的注解，但它们有以下几点不同之处。首先，它们的来源不同，
    @Autowired 来自 Spring 框架，而 @Resource 来自于（Java）JSR-250。其次，它们的依赖查找顺序不同，
    @Autowired 先根据类型再根据名称查询，而 @Resource 先根据名称再根据类型查询。第三，它们支持的参数不同，
    @Autowired 只支持设置 1 个参数，而 @Resource 支持设置 7 个参数。第四，它们的依赖注入用法不同，
    @Autowired 既支持构造方法注入，又支持属性注入和 Setter 注入，而 @Resource 只支持属性注入和 Setter 注入。
    最后，编译器 IDEA 的提示也不同，当注入 Mapper 对象时，使用 @Autowired 注解编译器会提示错误，
    而使用 @Resource 注解则不会提示错误。
*/

@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements SaleOrderService {
    @Autowired
    private SaleOrderMapper saleOrderMapper;

    /*
        @author：kazuha
        @statement：lambdaQueryWrapper.like（
        只有该参数是true时，才将like条件拼接到sql中，Strings.isNotEmpty() ->用于判断该值是否为空
        该参数是数据库中的字段名， Class::get  ->获取字段名
        该参数值字段值。obj.get() ->获取字段属性）
    */
//    @Override
//    public List<SaleOrder> getPage(PageParam pageParam, ReqSaleOrder reqSaleOrder) {
////        查询一页数据
//        IPage<SaleOrder> page = new Page<>(pageParam.getStartPage(), pageParam.getPageSize());
//        saleOrderMapper.selectPage(page, null);
//
//        List<SaleOrder> records = page.getRecords();
////        System.out.println("req: " + reqSaleOrder.getService_odd());
//        return records.stream().filter(new Predicate<SaleOrder>() {
//            @Override
//            public boolean test(SaleOrder saleOrder) {
////                System.out.println("order: " + saleOrder.getService_odd());
//                return saleOrder.getService_odd().equals(reqSaleOrder.getService_odd());
//            }
//        }).collect(Collectors.toList());
//    }

    @Override
    public IPage<SaleOrder> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        saleOrderMapper.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<SaleOrder> getPage(int currentPage, int pageSize, SaleOrder saleOrder) {
        LambdaQueryWrapper<SaleOrder> lqw = new LambdaQueryWrapper<SaleOrder>();
        lqw.like(Strings.isNotEmpty(saleOrder.getService_odd()),SaleOrder::getService_odd,saleOrder.getService_odd());
        lqw.like(Strings.isNotEmpty(saleOrder.getDevice_type()),SaleOrder::getDevice_type,saleOrder.getDevice_type());
        lqw.like(Strings.isNotEmpty(saleOrder.getPerson_handing()),SaleOrder::getPerson_handing,saleOrder.getPerson_handing());
        IPage page = new Page(currentPage,pageSize);
        saleOrderMapper.selectPage(page,lqw);
        return page;
    }

}
