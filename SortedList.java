/**
 * @author wangwentian0@hotmail.com
 * Sorted(asc) single linked list implemented in java
 * @license MIT
 * Thread unsafe
 * @param <T>
 */
public class SortedList <T extends Comparable>{
    private NodeWrapper<T> head;
    private NodeWrapper<T> tail;
    private Long size = 0L;

    private class NodeWrapper<T extends Comparable> {
        private T ori;
        private NodeWrapper<T> next;
        NodeWrapper(T ori) {
            this.ori = ori;
        }
        int compareTo(NodeWrapper<T> d) {
            return this.ori.compareTo(d.ori);
        }
    }

    /**
     * add a node in the order of asc
     * @param cand
     */
    public void add(T cand) {
        _add(cand);
    }

    private SortedList _add(T cand) {
        NodeWrapper<T> target = new NodeWrapper(cand);
        if(head == null) {
            head = target;
            tail = target;
            size += 1;
            return this;
        }
        if(target.compareTo(tail) >= 0) {
            tail.next = target;
            tail = target;
            size += 1;
            return this;
        }
        NodeWrapper cur = head;
        NodeWrapper prev = null;
        while(cur != null) {
            if(target.compareTo(cur) >=0) {
                prev = cur;
                cur = cur.next;
                continue;
            }else{
                target.next = cur;
                if(prev != null) {
                    prev.next = target;
                }else{
                    head = target;
                }
            }
            size += 1;
            break;
        }
        return this;
    }

    /**
     * return the smallest node, and delete it from list
     * @return
     * @throws IndexOutOfBoundsException
     */
    public T pollFirst() throws IndexOutOfBoundsException{
        NodeWrapper<T> tmp = head;
        if(head == null) throw new IndexOutOfBoundsException();
        NodeWrapper next = head.next;
        if(next == null) {
            head = null;
            tail = null;
        }else{
            head = next;
        }
        size -= 1;
        return tmp.ori;
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        return this.size <= 0;
    }

    /**
     * @return
     */
    public boolean isNotEmpty() {
        return this.size > 0;
    }

    /**
     * num of nodes contained in this sorted list
     * @return
     */
    public Long size() {
        return this.size;
    }

    /**
     * print the list, the format is like this 'a->b->c'
     * @return
     */
    public String toString(){
        NodeWrapper cur = head;
        if(cur == null) return "";
        StringBuilder sb = new StringBuilder();
        while(cur != null) {
            sb.append(cur.ori.toString()+"->");
            cur = cur.next;
        }
        return sb.delete(sb.length()-2, sb.length()).toString();
    }

    /**
     * test code for method. Add one if you add another method
     */
    //测试代码，更改源码后跑一下, 如果增加了一个方法，建议增加一个测试方法在这里
    private void testAdd() {
        //unit test
        SortedList<Integer> l = new SortedList<>();
        l._add(5)._add(1)._add(3);
        if(!"1->3->5".equals(l.toString())) {
            throw new RuntimeException("FAILED at testAdd()");
        }
    }

    /**
     * run all the tests
     */
    private void runTests() {
        testAdd();
        System.out.println("LGTM, COOL!");
    }

}

