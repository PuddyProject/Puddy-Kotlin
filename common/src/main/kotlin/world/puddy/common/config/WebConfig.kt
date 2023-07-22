package world.puddy.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000", "http://puddy.shop.s3-website.ap-northeast-2.amazonaws.com/",
                "http://puddy.shop/", "https://dgsoy5jp5hqg3.cloudfront.net",
                "https://www.puddy.world/"
            )
            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH")
    }
}
