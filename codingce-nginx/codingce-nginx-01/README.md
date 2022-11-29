# 1.准备工作
结合本文场景，需要安装Nginx和Java环境（运行SpringBoot项目）

## 1.1 关于Mac系统安装Nginx

本次用的是 homebrew 安装的

[Mac下使用brew安装nginx](https://blog.csdn.net/weixin_43874301/article/details/115641598?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162089135716780366547932%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=162089135716780366547932&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_v2~rank_v29-2-115641598.pc_v2_rank_blog_default&utm_term=nginx&spm=1018.2226.3001.4450)

也可前去官网下载

## 1.2 测试项目说明
由于只是测试, SpringBoot只是映射了根路径，端口分别是 10001 和 10002 ，分别返回 demo1 和 demo2 字符串作为区分。

# 2.Nginx负载均衡的集中方式介绍
## 2.1 轮询
轮询方式是Nginx负载`默认`的方式，顾名思义，所有请求都按照时间顺序分配到不同的服务上，如果服务Down掉，可以自动剔除，如下配置后轮训10001服务和10002服务。

```yml
upstream  dalaoyang-server {
       server    localhost:10001;
       server    localhost:10002;
}
```

## 2.2 权重
指定每个服务的权重比例，weight和访问比率成正比，通常用于后端服务机器性能不统一，将性能好的分配权重高来发挥服务器最大性能，如下配置后10002服务的访问比率会是10001服务的二倍。

```yml
upstream  dalaoyang-server {
       server    localhost:10001 weight=1;
       server    localhost:10002 weight=2;
}
```

## 2.3 iphash
每个请求都根据访问ip的hash结果分配，经过这样的处理，每个访客固定访问一个后端服务，如下配置（ip_hash可以和weight配合使用）。

```yml
upstream  dalaoyang-server {
       ip_hash; 
       server    localhost:10001 weight=1;
       server    localhost:10002 weight=2;
}
```
## 2.4 最少连接
将请求分配到连接数最少的服务上。
```yml
upstream  dalaoyang-server {
       least_conn;
       server    localhost:10001 weight=1;
       server    localhost:10002 weight=2;
}
```
## 2.5 fair
按后端服务器的响应时间来分配请求，响应时间短的优先分配。
```yml
upstream  dalaoyang-server {
       server    localhost:10001 weight=1;
       server    localhost:10002 weight=2;
       fair;  
}
```


# 3.测试(以轮询为例子)
重启nginx，第一次 访问http://localhost:10000如图所示，

轮询方式，刷新后依次切换服务
![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img/nginx01.png)
![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img/nginx02.png)

如果要修改负载均衡算法修改对应upstream模块即可。

此次nginx路径展示

主配置与自定义配置
![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img/myconf01.png)

轮询配置 在nginx.conf文件中配置的
```yml
upstream codingce-nginx {
       server localhost:10001;
       server localhost:10002;
}
```
![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img/myconf02.png)


进入自定义配置文件夹配置自定义配置
```bash
williamma@WilliamdeMacBook-Pro nginx % cd vhost 
williamma@WilliamdeMacBook-Pro vhost % ll
total 16
drwxr-xr-x   4 williamma  admin  128  5 13 15:17 ./
drwxr-xr-x  19 williamma  admin  608  5 13 15:28 ../
-rw-r--r--   1 williamma  admin  549  4 16 16:17 gulimall.conf
-rw-r--r--   1 williamma  admin  167  5 13 15:17 mytest.conf
williamma@WilliamdeMacBook-Pro vhost % vim mytest.conf 
```

配置文件展示
```bash
server {

    listen       10000;
    server_name  localhost;


    location / {
    	#用在此处
        proxy_pass http://codingce-nginx;
        proxy_redirect default;
    }

}
```

# 测试SpringBoot项目地址

[GitHub](https://github.com/xzMhehe/codingce-java)     
[Gitee](https://gitee.com/codingce/codingce-java)

>如有问题欢迎评论区讨论