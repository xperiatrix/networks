# networks
Debug Test and Restart Over Again.
Every Liitle Thing You Do Is Your Own Leverage In Future. 

## Three-Way Handshakes in TCP communication. 
The Detail of Three-Way Handshakes on Connection-Estiblishing in Transport-Control-Protocol

![](./src/structuremap.png)

## Easy FTP-Server Decision for R&D. 
Be very careful that the default value of the max-connections of SimpleHttpServer is 5 on Linux. That's why you'd better setup an Apache-Server instead of SimpleHttpServer as an FTP-Server in LAN, because the default max_conns of Apache-Server is 100 on Linux.

```
ss -lnt | grep -i your_simple_http_server_port  // default max_conn is 5 on linux
ss -lnt | grep -i your_apache_http_server_port  // default max_conn is 100 on linux
```



## Thanks so much for references
* [就是要你懂TCP--半连接队列和全连接队列](https://plantegg.github.io/2017/06/07/%E5%B0%B1%E6%98%AF%E8%A6%81%E4%BD%A0%E6%87%82TCP--%E5%8D%8A%E8%BF%9E%E6%8E%A5%E9%98%9F%E5%88%97%E5%92%8C%E5%85%A8%E8%BF%9E%E6%8E%A5%E9%98%9F%E5%88%97/)

