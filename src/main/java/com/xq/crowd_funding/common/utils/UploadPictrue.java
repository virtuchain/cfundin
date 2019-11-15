package com.xq.crowd_funding.common.utils;/*
    @auther yangjie
*/

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    /**
     * 将图片内容编码为字符串
     * @param multipartFile
     * @return
     */
    public  static  String encodeImageToBase64(MultipartFile multipartFile){
            byte [] bytes = null;
            try {
                bytes = multipartFile.getBytes();
            }catch (IOException e){
                e.printStackTrace();
            }
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(bytes).trim();
        }
    /**
     * 将图片 ( String ) 内容解码为输入流
     * @param base
     * @return
     */
    public static InputStream decodeBase64ToImage(String base) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodeBytes = null;
        try {
            decodeBytes = decoder.decodeBuffer(base);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(decodeBytes);
    }
}
