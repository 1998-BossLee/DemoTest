package jvm.load;

public class LoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //AppClassLoader->ExtClassLoader->BootstrapClassLoader
        ClassLoader loader = ReferenceCountingGC.class.getClassLoader();
        System.out.println(loader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        loader = HelloWorld.class.getClassLoader();
        System.out.println(loader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(loader.getParent());//sun.misc.Launcher$ExtClassLoader@1b6d3586
        System.out.println(loader.getParent().getParent());//null 原因是BootstrapLoader(引导类加载器)是用C语言实现的，找不到一个确定的返回父Loader的方式

        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        Class<?> testClass = loader.loadClass("jvm.load.ReferenceCountingGC");
        System.out.println(testClass);
        System.out.println(testClass.getFields().toString());
        //使用Class.forName()来加载类，默认会执行初始化块
        testClass = Class.forName("jvm.load.ReferenceCountingGC");
        System.out.println(testClass);
        System.out.println(testClass.getFields().toString());
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        testClass = Class.forName("jvm.load.ReferenceCountingGC", true, loader);
        System.out.println(testClass);
        System.out.println(testClass.getFields().toString());
    }
}

/**
 * Class.forName(): 将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块；
 * ClassLoader.loadClass(): 只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
 * Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象 。
 */

class HelloWorld {

}


