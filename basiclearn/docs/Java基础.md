# List, Map, Set
## 是否可以有重复元素（键值）？
- List 可以
- Map, Set 不可以。因此Map 和 Set 里的`添加`方法也是修改方法。

## 相同元素的判别
- List 利用equals()
- Map, Set 利用hashCode() **和** equals()

# PriorityQueue
- 元素可重复；故不可通过`添加`的方法来修改元素
- 利用equals()进行比较？

> 注意：上边两个关于相同元素的判别的地方有可能有误；因为他们可能还使用comparable或comparator接口中的==进行判断。不过一般来说，实现equals和compare/compareTo应该以相同方式处理等于的情况。

# Comparable和Comparator接口
- 作用不同：
  - Comparable是自然排序：即在类似TreeMap或者PriorityQueue中自动调用被@Override了的compareTo方法；
  - Comparator是定制排序：即在后期想要改变原来的“自然排序”的方法，但是原来的类又不能更改了，构造一个实现了该接口的类，传入sort或者map，priorityQueue中，进行**定制化**的比较方法。
    - 可以看出，Comparator优先于Comparable。当存在定制化的比较方法时，原来的自然排序方法就失效了。
    - 一般在自己的类中使用Comparable接口。而Comparator用于“反悔”或者一些内置类比如`String`类，我们想要修改它的比较方法是不可能的，只能在排序或者比较的时候传入一个`StringComparator implements Comparator`的类。

TODO Comparator能不能修改equals方法？比如String的equals方法应该怎么修改？

# <E extends Comparable<? super E>>
1. 先来理解`A implements Comparable<A>`
   ``` java
   class A implements Comparable<A> {}
   ```
   这样一个类`A`要求具有如下属性：A是可比较的，比较的对象是另一个`A`的对象.

   如果使用`B implements Comparable`，则`B`比较的对象是一个`Object`的对象，因为实际上上边的这行相当于`B implements Comparable<Object>`.应该了解，`Object`实际上就是啥“限制”都没有。

2. 通配符`<? super C>`是“下界通配符”，表示这个泛型是‘任意’一个具有如下属性的类`X`：`X`是`C`的父类或者是`C`.
3. **重点**：`<E extends Comparable<E>>`.这句后边的方形参数实际上可以是任何东西：`<E extends Comparable<D>>`，这样的话就相当于表示表示泛型`E`是一个实现了`Comparable<D>`接口的类。
   
   回到`<E extends Comparable<E>>`，它表示表示泛型`E`是一个实现了`Comparable<E>`接口（不是`Comparable`/`Comparable<Object>`接口）的类.
4. 第3条中所述的`<E extends Comparable<D>>`显然是不合理的，因为我们不能随便和一个类进行比较，最好和一个“与`E`有关”的类进行比较，那么和谁呢？这时候我们想起第2条里所述的上界通配符`<? super E>`——`<E extends Comparable<? super E>>`.表示该泛型是任意一个具有如下属性的<u>*类*</u>：
   1. 这个<u>*类*</u>可比较
   2. 这个<u>*类*</u>比较的对象是它自己或者它的“爸爸们”

# LinkedList
LinkedList是双向链表

# hashCode()
- `hashCode(a, b)!=hashCode(b, a)`；对于想要使二者相等的类，实现hashCode时只需另方法返回`hashCode(a, b) + hashCode(b, a)`即可

# HashMap详解

`HashMap`的特殊之处在于，其插入元素和寻找元素都是根据元素的`hashCode()`进行的。

## 问题1

一个以对象为键的HashMap中，如果插入某个对象`obj`之后，又修改了该对象内部的部分数据，（并能够导致哈希值发生变化）那么这个对象将无法作为“键”起作用了，它再也找不到对应的值了。

原因：插入的时候，是根据对象当前的哈希值`hash1`，在内存中找到一个位置`X`存放。之后你改了这个对象的内容（不是引用），那么存储在该内存`X`处的对象自然也变了，但问题在于，这时候的哈希值已经变了，当再次寻找的时候，拿着哈希值`hash2`去找，当然找不到相应的内存位置`X`了。

对于TreeMap也是类似的结论：不能在插入一个对象（引用）之后，再修改该处的内存，否则既会打乱树的平衡，也很难再找到这个元素了。