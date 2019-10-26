# Server
1. 创建ServerSocket
2. 在套接字上监听客户连接请求
3. 接受到之后，处理
4. 将处理结果返回给client
5. 关闭（服务端一般不关闭）
# Client
1. 创建Socket(host,port);指定要访问的服务器的地址和端口
2. 发送连接请求
3. 发送数据（服务要求）
4. 接受服务数据
5. 关闭socket

# 注意
## 和UDP的区别
模型上的区别：UDP是快递模型，寄件人和收件人的地址都在包裹上写明；   
TCP不是：面向连接——所有的信息都是连接本身携带的

服务端必须指定建立的端口，需要提供给客户端，让客户端通过该端口访问

客户端