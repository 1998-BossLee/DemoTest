package 设计模式;

public class SingletonLazy {

    private volatile static SingletonLazy instance;

    private SingletonLazy(){
    };

    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }

}
