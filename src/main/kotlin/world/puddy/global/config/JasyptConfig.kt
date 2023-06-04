package world.puddy.global.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableEncryptableProperties
class JasyptConfig {

    @Value("\${jasypt.encryptor.password}")
    private var encryptKey: String? = null

    @Bean(name = ["jasyptStringEncryptor"])
    fun stringEncryptor(): StringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.password = encryptKey
        config.algorithm = "PBEWithMD5AndDES"
        config.setKeyObtentionIterations("1000")
        config.setPoolSize("1")
        config.providerName = "SunJCE"
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator")
        config.stringOutputType = "base64"
        encryptor.setConfig(config)
        return encryptor
    }
}