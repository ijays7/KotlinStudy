## Kotlin学习项目

[![CircleCI](https://circleci.com/gh/ijays7/KotlinStudy.svg?style=svg)](https://circleci.com/gh/ijays7/KotlinStudy)

分别包含：

- Android6.0 动态申请权限

- 展示如何使用 layoutAnimation

- 使用 MVP 模式打开 wanAndroid 页面，包含文章列表和文章详情

- 展示如何使用 Jetpack 中的 navigation

- [RxLifecycle原理解析](http://jaysdev.github.io/2019/01/22/RxLifecycle%E8%A7%A3%E6%9E%90/)

- 使用[AutoDispose](https://github.com/uber/AutoDispose)来自动取消订阅。相对于 RxLifecycle，它最大的优势在于它并不需要继承 RxActivity/RxFragment，使得它更具的灵活性。

- 展示如何使用 [最新的ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2),其内部使用 RecyclerView 来简化内部逻辑，但目前还在 alpha阶段。

- 使用局部函数（Local Function）来简化代码，即在函数中定义函数。比如：

  ```kotlin
   fun saveUser(user: UserModel) {
          /**
           *优化,局部函数可以访问所在函数中的所有参数和变量
           */
          fun validate(value: String, fieldName: String) {
              if (value.isEmpty()) {
                  throw IllegalArgumentException("Cannot save user ${user.userId}:empty $fieldName")
              }
          }
  
          validate(user.userName, "userName")
          validate(user.userId, "userId")
       // ... more actions
      }
  ```

  

