package com.xx.webframework.restapi;

import com.github.pagehelper.Page;
import com.xx.webframework.restapi.common.ApiException;
import com.xx.webframework.restapi.common.ResponseData;
import com.xx.webframework.restapi.common.UserNotFoundApiException;
import com.xx.webframework.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
public class HomeController {

    @Autowired(required = false)
    ProductService productService;

    // 缓存json数据
    private Map<String,String> cacheMap = new ConcurrentHashMap<>();

    @RequestMapping(method = RequestMethod.GET,value = "/product/list")
    public ResponseData getProductList(){

        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setMessaage("操作成功");
        int pageNum = 1;
        int pageSize = 10;
        responseData.setData(productService.getProductPage(new Page(pageNum,pageSize)));

        return responseData;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/exception")
    public ResponseData getProductList2(){
        throw new UserNotFoundApiException();
    }

    /**
     * mock data response
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{filename}")
    public String process(@PathVariable("filename") String filename){
        if(null == cacheMap.get(filename)){
            String jsonData = readFileContent(filename);
            if(StringUtils.isNoneBlank(jsonData)) {
                log.info("put {} to cache data",filename);
                cacheMap.put(filename, jsonData);
            }
        }
        return cacheMap.get(filename);
    }

    private String readFileContent(String filename) {
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(String.format("json/%s.json",filename));
        if(is != null){
            try{
                byte[] bytes = new byte[is.available()];
                IOUtils.readFully(is,bytes);
                return new String(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    @GetMapping("/")
    public Map<String, Object> greeting() {
        return Collections.singletonMap("message", "Hello World");
    }

}

