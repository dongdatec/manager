package com.kazuha.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
/*
    @author：kazuha
    @statement：@Valid 注解通常用于对象属性字段的规则检测
*/
@Data
@TableName("dongda_service")
public class SaleOrder {
    //序号
    private Integer id;
    //日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date service_date ;
    //单号
    private String service_odd;
    //机器序列号
    private String machine_id;
    //单位名称
    private String department;
    //办公室
    private String office;
    //电话
    private String tel;
    //设备型号
    private String device_type;
    //故障现象
    private String fault_phenomenon;
    //故障原因
    private String fault_cause;
    //是否维修
    private Boolean is_maintain;
    //是否送回
    private Boolean is_back;
    //经手人
    private String person_handing;

}
