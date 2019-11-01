package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.common.utils.UploadPictrue;
import com.xq.crowd_funding.raisefunding.servieces.PictureServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 文件上传的类
 */
@Service
public class PictureSerImpl implements PictureServer {

    @Value("${oss.project.parent.folder}")
    private String folder;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.bucket.domain}")
    private String domain;
    /**
     * 上传头图片服务
     * @param headFile
     * @return
     */
    @Override
    public ResultEntity<String> uploadHeadPicture(MultipartFile headFile) {
        String newFileName = CrowdUtils.getNewFileName(headFile.getOriginalFilename());
        String folderFileName = CrowdUtils.folderFileName(CrowdUtils.getNewFoldeName(),newFileName);
        try {
            InputStream inputStream = new FileInputStream(new File(headFile.getName()));
            UploadPictrue.uploadOneFileToOSS(
               endpoint,accessKeyId,accessKeySecret,folderFileName,folder,bucketName,inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  ResultEntity.failed("上传失败");
        }
        return ResultEntity.successWithData(folderFileName);
    }
}
