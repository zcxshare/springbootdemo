package com.example.springbootdemo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtils {
    public static final String ICON_IMAGE = "iconImage";

    public static File saveFile(File directory, String fileName, MultipartFile file) {
        if (!directory.isDirectory()) directory.delete();
        if (!directory.exists()) directory.mkdirs();
        File saveFile = new File(directory, fileName);
        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            file.transferTo(saveFile);
            return saveFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile,String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = Constants.getUUID() + ".png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }
}
