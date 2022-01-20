# Register-login-network-programming-Demo
Through network programming to achieve simple registration login function


### 需求
使用网络通信完成注册和登录功能，注册的用户信息必须进行存档，登陆时需要从存档的数据检测是否能够登录

### 分析
1. 使用TCP完成，因为TCP是可靠性数据传输，可以保证信息能够被接收
2. 用户属于客户端操作，服务器端需要区分用户的行为
3. 为了区分客户端的行为，需要设计一个消息类，然后使用序列化的方式来进行传输
4. 用户注册信息需要存档，因此需要设计一个用户类，为了方便使用，也可以直接将一个集合使用序列化的凡是存储再文档中
