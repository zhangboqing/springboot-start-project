
[TOC]
#  Spring Boot 基础架构
## 项目技术选型
> 1. SpringBoot+MyBatis+Maven+Git+Redis+Mysql 
> 2. SpringCloud (完善阶段)


## 项目结构介绍
### com.zbq.heimdallrbackend包结构
> 1. common     公共工具包 
> 2. config     项目配置包，存放java配置类
> 3. constant   项目常量包，定义常量和异常类
> 4. controller controller层
> 5. domain     数据模型层，定义entity(数据库表实体)、form、result
> 6. manager    manager层,存在于dao和service之间的
> 7. mysql      dao层
> 8. service    service层  

### document目录
> 1. api     用来存放项目所有接口文档，对外的接口必须有对应的文档 
> 2. sql     用来存放建表、修改表、添加索引、相关表初始化数据等，数据库DDL，DML，DCL语句

## 关于调用关系说明
> 1. manager可调用其他manager或dao
> 2. Service可调用manager或dao
> 3. controller调用service


## 相关项目tools或插件说明
### 项目tools
> 1. 使用DAO逆向工程生成domain，xml，dao相关初始类
  你可以从本链接下载本工具: https://github.com/astarring/mybatis-generator-gui/releases
### 编辑器插件  
> 1. Lombok      getter、setter等生产工具   
> 2. GsonFormat  Json字符串自动生成实体类
  可参考该博客地址：https://www.cnblogs.com/756623607-zhang/p/7126844.html
  
## url规范:
```
    /api/{端}/{模块名称}/{方法或功能名}
    或
    /api/{模块名称}/{方法名}
```  
  
## 编写规范
> 1. SQL编写，不使用xml配置方式，简单SQL通过注解， 复杂SQL使用Provider提供
> 2. 接口文档采用.md结尾的MacDown文件格式编写
> 3. sql文档采用.sql结尾的文件格式编写 
 

## 方法名编写规范
方法命名:
>* 插入 : save 开头
>* 查询单条: get 开头
>* 查询多条: find 开头 List结尾
>* 更新: update 开头
>* 查询数量: count 开头
>* 删除: delete 开头
>* 批量处理: batch 开头
>* 分页: find Page 结尾

## Git试用技巧
```
>1. 创建远程分枝
    创建分支       git branch 分支名
    推送到远程      git push origin 分支名
    
>2. 第一次拉取远程分支（本地没有）
    git fetch origin 远程分枝名:本地分枝名   
    例如: git fetch origin feature/v1.0:feature/v1.0
    
>3. 提交代码
    1.git add .
    2.git commit -m "注释"   
    3.git pull
    是否有冲突
        4.1.有冲突解决后，回到第一步 git add . ,再执行一遍步骤
        4.2.没有冲突，继续第5步
    5.git push
    
>4. 其他命令
     git命令   任何操作都需要以 git 命令为开头
     本地操作：
     git init  初始化一个本地仓库  新建为 master主分支
     git status  查看当前分支状态
     git add  <文件名>   将文件更改添加到分支状态中 相当于文件等待被提交
     git commit -m <"描述信息">  提交并添加描述信息
     git branch  查看分支   前面带*号的为当前所在分支
     git branch <分支名称>  新建分支
     git checkout <分支名>  切换分支
     git checkout -b <分支名>  新建分支并切换到此分支
     git merge <分支名>   将指定分支名合并到当前分支  一般为切换到主分支使用此命令
     git merge --no-ff -m "提交描述" <分支名>   合并分支并提交
     git branch -d <分支名>  有新建分支，那肯定有删除分支，假如这个分支新建错了，或者a分支的代码已经顺利合并到 master 分支来了，那么a分支没用了，需要删除，这个时候执行 git branch -d a 就可以把a分支删除了
     git branch -D <分支名>  强制删除分支，不管分支是否有未提交合并的代码

     git tag 查看所有标签
     git tag <标签名> 在当前状态下新建一个标签，可用来当作版本号使用
     git tag -a <标签名称> -m <"标签描述"> <提交id>  在指定的提交状态下新建一个标签
     git show <标签名称>   查看标签的详情
     git tag -d <标签名> 删除标签
     git push origin <标签名>   推送标签到远程仓库
     git push origin --tags  推送所有未推送的标签
     git push origin :refs/tags/<标签名>   删除远程标签，本地要先删除后才可以
     
     
     git checkout <标签名> 切换到标签名指定的状态
     git diff <文件名> 查看文件修改内容
     
     git log      查看提交日志   --pretty=oneline  此参数减少输出信息  穿梭前，用git log可以查看提交历史，以便确定要回退到哪个版本。
     git reflog   要重返未来，用git reflog查看命令历史，以便确定要回到未来的哪个版本。
     git log --graph --pretty=oneline --abbrev-commit   查看分支合并图
     git reset --hard <HEAD^||提交ID> 穿梭到指定提交版本
     HEAD指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使用命令git reset --hard commit_id。
     
     git checkout -- <文件名>  将指定的文件恢复到最近一次 commit或add操作时候的状态
     git reset HEAD <文件名>   将指定的文件从暂存区的修改撤销掉（unstage），重新放回工作区
     git rm <文件名>       删除指定的文件
     
     git stash  把当前工作现场“储藏”起来，等以后恢复现场后继续工作
     git stash list 查看暂存状态
     git stash apply 恢复暂存状态
     git stash drop  删除暂存状态
     git stash pop   恢复并删除暂存状态
     git stash apply <stash@{0}>  恢复指定的暂存状态
     
     
     远程仓库操作:
     git clone <远程地址>  从远征仓库拷贝过来代码，相当于建立本地分支
     git pull 将最新的提交从远程仓库抓取下来
     git push  将本地修改后的代码提交到远程仓库
     git push <远程仓库名，默认origin> <本地分支名>  将指定的分支推送到远程分支上
     
     git remote -v 查看远程仓库  -v 为详细信息
     
     git checkout -b <本地支分支名> <远程仓库名，默认origin>/<远程支分支名> 拉取远程主分支下的支分支。。。
     git branch --set-upstream <本地支分支名> <远程仓库名，默认origin>/<远程支分支名>  将本地分支与远程指定的分支关联起来
     
     //以下为先有本地库，再建立远程库操作所用的命令
     git remote add origin <URL地址> 本地库与远征库关联
     git push -u origin master 关联后，使用命令第一次推送master分支的所有内容，   -u参数为推送当前分支所有内容
    
```







