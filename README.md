# Android 通用圆角布局，快速实现圆角需求。
## 由于原作者暂停维护该圆角布局,而且私有的仓库地址maven总是链接不上,所以拿来引用修改一下,实现了另外的FrameLayout跟LinearLayout布局,顺便改为https://jitpack.io/仓库

## 原作者Github地址为: <a href="https://github.com/GcsSloop/rclayout">传送门地址</a>

**原作者的相关原理解析： <a href="http://www.gcssloop.com/gebug/rclayout">相关原理解析</a>**

## 效果预览

<img src="http://ww1.sinaimg.cn/large/005Xtdi2gy1fqbgk8pmevg309u0ghwz9.gif" width="300"/> <img src="https://ww4.sinaimg.cn/large/006tKfTcly1fk7twywj5oj30u01fewka.jpg" width="294"/>

<img src="/img/01.png" width="300"/> <img src="/img/02.png" width="300"/> 

<video src="/img/03.mp4" width="300"/>

## 支持的特性

- [x] 包裹任意组件。
- [x] 设置圆角大小。
- [x] 分别对每一个角设置圆角大小。
- [x] 设置描边宽度。
- [x] 设置描边颜色。
- [x] 圆形。
- [x] 支持Padding。
- [x] 圆角抗锯齿。
- [x] 内容可点击区域即为显示区域。
- [x] 是否剪裁自身背景。

## 主要文件

| 名字             | 摘要           |
| ---------------- | -------------- |
| RCRealtiveLayout | 圆角相对布局。 |
| RCLinearLayout | 圆角相对布局。 |
| RCFrameLayout | 圆角相对布局。 |
| RCImageView      | 圆角图片。     |
| RCHelper         | 圆角辅助工具。 |

### 1. 基本用法 (可以参考demo)

RCRelativeLayout(Round Corner RelativeLayout)，使用圆角布局包裹需要圆角的内容然后添加自定义属性即可

```xml
 <com.evil.rlayout.RoundRelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="360dp"
        android:background="#9f06"
        android:orientation="vertical"
        app:clip_background="true"
        app:round_corner="15dp"
        app:stroke_color="#000"
        app:stroke_width="2dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_gravity="center"
            android:text="RRLayout"
            android:textColor="#328fff"
            android:textSize="13dp" />

        <ImageView
            android:layout_width="90dp"
            android:layout_height="90dp"
            android:layout_centerHorizontal="true"
            android:layout_gravity="right"
            android:layout_marginTop="17dp"
            android:scaleType="centerCrop"
            android:src="@mipmap/a03" />

</com.evil.rlayout.RoundRelativeLayout>
```

### 2. 配置属性

可以在布局文件中配置的基本属性有五个：

| 属性名称                      | 摘要      | 是否必须设置 | 类型      |
| ------------------------- | ------- | ------ | ------- |
| round_corner              | 总体圆角半径  | 否      | dp      |
| round_corner_top_left     | 左上角圆角半径 | 否      | dp      |
| round_corner_top_right    | 右上角圆角半径 | 否      | dp      |
| round_corner_bottom_left  | 左下角圆角半径 | 否      | dp      |
| round_corner_bottom_right | 右下角圆角半径 | 否      | dp      |
| round_as_circle           | 是否剪裁为圆形 | 否      | boolean |
| stroke_width              | 描边半径    | 否      | dp      |
| stroke_color              | 描边颜色    | 否      | color   |
| clip_background           | 剪裁背景    | 否      | boolean |

### 3. 属性简介

#### 3.1 圆角属性

`round_as_circle` 的权限最高，在默认情况下它的值为false，如果设置这个属性为 true，则会忽略圆角大小的数值，剪裁结果均为圆形。

设置圆角大小的一共有5个属性，一个是全局的圆角大小`round_corner`，其余四个`round_corner_xx_xx`是分别对每一个角进行设置，它们之间存在替代关系。

1. 仅设置全局，所有的角都跟随全局。
2. 仅对某些角设置，则只有设置过的角会有圆角效果。
3. 全局和部分都有设置，则有具体设置的角跟随具体设置的数值，没有具体设置的角跟随全局设置。

#### 3.2 描边属性

描边宽度`stroke_width`默认情况下数值为 0，即不存在描边效果。  
描边颜色`stroke_color`默认情况下为白色，允许自定义颜色。

#### 3.3 背景剪裁

RCLayout 默认对自身背景剪裁，但是可以通过设置 clip_background 为 false 让RCLayout 不剪裁自身的背景。

### 4.添加方法

#### 4.1 添加仓库

在项目的 `build.gradle` 文件中配置仓库地址。

```groovy
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

#### 4.2 添加项目依赖

在需要添加依赖的 Module 下添加以下信息，使用方式和普通的远程仓库一样。

```groovy
 implementation 'com.github.fengxiaocan:roundlayout:1.0.0'
```
