package com.kazuha.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {


    /*
        @author：kazuha
        @statement：读取控制台内容 /目前跑不动
    */
    public static String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入"+tip+":");
        System.out.println(help.toString());
        if(scanner.hasNext()){
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)){
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的"+tip+"!");

    }
    /*
        @author：kazuha
        @statement：操作步骤
        1.修改数据源包括地址密码信息，对应代码标记
        2.模块配置，可以修改包名
        3.修改模板（可以忽略）
    */
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir")+"/demo";
        globalConfig.setOutputDir(projectPath+"/src/main/java");
        globalConfig.setAuthor("kazuha");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true); //实体属性Swagger2 注解
        globalConfig.setBaseResultMap(true);    //XML ResultMap
        globalConfig.setBaseColumnList(true);   //XML columList
        //去掉service接口首字母的I，如DO为User则叫UserService
        globalConfig.setServiceName("%Service");
        mpg.setGlobalConfig(globalConfig);

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //一：修改数据源
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/dongda?serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        //二、模块配置
        packageConfig.setParent("com.kazuha")
                .setEntity("pojo")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        mpg.setPackageInfo(packageConfig);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        //如果模板引擎是 freemarker
        String templatePath = "templates/mapper.xml.flt";
        //如果模板引擎是velocity
        // String templatePath = "templates/mapper.xml.vm";

        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        //自定义配置会被优先输出
        focList.add(new FileOutConfig() {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输出文件名，如果你的 Entity 设置了前后缀、此处注意xml的名称会跟着发生变化
                return projectPath + "/src/main/resources/mapper" + packageConfig.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        //写于公共父类的公共字段
        strategy.setInclude(scanner("表名，多个逗号分隔").split(","));
        strategy.setControllerMappingHyphenStyle(true);

        //注意是否要去掉表的前缀
        //strategy.setTablePrefix("dongda");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
