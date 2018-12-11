package com.xx.webframework.restapi.util;

import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作工具类
 */
public class FileUtils extends FileSystemUtils {


    public static String readString(File file) {
        try {
            return org.apache.commons.io.FileUtils.readFileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
