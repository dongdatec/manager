package com.kazuha.req;

import lombok.Data;

/**
 * 订单请求条件
 */
@Data
public class ReqSaleOrder {
    private String service_odd;
    private String device_type;
    private String person_handing;
}
