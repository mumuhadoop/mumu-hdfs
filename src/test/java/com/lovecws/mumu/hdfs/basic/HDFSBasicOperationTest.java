package com.lovecws.mumu.hdfs.basic;

import com.lovecws.mumu.hdfs.HDFSConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: hdfs目录测试
 * @date 2017-10-09 15:21
 */
public class HDFSBasicOperationTest {

    private static final Logger log = Logger.getLogger(HDFSBasicOperationTest.class);
    private HDFSBasicOperation hdfsDirOperation = new HDFSBasicOperation();

    @Test
    public void mkdirs() {
        hdfsDirOperation.mkdirs("soft");
    }

    @Test
    public void mkdir() {
        hdfsDirOperation.mkdir("readme.txt");
    }

    @Test
    public void listStatus() {
        hdfsDirOperation.listStatus("/user/Administrator/soft");
    }

    @Test
    public void rename() {
        hdfsDirOperation.rename("soft", "soft");
    }

    @Test
    public void exists() {
        hdfsDirOperation.exists("soft");
    }

    @Test
    public void delete() {
        hdfsDirOperation.delete("soft", true);
    }

    @Test
    public void copyFromLocal() {
        hdfsDirOperation.copyFromLocal("D:\\data\\webmagic\\feizl", "feizl");
    }

    @Test
    public void copyToLocal() {
        hdfsDirOperation.copyToLocal("/hadoop/hadoop-2.7.3.tar.gz", "D:\\hadoop\\hadoop-2.7.3.tar.gz");
    }

    @Test
    public void append() {
        hdfsDirOperation.append("README.txt", "lovecws");
    }

    @Test
    public void createNewFile() {
        hdfsDirOperation.createNewFile("README.txt");
    }

    @Test
    public void create() {
        hdfsDirOperation.create("LICENSE.txt", "babymm");
    }

    @Test
    public void open() {
        hdfsDirOperation.open("README.txt");
    }

    @Test
    public void sequenceFile() {
        hdfsDirOperation.sequenceFile(HDFSConfiguration.HDFS_URI +"/user/Administrator/sequence2");
        hdfsDirOperation.open(HDFSConfiguration.HDFS_URI +"/user/Administrator/sequence2");
    }

    @Test
    public void sequenceFileReader() {
        hdfsDirOperation.sequenceFileReader(HDFSConfiguration.HDFS_URI + "/user/Administrator/sequence");
    }

    @Test
    public void mapfile() {
        hdfsDirOperation.mapfile(HDFSConfiguration.HDFS_URI + "/user/Administrator/mapfile2");
    }

    @Test
    public void mapfileRead() {
        hdfsDirOperation.mapfileRead(HDFSConfiguration.HDFS_URI + "/user/Administrator/mapfile");
    }
}
