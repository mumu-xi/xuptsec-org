# xuptsec-org
 ## start
 ```
   git clone git@github.com:mumu-xi/xuptsec-org.git
 ```
## install
```
   cd xuptsec-org/client // 前端目录
   npm install
   npm start
   http://localhost:8080

   cd ../server // 后端目录
   npm install
   npm start // 开启服务器
   http://localhost:8181

```
## 部署与打包
```
   npm run build
```
## 接口文档
### 纳新报名
```
POST  json  url/api/signup
请求示例：
{
  "stuName": "唐梦",
  "stuSex": "女",
  "stuClass": "信息对抗1401",
  "stuNumber": "03146000",
  "stuTel": "4567890222",
  "stuGroup": "开发",
  "stuIntro": "啦啦啦啦啦"
}
成功返回示例：
{
  "message": "请求成功",
  "state": true,
}
失败返回示例：
{
   "message": "请求失败",
   "state": false,
}
```
### 成员简介
```
get json url/api/people
请求实例：
{
  pageNum:'1',
  pageSize:'10'
}
成功返回示例:
{
  "message":"请求成功",
  "state":true,
  "data":[{
    "picurl":"url1",
    "peopleName":"张三",
    "peopleIntro":"多年深耕技术创业领域，在云计算、移动社交、多媒体处理等领域有着多年的技术积累..."
  },{
    "picurl":"url2",
    "peopleName":"李四",
    "peopleIntro":"喵喵..."
  }]
}
失败返回示例:
{
  "message":"请求失败",
  "state":false,
  "data":null
}

```
### 用户登录
```
POST  json  url/api/admin/login
请求示例：
{
  "userName": "admin",
  "passWord": "123456",
}
成功返回示例：
{
  "message": "请求成功",
  "state":true,
}
失败返回示例：
{
   "message": "请求失败",
   "state":false,
}
```
### 用户刷新登录
```
get  json  url/api/admin/token
请求示例：

成功返回示例：
{
  "message": "请求成功",
  "state":true,
}
失败返回示例：
{
   "message": "请求失败",
   "state":false,
}
```
### 帖子
```
get json url/api/wiki
请求示例：
{
  "pageNum": 1,
  "pageSize": 10,
  "type": 1, <!-- type为帖子类型 type:0 ->随机类型    type:1 ->开发     type：2 -> 安全    -->
}
成功返回示例：
{
  "message": "请求成功"，
  "state": true,
  "total":100,
  "data": {
      "title": 'javascript的几种基本类型',
      "author: "木木木夕",
      "date: "2017-11-1",
      "tags: "javascript",
      "outline: "在英语中，Loop这个词指的是由弯曲的曲线所产生的形状。类似的概念，Loop这个词已经被用于编程中。如果你看到下图，你就会清楚的知道指令的流动是如何在一个循环的动作中不断重复的。在编程中，循环的概念并不是什么新概念，它们常常在编码时使用。虽然不是的语言其语法不同，但基本概念是相同的，根据需要重复相同的代码块。JavaScript增加了循环类型（包括各种类型的循环），并使其与它们的工作更加舒适和高效。在本文中，我们将学习JavaScript中所有可用的循环。",
      "cardsId: 2
    }
}
```



