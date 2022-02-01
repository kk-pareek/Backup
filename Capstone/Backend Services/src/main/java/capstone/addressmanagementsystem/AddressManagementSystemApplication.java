package capstone.addressmanagementsystem;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AddressManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressManagementSystemApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("capstone.addressmanagementsystem"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "ADDRESS MANAGEMENT SYSTEM",
                "An API to manage addresses: allows user to Create, Read, Update & Delete addresses in database.",
                "Group-A",
                "",
                new springfox.documentation.service.Contact("", "https://www.tavant.com/", ""),
                "",
                "",
                Collections.emptyList());
    }
}
