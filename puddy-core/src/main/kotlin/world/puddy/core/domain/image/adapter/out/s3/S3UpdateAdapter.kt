package world.puddy.core.domain.image.adapter.out.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class S3UpdateAdapter(
    private val amazonS3Client: AmazonS3Client
) {

    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucketName: String

    fun uploadToS3(command: UploadFileCommand): S3ImageInfomation {
        val metadata = createMetaDate(command.file)
        val storedName = convertToStoredName(command.fileName)
        amazonS3Client.putObject(
            PutObjectRequest(bucketName, command.folderName + "/" + storedName, command.file.inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
        return S3ImageInfomation(
            storedName,
            amazonS3Client.getUrl(bucketName, command.folderName + "/" + command.fileName).toString()
        )
    }

    protected fun createMetaDate(file: MultipartFile): ObjectMetadata {
        val objectMetaData = ObjectMetadata()
        objectMetaData.contentType = file.contentType
        objectMetaData.contentLength = file.size
        return objectMetaData
    }

    private fun convertToStoredName(originalName: String): String {
        return UUID.randomUUID().toString() + originalName
    }
}
