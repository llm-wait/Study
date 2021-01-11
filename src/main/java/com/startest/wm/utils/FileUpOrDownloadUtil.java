package com.startest.wm.utils;


import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-02-15:31
 * @描述 文件上传下载帮助类
 */
public class FileUpOrDownloadUtil {

    /**
     * 上传文件
     * @param is
     * @param targetPath
     * @throws IOException
     */
    public static void upFile(InputStream is,String targetPath) throws IOException {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        in = new BufferedInputStream(is);
        out = new BufferedOutputStream(new FileOutputStream(targetPath));
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
    }

    /**
     * 文件下载
     * @param fileName
     * @param sourcefile
     * @param httpServletResponse
     * @throws IOException
     */
    public static void downloadFile(String fileName,String sourcefile, HttpServletResponse httpServletResponse) throws IOException {
        BufferedInputStream in = null;
        in = new BufferedInputStream(new FileInputStream(sourcefile));
        String fileName1 = fileName;
        if(fileName.contains(".")){
            fileName1 = fileName.substring(0,fileName.lastIndexOf("."));
        }
        String fileEnd = sourcefile.substring(sourcefile.lastIndexOf("."));
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName1.getBytes(), "iso8859-1") + fileEnd);
        //httpServletResponse.setContentType("application/octet-stream;charset=UTF-8");

        OutputStream outputStream = httpServletResponse.getOutputStream();
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            outputStream.write(b, 0, len);
        }
        in.close();
        outputStream.flush();
        outputStream.close();
    }
}
