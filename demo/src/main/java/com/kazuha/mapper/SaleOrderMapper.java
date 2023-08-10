package com.kazuha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kazuha.pojo.SaleOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {

}
