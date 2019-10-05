
# 接收端
1. 使用DatagramSocket指定接受端口
2. 准备容器 封装成DatagramPacket包裹 DatagramPacket​(byte[] buf, int length)；接受容器不需地址参数
3. 阻塞式 接受方法 receive(DatagramPacket p);
4. 得到数据：getData();
5. 释放资源
# 发送端
1. 使用DatagramSocket指定发送端口
2. 准备数据 封装成DatagramPacket包裹，**构造函数使用带地址**的，地址即为 目的地 DatagramPacket​(byte[] buf, int offset, int length, InetAddress address, int port)
3. 非阻塞式 发送（不管对方是否收到） receive(DatagramPacket p);
4. 得到数据：getData();
5. 释放资源


# 注意
严格来讲没有发送和接受

都是发送也都是接受

## 注意
快递员（socket）携带包裹（DatagramPakcet），包裹上有收件人的地址和端口，***而不是***快递员有地址和端口   
（甚至也包括了寄件人的地址和端口：貌似将sendSocket指定（或随机分配）的寄件人的地址和端口信息 在执行sendSocket(sendPacket);的时候“印”在了包裹上）

所以在发送的时候，是否指定端口并不重要

所以在监听的时候，监听的是对面当时指定的地址和端口

其实按照快递模型来收，应该是收件人告诉了寄件人自己的地址，然后寄件人才****在包裹上****写上了对方的地址（以及自己的地址）