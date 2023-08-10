package com.kazuha.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kazuha.controller.utils.ResultsForFront;
import com.kazuha.pojo.SaleOrder;
import com.kazuha.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;

    //TODO：查询全部（已完成）
    @GetMapping("/list")
    public ResultsForFront list(){
        return new ResultsForFront(true,saleOrderService.list());
    }

    //TODO：按ID查询（已完成）
    @GetMapping("{id}")
    public ResultsForFront getById(@PathVariable Integer id){
        return new ResultsForFront(true, saleOrderService.getById(id));
    }

    //TODO：新增 （已完成）
    @PostMapping("/save")
    public ResultsForFront save(@RequestBody SaleOrder saleOrder){
        boolean flag = saleOrderService.save(saleOrder);
        return new ResultsForFront(flag,flag?"添加成功=v=":"添加失败=-=]、");
    }
    //TODO：修改（已完成）
    @PutMapping
    public ResultsForFront mod(@RequestBody SaleOrder saleOrder){
        boolean flag = saleOrderService.updateById(saleOrder);
        return  new ResultsForFront(flag,flag?"修改成功=v=":"修改失败=-=]、");
    }
    //TODO：新增或修改（已完成）
    /*
        @author：kazuha
        @statement：.saveOrUpdate()
        saveOrUpdate()的底层原理是：
    ·先进行查询 如果这个id为空 -----就新增。
    ·如果有id的话就会通过这个id先进行查询 如果查到数据就进行修改，如果没有查到数据
     就进行新增

    */
    @PostMapping("/saveOrMod")
    public ResultsForFront saveOrMod(@RequestBody SaleOrder saleOrder){
        boolean flag = saleOrderService.saveOrUpdate(saleOrder);
        return  new ResultsForFront(flag,flag?"操作成功=v=":"操作失败=-=]、");
    }

    //TODO:删除（已完成）
    @DeleteMapping("{id}")
    public ResultsForFront delete(@PathVariable Integer id){
        boolean flag = saleOrderService.removeById(id);
        return new ResultsForFront(flag,flag?"删除成功=v=":"删除失败=-=]、");
    }
    //TODO:模糊查询 （已完成，可根据需要来进行添加模糊查询的条件）
    @GetMapping("/listlike")
    public ResultsForFront listLike(@RequestBody SaleOrder saleOrder){
        LambdaQueryWrapper<SaleOrder> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(SaleOrder::getService_odd,saleOrder.getService_odd());
        return new ResultsForFront(true,saleOrderService.list(lambdaQueryWrapper));
    }
    //TODO：匹配查询 （已完成）
    @GetMapping("/listeq")
    public ResultsForFront listEq(@RequestBody SaleOrder saleOrder){
        LambdaQueryWrapper<SaleOrder> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(SaleOrder::getService_odd,saleOrder.getService_odd());
        return new ResultsForFront(true,saleOrderService.list(lambdaQueryWrapper));
    }

    //TODO：分页查询（结合条件）
//    @GetMapping
//    public ResultsForOrder getPage(PageParam pageParam, ReqSaleOrder reqSaleOrder) {
//        return new ResultsForOrder(true,saleOrderService.getPage(pageParam, reqSaleOrder));
//    }

//    @GetMapping("{currentPage}/{pageSize}")
//    public ResultsForOrder getPage(@PathVariable int currentPage,@PathVariable int pageSize){
//
////        ipCountService.count();
//
//        IPage<SaleOrder> page = saleOrderService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
//        if( currentPage > page.getPages()){
//            page = saleOrderService.getPage((int)page.getPages(), pageSize);
//        }
//        return new ResultsForOrder(true, page);
//    }


    @GetMapping("/{currentPage}/{pageSize}")
    public ResultsForFront getPage(@PathVariable int currentPage, @PathVariable int pageSize, SaleOrder saleOrder){
        System.out.println("参数==>"+saleOrder);

//        ipCountService.count();


        IPage<SaleOrder> page = saleOrderService.getPage(currentPage, pageSize, saleOrder);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = saleOrderService.getPage((int)page.getPages(), pageSize, saleOrder);
        }

        return new ResultsForFront(true, page);
    }
}
