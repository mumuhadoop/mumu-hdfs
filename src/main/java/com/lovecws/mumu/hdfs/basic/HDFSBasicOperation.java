package com.lovecws.mumu.hdfs.basic;

import com.lovecws.mumu.hdfs.HDFSConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.DeflateCodec;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: hdfs基本操作
 * @date 2017-10-09 14:08
 */
public class HDFSBasicOperation {

    private static final Logger log = Logger.getLogger(HDFSBasicOperation.class);

    /**
     * 创建目录
     *
     * @param dir hadoop目录（支持绝对路径和相对路径）
     */
    public void mkdir(String dir) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            boolean mkdir = distributedFileSystem.mkdir(new Path(dir), FsPermission.getDefault());
            log.info(mkdir ? "hdfs目录创建成功" : "hdfs目录创建失败");
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * 创建目录 级联创建上级目录
     *
     * @param dir hadoop目录（支持绝对路径和相对路径）
     */
    public void mkdirs(String dir) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            boolean mkdir = distributedFileSystem.mkdirs(new Path(dir), FsPermission.getDefault());
            log.info(mkdir ? "hdfs目录创建成功" : "hdfs目录创建失败");
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 列出目录下的子目录或者文件
     *
     * @param dir hadoop目录
     */
    public void listStatus(String dir) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            FileStatus[] fileStatuses = distributedFileSystem.listStatus(new Path(dir));
            for (FileStatus fileStatus : fileStatuses) {
                log.info(fileStatus);
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 重命名目录
     *
     * @param dir     hadoop目录
     * @param distDir hadoop目录
     */
    public void rename(String dir, String distDir) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            boolean rename = distributedFileSystem.rename(new Path(dir), new Path(distDir));
            log.info(rename ? "hdfs目录重命名成功" : "hdfs目录重命名失败");
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 查看文件或者目录是否存在
     *
     * @param path
     */
    public void exists(String path) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            boolean exists = distributedFileSystem.exists(new Path(path));
            log.info(exists ? "hdfs【" + path + "】存在" : "hdfs【" + path + "】不存在");
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 删除文件
     *
     * @param path
     * @param recursive 是否级联删除
     */
    public void delete(String path, boolean recursive) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            boolean delete = distributedFileSystem.delete(new Path(path), recursive);
            log.info(delete ? "hdfs【" + path + "】删除成功" : "hdfs【" + path + "】删除失败");
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 将本地文件上传到hdfs
     *
     * @param src  本地文件
     * @param dist hdfs文件
     */
    public void copyFromLocal(String src, String dist) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            distributedFileSystem.copyFromLocalFile(new Path(src), new Path(dist));
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 将hdfs文件下载到本地
     *
     * @param src  hdfs文件
     * @param dist 本地文件
     */
    public void copyToLocal(String src, String dist) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            distributedFileSystem.copyToLocalFile(new Path(src), new Path(dist));
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 将hdfs文件下载到本地
     *
     * @param src    hdfs文件
     * @param append 追加内容
     */
    public void append(String src, String append) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        FSDataOutputStream fsDataOutputStream = null;
        try {
            fsDataOutputStream = distributedFileSystem.append(new Path(src));
            fsDataOutputStream.writeBytes(append);
            fsDataOutputStream.flush();
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                fsDataOutputStream.close();
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * hdfs创建新文件
     *
     * @param path hdfs文件
     */
    public void createNewFile(String path) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        try {
            if (!distributedFileSystem.exists(new Path(path))) {
                boolean newFile = distributedFileSystem.createNewFile(new Path(path));
                log.info(newFile ? "hdfs【" + path + "】文件创建成功" : "hdfs【" + path + "】文件创建失败");
            } else {
                log.info("hdfs【" + path + "】文件已存在");
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * hdfs创建文件并写入内容
     *
     * @param path    hdfs文件
     * @param content hdfs文件内容
     */
    public void create(String path, String content) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        FSDataOutputStream fsDataOutputStream = null;
        try {
            Path pt = new Path(path);
            if (!distributedFileSystem.exists(pt)) {
                fsDataOutputStream = distributedFileSystem.create(pt);
                fsDataOutputStream.writeBytes(content);
                fsDataOutputStream.flush();
                log.info("hdfs【" + path + "】文件创建成功，并且写入文件内容");
            } else {
                log.info("hdfs【" + path + "】文件已存在");
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                if (fsDataOutputStream != null) {
                    fsDataOutputStream.close();
                }
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 查看文件内容
     *
     * @param path hdfs文件
     */
    public void open(String path) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        FSDataInputStream fsDataInputStream = null;
        try {
            Path pt = new Path(path);
            if (distributedFileSystem.exists(pt)) {
                fsDataInputStream = distributedFileSystem.open(pt);
                byte[] bs = new byte[fsDataInputStream.available()];
                fsDataInputStream.read(bs);
                log.info(new String(bs));
            } else {
                log.info("hdfs【" + path + "】文件不存在");
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                if (fsDataInputStream != null) {
                    fsDataInputStream.close();
                }
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 顺序写文件
     *
     * @param path
     */
    public void sequenceFile(String path) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        SequenceFile.Writer writer = null;
        IntWritable intWritable = new IntWritable();
        Text text = new Text();
        try {
            //writer = SequenceFile.createWriter(distributedFileSystem, new Configuration(), new Path(path), intWritable.getClass(), text.getClass());
            writer = SequenceFile.createWriter(new Configuration(), SequenceFile.Writer.file(new Path(path)), SequenceFile.Writer.compression(SequenceFile.CompressionType.RECORD), SequenceFile.Writer.keyClass(IntWritable.class), SequenceFile.Writer.valueClass(Text.class));
            for (int i = 1; i <= 1000; i++) {
                intWritable.set(i);
                text.set("lovecws" + String.valueOf(i));
                writer.append(intWritable, text);
            }
            writer.sync();
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                writer.close();
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 顺序写文件
     *
     * @param path
     */
    public void sequenceFileReader(String path) {
        DistributedFileSystem distributedFileSystem = new HDFSConfiguration().distributedFileSystem();
        SequenceFile.Reader reader = null;
        try {
            Configuration configuration = new Configuration();
            //reader = new SequenceFile.Reader(distributedFileSystem, new Path(path), configuration);
            reader = new SequenceFile.Reader(configuration, SequenceFile.Reader.file(new Path(path)));

            Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
            Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), configuration);
            long position = reader.getPosition();
            //reader.seek(10);
            while (reader.next(key, value)) {
                String screen = reader.syncSeen() ? "*" : "";
                System.out.printf("[%s%s]\t%s\t%s\n", position, screen, key, value);
                position = reader.getPosition();
            }
        } catch (IOException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                distributedFileSystem.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

    /**
     * 写入mapfile
     *
     * @param path
     */
    public void mapfile(String path) {
        Configuration configuration = new Configuration();
        //configuration.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
        MapFile.Writer writer = null;
        try {
            writer = new MapFile.Writer(configuration, new Path(path), MapFile.Writer.keyClass(IntWritable.class), MapFile.Writer.valueClass(Text.class), MapFile.Writer.compression(SequenceFile.CompressionType.NONE, new DeflateCodec()));
            for (int i = 1; i <= 10000; i++) {
                writer.append(new IntWritable(i), new Text("lovecws" + i));
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取mapfile文件
     *
     * @param path
     */
    public void mapfileRead(String path) {
        Configuration configuration = new Configuration();
        //configuration.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
        MapFile.Reader reader = null;
        try {
            reader = new MapFile.Reader(new Path(path), configuration);
            IntWritable key = new IntWritable();
            Text value = new Text();
            while (reader.next(key, value)) {
                System.out.printf("[%s%s]\t%s\t%s\n", reader.midKey(), "", key, value);
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
