package juc;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList
 * 写时复制：要操作内存时，先复制一份新的内存操作，再用新内存地址替换旧内存地址。
 * add(E) 上锁；获取当前数组；复制一份新的内存数组；新数组添加元素；设置当前数组为新数组内存地址。 删除操作类似。
 * get(idx) 获取数组；再获取下标元素；没上锁，多线程操作可能存在数据不一致。 这是写时复制策略的弱一致性问题。
 * 使用迭代器遍历数组不会有弱一致性问题，因为迭代器指向旧数组，新内存改动也没有关系。
 *
 */
public class ListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.get(0);
        list.set(0,2);
        list.remove(1);
    }

}
