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
  "state":true,
}
失败返回示例：
{
   "message": "请求失败",
   "state":false,
}
```

### 后台管理登录
```
POST  json  url/api/login
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

get  json  url/api/token
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




