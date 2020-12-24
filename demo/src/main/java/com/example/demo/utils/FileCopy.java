package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileCopy {
    //1819
    private static final String PATH = "C:\\spring\\modules\\";
    private static final String DESTPATH = "C:\\spring\\modules2\\";
    private static int count = 0;
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        try {
            folder(PATH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       /* boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(10000);
                flag = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        /*File libFile = new File(DESTPATH);
        File[] libList = libFile.listFiles();
        log.info(Thread.currentThread().getName() + "---libList.length:\t" + libList.length);*/
        log.info("---------jieshu------");
    }

    /**
     * 遍历文件夹
     *
     * @param folderPath
     * @throws IOException
     */
    private static void folder(String folderPath) throws IOException {
        File folderFile = new File(folderPath);
        File[] fileList = folderFile.listFiles();
        if (fileList.length > 0) {
            out:
            for (File file : fileList) {
                if (file.isDirectory()) {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                folder(file.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } else {
                    if (!file.getName().contains(".")) {
                        break out;
                    }
                    if (".jar".equals(file.getName().substring(file.getName().indexOf(".")))) {
                        // jar复制
                        File dest = new File(DESTPATH + file.getName());
                        if (dest.exists()) {
                            // log.info(Thread.currentThread().getName()+"\t"+file.getPath());
                            dest = new File(DESTPATH + getRandom() + file.getName());
                            //copyFileUsingFileChannels(dest, new File(DESTPATH + file.getName()));
                        }
                        //log.info(Thread.currentThread().getName()+"\t"+file.getPath());
                        copyFileUsingFileChannels(file, new File(DESTPATH + file.getName()));
                        synchronized (file.getName()) {
                            count += 1;
                        }
                    }
                }
            }
        }
    }

    /**
     * 文件复制
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    private static String getRandom() {
        StringBuilder builder = new StringBuilder();
        String time = System.currentTimeMillis() + "";
        char[] ca = time.toCharArray();
        for (int i = 0; i < 3; i++) {
            int random = new Random().nextInt(ca.length);
            builder.append(ca[random]).append(random);
        }
        return builder.toString();
    }
}
