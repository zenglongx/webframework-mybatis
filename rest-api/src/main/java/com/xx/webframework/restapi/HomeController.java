package com.xx.webframework.restapi;

import com.github.pagehelper.Page;
import com.xx.webframework.domain.Product;
import com.xx.webframework.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Slf4j
public class HomeController {

    @Autowired
    ProductService productService;

    // 缓存json数据
    private Map<String,String> cacheMap = new ConcurrentHashMap<>();

    @RequestMapping(method = RequestMethod.GET,value = "/product/list")
    public List<Product> getProductList(){
//        return productService.findAll();
        int pageNum = 1;
        int pageSize = 10;
        return productService.getProductPage(new Page(pageNum,pageSize));
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
}

