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

    @Value("\${cloud.aws.s3.bucket.name}")
    private lateinit var buckName: String

    fun uploadToS3(command: UploadFileCommand): String {
        val metadata = createMetaDate(command.file)
        amazonS3Client.putObject(
            PutObjectRequest(buckName, command.folderName + "/" + command.fileName, command.file.inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
        return amazonS3Client.getUrl(buckName, command.folderName + "/" + command.fileName).toString()
    }

    private fun convertToStoredName(originalName: String): String {
        return UUID.randomUUID().toString() + originalName
    }

    protected fun createMetaDate(file: MultipartFile): ObjectMetadata {
        val objectMetaData = ObjectMetadata()
        objectMetaData.contentType = file.contentType
        objectMetaData.contentLength = file.size
        return objectMetaData
    }
}
