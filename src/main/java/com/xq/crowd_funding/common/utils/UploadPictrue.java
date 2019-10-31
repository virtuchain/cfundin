package com.xq.crowd_funding.common.utils;/*
    @auther yangjie
*/

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;

import java.io.InputStream;

/**
 * 上传图片
 */
public class UploadPictrue {
    /**
     * 上传一张图片到OSS
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     * @param fileName
     * @param folderName
     * @param bucketName
     * @param inputStream
     */
        public static void uploadOneFileToOSS(
                String endpoint, String accessKeyId, String accessKeySecret, String fileName,
                          String folderName, String bucketName, InputStream inputStream){
            try {
                // 创建 ossclient实例
                OSSClient ossClient = new OSSClient(endpoint , accessKeyId ,accessKeySecret);
                // 存入对象的名称 = 目录名+ 文件名
                String objectName = folderName+"/"+fileName;
                ossClient.putObject(bucketName , objectName , inputStream);
                // 关闭 ossclient
                ossClient.shutdown();
            }catch (OSSException e){
                e.printStackTrace();
                throw  new RuntimeException(e.getMessage());
            }catch (ClientException e){
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
}
