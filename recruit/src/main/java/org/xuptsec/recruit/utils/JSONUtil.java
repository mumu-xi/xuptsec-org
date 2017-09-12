package org.xuptsec.recruit.utils;

import com.alibaba.fastjson.JSONObject;
import org.xuptsec.recruit.poji.ResultJoin;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mu on 2017/9/12.
 */
public class JSONUtil {

    public static JSONObject stringToJSONObject(HttpServletRequest httpServletRequest) throws IOException {
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), "utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //将json字符串转换为json对象
        return (JSONObject) JSONObject.parse(sb.toString());
    }

    public static String objectToJSONString(ResultJoin resultJoin) {
        return JSONObject.toJSONString(resultJoin).toString();
    }
}
