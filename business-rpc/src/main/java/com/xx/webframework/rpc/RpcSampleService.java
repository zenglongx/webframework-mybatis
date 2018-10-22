package com.xx.webframework.rpc;

import com.alibaba.dubbo.config.annotation.Service;

@Service(
    version = "1.0.0"
)
public class RpcSampleService implements ISampleService{
    public String sayHello(){
        return "hello";
    }
}
