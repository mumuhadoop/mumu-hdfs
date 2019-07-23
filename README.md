## mumu-hdfs hadoop分布式文件系统
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/mumuhadoop/mumu-hdfs/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/mumuhadoop/mumu-hdfs.svg?branch=master)](https://travis-ci.org/mumuhadoop/mumu-hdfs)
[![codecov](https://codecov.io/gh/mumuhadoop/mumu-hdfs/branch/master/graph/badge.svg)](https://codecov.io/gh/mumuhadoop/mumu-hdfs)
[![Documentation Status](https://readthedocs.org/projects/mumu-hdfs/badge/?version=latest)](https://mumu-hdfs.readthedocs.io/en/latest/?badge=latest)  
***Hadoop分布式文件系统(HDFS)被设计成适合运行在通用硬件(commodity hardware)上的分布式文件系统。它和现有的分布式文件系统有很多共同点。但同时，它和其他的分布式文件系统的区别也是很明显的。HDFS是一个高度容错性的系统，适合部署在廉价的机器上。HDFS能提供高吞吐量的数据访问，非常适合大规模数据集上的应用。HDFS放宽了一部分POSIX约束，来实现流式读取文件系统数据的目的。HDFS在最开始是作为Apache Nutch搜索引擎项目的基础架构而开发的。HDFS是Apache Hadoop Core项目的一部分。***

![hdfs架构图](http://hadoop.apache.org/docs/r1.0.4/cn/images/hdfsarchitecture.gif)  

## hdfs基本特征
- 大规模分布式存储，hdfs以分布式存储和良好的可拓展性提供了大规模的数据存储能力。
- 高并发访问能力，hdfs以多节点并发访问方式提供很高的数据访问带宽。
- 强大的容错能力，hdfs采用多副本数据块存储，按照块的形式随机选择存储节点。
- 顺序式访问文件，hdfs对顺序读进行了优化，支持大数据量的顺序读写，代价是对随机度的性能下降。
- 简单的一致性模型，hdfs提供一次写入到此读取的模式，不提供对hdfs文件的修改，但是可以在文件末尾附加新数据。
- 数据块存储，hdfs采用基于大数据块的文件存储，默认的块大小为64mb，较少源数据存储，分布存储在不同的服务器上。

## 相关阅读  
[hadoop官网文档](http://hadoop.apache.org)  
[hadoop hdfs分布式文件系统](http://hadoop.apache.org/docs/r1.0.4/cn/hdfs_design.html)  
## 联系方式
**以上观点纯属个人看法，如有不同，欢迎指正。  
email:<babymm@aliyun.com>  
github:[https://github.com/babymm](https://github.com/babymm)**