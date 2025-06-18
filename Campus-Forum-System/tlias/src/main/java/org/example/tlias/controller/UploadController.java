package org.example.tlias.controller;

import org.example.tlias.utils.Result;
import org.example.tlias.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UploadController {
    @Autowired
    AliOSSUtils aliOSSUtils;

    /*    @PostMapping("/upload")
        Result upload(@RequestParam("image") MultipartFile file) throws IOException {
            String orginFileName= file.getOriginalFilename();
            assert orginFileName != null;
            String newFileName = UUID.randomUUID() +orginFileName.substring(orginFileName.lastIndexOf("."));
            file.transferTo(new File("D:/image/"+newFileName));
            return Result.success();
        }*/
    @PreAuthorize("hasAuthority('CAN_POST')")
    @PostMapping("/upload")
    Result upload( MultipartFile file) throws IOException {
        String url = aliOSSUtils.upload(file);
        return Result.success(url);
    }
}
