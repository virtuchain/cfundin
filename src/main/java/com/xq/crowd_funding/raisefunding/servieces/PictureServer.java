package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PictureServer {

    ResultEntity<String> uploadHeadPicture(MultipartFile headFile);

}
