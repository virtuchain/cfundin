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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
     * 上传头图片 OSS 服务
     * @param headFile
     * @return
     */
    @Override
    public ResultEntity<String> uploadHeadPicture(MultipartFile headFile) {

        String originalFilename =  headFile.getOriginalFilename();
        String newFileName =  CrowdUtils.getNewFileName(originalFilename);
        String newfolderName = CrowdUtils.getNewFoldeNameByDate(folder);
       try {
           InputStream inputStream = headFile.getInputStream();

           UploadPictrue.uploadOneFileToOSS(
                   endpoint,accessKeyId,accessKeySecret,newFileName,
                   newfolderName,bucketName,inputStream);

       }catch (Exception e){
           e.printStackTrace();
       }
       // 头图片路径
        String headPicturePath = bucketName+"/"+newfolderName+"/"+newFileName;
        System.out.println("headPicturePath "+headPicturePath);
        return ResultEntity.successWithData(headPicturePath);
    }

    /**
     * 上传详情图片
     * @param detailFiles
     * @return
     */
    public ResultEntity uploadDetailPicture(List<MultipartFile> detailFiles){

        List<String> detailicturePathList = new ArrayList<>();

        for(MultipartFile detailFile:detailFiles ){

            System.out.println("detailFile："+detailFile.getOriginalFilename());
           try {
               String originalFilename =  detailFile.getOriginalFilename();
               String newFileName =  CrowdUtils.getNewFileName(originalFilename);
               String newfolderName = CrowdUtils.getNewFoldeNameByDate(folder);
               InputStream inputStream = detailFile.getInputStream();

               UploadPictrue.uploadOneFileToOSS(
                       endpoint,accessKeyId,accessKeySecret,newFileName,
                       newfolderName,bucketName,inputStream);
               // 详情图片路径
               detailicturePathList.add(bucketName+"/"+newfolderName+"/"+newFileName);
           }catch (Exception e){
               e.printStackTrace();
           }
        }
        System.out.println("detailicturePathList "+detailicturePathList);
        return ResultEntity.successWithData(detailicturePathList);
    }
}
