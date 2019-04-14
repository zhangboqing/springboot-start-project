package com.example.startdemo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/8/6
 *
 * swagger2配置
 *
 * 访问地址：
 * http://localhost:8080/swagger-ui.html
 * http://localhost:8080/doc.html  (带有文档)
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket docket() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("X-Auth-Token").description("登录授权")
                .modelRef(new ModelRef("string")).parameterType("header")
                //header中的ticket参数非必填，传空也可以
                .required(false).build();

        //根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(" api doc")
                .apiInfo(apiInfo()).select()
                //当前包路径
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any()).build().globalOperationParameters(pars);
//                .pathMapping();
    }


    //构建api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("使用Swagger2构建RESTful API")
                //创建人
                .contact(new Contact("张波清", "756623607@qq.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

}