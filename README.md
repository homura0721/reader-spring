* Background

  *  学习项目 小说APP 悦阅 服务器

* Server Address

  * | 服务器地址                |
    | ------------------------- |
    | http://47.115.82.64:8080/ |

  * 需要先登录才能访问

* API

  | URL                               | HTTP方法 | 说明                |
  | --------------------------------- | -------- | ------------------- |
  | /book                             | GET      | 查看所有书籍        |
  | /book/{bookId}                    | GET      | 查询指定id书籍      |
  | /book/{bookId}                    | DELETE   | 删除指定id书籍      |
  | /book                             | POST     | 新增一本书籍        |
  | /book                             | PUT      | 更新一本书籍        |
  | /book/s/?keyword={书名或作者}     | GET      | 根据keyword查找书籍 |
  | /book/t/?tag={书籍标签}           | GET      | 根据tag查找书籍     |
  | /book/{bookId}/comment            | POST     | 新增评论            |
  | /book/{bookId}/hotcomments        | GET      | 获取前10条评论      |
  | /user/login/{username}/{password} | GET      | 登录账号            |
  | /user/register                    | POST     | 注册账号            |
  | /user/favorite/my/get             | GET      | 查询收藏夹          |
  | /user/favorite/my/add/{bookId}    | POST     | 添加收藏夹          |
  | /user/favorite/my/del/{bookId}    | DELETE   | 删除收藏夹          |
  | /user/get/my/data                 | GET      | 查看个人信息        |
  | /user/change/my/data              | PUT      | 修改个人信息        |
  | /user/change/my/password          | PUT      | 修改密码            |

* Maintainers

  [@Remelife](https://github.com/Remelife).

* End

 ![image](https://github.com/Remelife/README_img/blob/master/1.jpg)

