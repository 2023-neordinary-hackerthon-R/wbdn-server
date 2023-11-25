package neordinaryr.wbdn.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.config.AmazonConfig;
import neordinaryr.wbdn.global.exception.BaseException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;

    public String uploadFile(String keyName, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public void deleteFile(String keyName) {
        if (amazonS3.doesObjectExist(amazonConfig.getBucket(), keyName)) {
            amazonS3.deleteObject(amazonConfig.getBucket(), keyName);
        } else {
            throw BaseException.of(ErrorCode.S3_OBJECT_NOT_FOUND);
        }
    }

    public String generatePostPhotoKeyName(String fileName) {
        return amazonConfig.getPostPhoto() + '/' + fileName;
    }

    public String generateUserProfileImageKeyName(String fileName) {
        return amazonConfig.getUserProfileImage() + '/' + fileName;
    }

    public String getKeyName(String fileUrl) {
        Pattern regex = Pattern.compile(getPattern());
        Matcher matcher = regex.matcher(fileUrl);

        String keyName = null;
        if (matcher.find()) {
            keyName = matcher.group(1);
        }

        return keyName;
    }

    private String getPattern() {
        return "https://" + amazonConfig.getBucket() + "\\.s3\\." + amazonConfig.getRegion() + "\\.amazonaws\\.com(.*)";
    }
}
