package com.kazuha;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazuha.pojo.SaleOrder;
import com.kazuha.service.SaleOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private SaleOrderService saleOrderService;
    @Resource
    private ObjectMapper mapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testJsonFormat() {
        SaleOrder order = new SaleOrder();
        order.setService_odd("1002");
        IPage<SaleOrder> page = saleOrderService.getPage(1, 4, order);
        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( 1 > page.getPages()){
            page = saleOrderService.getPage((int)page.getPages(), 4, order);
        }
        List<SaleOrder> records = page.getRecords();
        records.forEach(r -> {
            try {
                System.out.println(mapper.writeValueAsString(r));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

}
