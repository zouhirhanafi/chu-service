//package ma.ensaf.ekili.config;
//
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SpringFoxConfig {
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "CHU REST API",
//                "Some custom description of CHU API.",
//                "API CHU",
//                "Terms of service : CHU API",
//                new Contact("John Doe", "www.example.com", "myeaddress@company.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList());
//    }
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
////    /**
////     * SwaggerUI information
////     */
////    @Bean
////    UiConfiguration uiConfig() {
////        return UiConfigurationBuilder.builder()
////                .deepLinking(true)
////                .displayOperationId(false)
////                .defaultModelsExpandDepth(1)
////                .defaultModelExpandDepth(1)
////                .defaultModelRendering(ModelRendering.EXAMPLE)
////                .displayRequestDuration(false)
////                .docExpansion(DocExpansion.NONE)
////                .filter(false)
////                .maxDisplayedTags(null)
////                .operationsSorter(OperationsSorter.ALPHA)
////                .showExtensions(false)
////                .tagsSorter(TagsSorter.ALPHA)
////                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
////                .validatorUrl(null)
////                .build();
////    }
////
////    @Bean
////    public EmailAnnotationPlugin emailPlugin() {
////        return new EmailAnnotationPlugin();
////    }
//}