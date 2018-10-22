### rabbit docker start

```
# 利用docker hub 镜像启动 默认用户名密码 gust/gust
docker run -d --hostname my-rabbit --name some-rabbit -p 8080:15672 -p 5672:5672 -e RABBITMQ_ERLANG_COOKIE='secret cookie here' rabbitmq:3-management

# 启动临时访问节点
docker run -it --rm --link some-rabbit:my-rabbit -e RABBITMQ_ERLANG_COOKIE='secret cookie here' rabbitmq:3 bash

rabbitmqctl -n rabbit@my-rabbit list_users

```