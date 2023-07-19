package 设计模式;

public class SingletonHungry {

    private final static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry(){};

    public static SingletonHungry getInstance() {
        return instance;
    }

}
