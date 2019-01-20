package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.emnus.ResultStatus;
import com.qinyadong.shopping.utils.FastDFSClient;
import com.qinyadong.shopping.utils.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureUpLoadController {

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile) {
        Result<String> result = new Result<>();
        result.setStatus(ResultStatus.REQUEST_PARAM_ERROR);
        result.setSuccess(false);
        String[] upload = null;
        if (uploadFile == null) {
            result.setMessage("至少上传一张图片");
            return result;
        }
        //得到一个图片的地址和扩展名
        try {
            byte[] fileByte = null;
            String originalFilename = uploadFile.getOriginalFilename();
            String extFilename = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            InputStream inputStream = uploadFile.getInputStream();
            if (inputStream != null) {
                fileByte = new byte[inputStream.available()];
                inputStream.read(fileByte);
            }
            inputStream.close();
            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename, fileByte, extFilename);

            try {
                upload = FastDFSClient.upload(fastDFSFile);
            } catch (Exception e) {
                log.error("上传文件异常!",e);
                e.printStackTrace();
                result.setMessage("文件上传异常");
                return result;
            }
            if (ArrayUtils.isEmpty(upload)) {
                log.error("上传文件失败，请再次上传");
                result.setMessage("上传文件失败，请再次上传");
                return result;
            }
            String path=FastDFSClient.getTrackerUrl()+upload[0]+ "/"+upload[1];
            result.setMessage("文件上传成功");
            result.setSuccess(true);
            result.setStatus(ResultStatus.SUCCESS);
            result.setData(path);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("系统错误");
            return result;
        }
    }
}
