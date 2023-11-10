# GraphQL学习

## 相关服务准备

---
#### 启动PostgresSQL

`docker-compose.yml`中可以修改postgres数据库的用户名和密码

```yaml
services:
  postgres:
    container_name: postgres_container
    image: postgres
    environment:
      POSTGRES_USER: ${POSTGRES_USER:-admin}  # 用户名：admin
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD:-123456} # 密码：123456
      PGDATA: /data/postgres
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped
```
直接拉起数据库和可视化web界面
~~~shell
cd ~/Desktop/graphql/graph
docker compose up
~~~

#### 初始化PostgresSQL

在图形化界面中，初始化数据库设为postgres，至于其他数据库名字，会出现不存在该数据库，可能是我的问题

