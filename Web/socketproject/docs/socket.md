# Socket编程
[Socket](https://www.zhihu.com/question/29637351/answer/67610424)

socket实际上相当于一根网线，连接了两个电脑，你可以称其中一个为 “客户端”，另一个为“服务端”，但实际上没有太大的差别。

就拿：

```java
socket.getInetAddress();//对面的地址
socket.getLocalAddress();//自己的地址
socket.getPort();//对面的进程的端口
socket.getLocalPort();//自己进程的端口
```

关于流操作：

```java
socket.getInputStream();//得到的是一个inputstream，可以获得对方发过来的信息流
socket.getOutputStream();//得到的是一个outputstream，可以将己方的信息流放到socket上传给对方
```

