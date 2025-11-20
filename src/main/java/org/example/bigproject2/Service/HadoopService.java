package org.example.bigproject2.Service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class HadoopService {
    private static final String HDFS_PATH = "/user/hadoop/images2";

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        try{
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(HDFS_PATH + "/" + fileName);
            FSDataOutputStream out = fs.create(path,true);
            InputStream in = file.getInputStream();
            IOUtils.copyBytes(in,out,conf);
            in.close();
            out.close();
            return fileName;
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("HDFS upload failed");
        }
    }
    public byte[] getImage(String fileName) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(HDFS_PATH + "/" + fileName);
        FSDataInputStream in = fs.open(path);
        byte[] data = in.readAllBytes();
        in.close();
        return data;
    }
}
