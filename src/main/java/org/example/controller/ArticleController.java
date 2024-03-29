package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Result;
import org.example.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token , HttpServletResponse response*/){
        //驗證token
//        try {
//            Map<String,Object> claims = JwtUtil.parseToken(token);
            return Result.success("所有文章順序");
//        }catch (Exception e){
//            //http狀態碼401
//            response.setStatus(401);
//            return Result.error("尚未登入");
//        }
    }

}
