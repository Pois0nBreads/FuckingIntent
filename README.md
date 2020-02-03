# FuckingIntent v1.1
none


安装后可以使Chrome，UC等不允许通过设置iframe的src属性来启动Android应用的浏览器，兼容百度贴吧手机APP的唤醒和跳转。

大致原理就是制作一个兼容http scheme的APP来间接的唤醒百度贴。

可以说这个软件就是一个协议适配器（Adapter）了 _(:з」∠)_

下载地址：https://github.com/Pois0nBreads/FuckingIntent/releases
蓝奏云分流：https://www.lanzous.com/b0c23usof 密码:i300 如果Github下载过慢可以用这个

祝大家用的开心 #如果可以，请赏我杯饮料喝 谢谢 https://afdian.net/@Pois0nBread

以下摘自[chrome 开发文档]( https://developer.chrome.com/multidevice/android/intents)，明确说了 chrom25+以后不支持 url scheme

> A little known feature in Android lets you launch apps directly from a web page via an Android Intent. One scenario is launching an app when the user lands on a page, which you can achieve by embedding an iframe in the page with a custom URI-scheme set as the src, as follows: <iframe src="paulsawesomeapp://page1"> </iframe>. This works in the Chrome for Android browser, version 18 and earlier. It also works in the Android browser, of course.

> The functionality has changed slightly in Chrome for Android, versions 25 and later. It is no longer possible to launch an Android app by setting an iframe's src attribute. For example, navigating an iframe to a URI with a custom scheme such as paulsawesomeapp:// will not work even if the user has the appropriate app installed. Instead, you should implement a user gesture to launch the app via a custom scheme, or use the “ intent:” syntax described in this article.

![image](https://github.com/Pois0nBreads/FuckingIntent/blob/master/images/Screenshot_20200126-201633.jpg)
![image](https://github.com/Pois0nBreads/FuckingIntent/blob/master/images/Screenshot_20200126-201651.jpg)
