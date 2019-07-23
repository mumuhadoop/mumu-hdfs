package com.lovecws.mumu.hdfs;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: 配置测试
 * @date 2017-10-09 15:18
 */
public class HDFSConfigurationTest {

    private static final Logger log = Logger.getLogger(HDFSConfigurationTest.class);
    private HDFSConfiguration hdfsConfiguration = new HDFSConfiguration();

    @Test
    public void distributedFileSystem() {
        DistributedFileSystem distributedFileSystem = hdfsConfiguration.distributedFileSystem();
        try {
            FsStatus status = distributedFileSystem.getStatus();
            log.info(JSON.toJSONString(status));
            RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = distributedFileSystem.listFiles(new Path("/"), false);
            while (locatedFileStatusRemoteIterator.hasNext()) {
                LocatedFileStatus fileStatus = locatedFileStatusRemoteIterator.next();
                log.info(fileStatus.toString());
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
