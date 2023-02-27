package com.gjh.util;

import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.util.Map;

public class WbUtils {
    public static <T> T copyParamToBean(Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);
            BeanUtils.populate(bean, value); System.out.println("注入之后：" + bean);
        } catch (Exception e) { e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {

        }
        return defaultValue;
    }
    public  static void deleteFile(String s){
        File file=new File(s);
        file.delete();
    }
}
