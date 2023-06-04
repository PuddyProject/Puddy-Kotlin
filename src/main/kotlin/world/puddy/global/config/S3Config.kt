package world.puddy.global.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {

    @Value("\${cloud.aws.credentials.access-key}")
    private val s3AccessKey: String? = null

    @Value("\${cloud.aws.credentials.secret-key}")
    private  var s3SecretKey: String? = null

    @Value("\${cloud.aws.region.static}")
    private var s3Region: String? = null // Bucket Region

    @Bean
    fun amazonS3Client(): AmazonS3Client? {
        val awsCreds = BasicAWSCredentials(s3AccessKey, s3SecretKey)
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .withRegion(s3Region)
            .build() as AmazonS3Client
    }

}