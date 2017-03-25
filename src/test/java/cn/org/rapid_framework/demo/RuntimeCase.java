package cn.org.rapid_framework.demo;

import cn.org.rapid_framework.generator.GeneratorProperties;
import org.junit.Test;

/**
 * 类RuntimeCase.java的实现描述：
 *
 * @author chenyuan001 2017/3/25 15:01
 */
public class RuntimeCase {


    @Test
    public void openFolder() throws Exception {
        //打开文件夹
        // Runtime.getRuntime().exec("cmd.exe /c start "+ GeneratorProperties.getRequiredProperty("outRoot"));
        Runtime.getRuntime().exec("cmd.exe /c start D:/");
    }
}
