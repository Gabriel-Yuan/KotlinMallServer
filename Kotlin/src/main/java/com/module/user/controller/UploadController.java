package com.module.user.controller;

import com.module.user.domain.BaseResp;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(produces = {"application/json; charset=UTF-8"}, value = {
        "/common"})
public class UploadController extends BaseController {
    @RequestMapping(value = {"/getUploadToken"}, method = {RequestMethod.POST})
    @ResponseBody
    public BaseResp<String> getUploadToken() {
        String accessKey = "eBBGD0llZHPFNo1D-dK3_JSztl8dMVp7ioBsus9p";
        String secretKey = "IuN1DDfwaCW0xMX4QLRQHs1o7RhefZxl4gzPMQkh";
        String bucket = "photos";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);

        BaseResp resp = new BaseResp();
        resp.setStatus(0);
        resp.setMessage("");
        resp.setData(upToken);

        return resp;
    }
}

