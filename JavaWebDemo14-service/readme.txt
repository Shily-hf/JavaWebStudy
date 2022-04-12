事物管理
    1）涉及到的组件
    -OpenSessionInViewFilter
    -TransactionManager
    -ThreadLocal
    -ConnUtil
    -BaseDAO

    2)ThreadLocal
    -get(),set(obj)
    -ThreadLocal称之为本地线程。可以通过set方法在当前线程上存储数据，通过get方法在当前线程上获取数据

    -set方法源码分析：
     public void set(T value) {
            Thread t = Thread.currentThread();              //获取当前线程
            ThreadLocal.ThreadLocalMap map = this.getMap(t);//每一个线程都维护自己的一个容器
            if (map != null) {
                map.set(this, value);                       //这里的key对应的是ThreadLoacl,因为我们的组件中需要传输（共享）的对象可能会有多个
            } else {
                this.createMap(t, value);                   //默认情况下map是没有初始化的，那么第一次往其中添加数据时，会去初始化
            }
        }

     -get方法源码分析：
     public T get() {
             Thread t = Thread.currentThread();             //获取当前线程
             ThreadLocal.ThreadLocalMap map = this.getMap(t);//获取和这个线程（企业）相关的ThreadLocalMap（也就是工作纽带）
             if (map != null) {
                 ThreadLocal.ThreadLocalMap.Entry e = map.getEntry(this);//this指的是ThreadLocal对象，通过它才能知道哪一个工作纽带
                 if (e != null) {
                     T result = e.value;                      //entry.value()就可以获得到工具箱了
                     return result;
                 }
             }

             return this.setInitialValue();
         }