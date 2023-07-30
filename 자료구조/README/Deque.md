## 덱이란?

- 덱이란 ‘Double-Ended Queue’의 줄임말로 큐와 스택을 합친 형태
- 큐의 양쪽으로 요소의 삽입과 삭제를 수행하는 자료구조

## 덱의 특징

- 스크롤(Scroll) : 한쪽으로만 입력 가능하도록 설정한 덱
- 셸프(Shelf) : 한쪽으로만 출력 가능하도록 설정한 덱

## 덱의 선언

```java
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.LinkedBlockingDeque;
import java.util.ConcurrentLinkedDeque;
import java.util.Deque;
class Deque{
	public static void main(String[] args){
		Deque<Object o> name1 = new LinkedList<>();
		Deque<Object o> name2 = new ArrayDeque<>();
		Deque<Object o> name3 = new LinkedBlockingDeque<>();
		Deque<Object o> name4 = new ConcurrentLinkedDeque<>();
	}
}
```

- 덱은 LinkedList, ArrayDeque, LinkedBlockingDeque, ConcurrentLinkedDeque에 구현

## 덱의 메서드

- First = 왼쪽 / Last = 오른쪽
- offerFirst(E e) / offerLast(E e) : 데이터를 덱에 추가하고, true( boolean) 반환
    
    ```java
    deque.offerFirst(2);
    //[2]
    deque.offerFirst(1);
    //[1, 2]
    deque.offerLast(3);
    //[1, 2, 3]
    System.out.println(deque);
    System.out.println(deque.offerFirst[1]);
    
    //출력 결과
    [1, 2, 3]
    true
    ```
    
- pollFirst() / pollLast() : 덱의 요소를 제거하고, 해당 값을 반환
    
    ```java
    deque.offerFirst(2);
    //[2]
    deque.offerFirst(1);
    //[1, 2]
    deque.offerLast(3);
    //[1, 2, 3]
    System.out.println(deque.pollFirst());
    System.out.println(deque.pollLast());
    
    //출력 결과
    1
    3
    ```
    
- peekFirst() / peekLast() : 방향별 덱의 첫번째 요소를 반환
    
    ```java
    deque.offerFirst(2);
    //[2]
    deque.offerFirst(1);
    //[1, 2]
    deque.offerLast(3);
    //[1, 2, 3]
    System.out.println(deque.peekFirst());
    System.out.println(deque.peekLast());
    
    //출력 결과
    1
    3
    ```
    
- size() : 덱이 가진 요소의 개수를 반환
    
    ```java
    deque.offerFirst(2);
    //[2]
    deque.offerFirst(1);
    //[1, 2]
    deque.offerLast(3);
    //[1, 2, 3]
    System.out.println(deque.size());
    
    //출력 결과
    3
    ```
    
- removeFirstOccurrence( Object o ) / removeLastOccurrence( Object o )
    - 각 방향에서 가장 먼저 찾은 해당 요소를 제거하고, true, false( boolean ) 반환
    
    ```java
    deque.offer(2);
    deque.offer(3);
    deque.offer(2);
    //[2, 3, 2]
    System.out.println(deque);
    System.out.println(deque.removeFirstOccurrence(2));
    System.out.println(deque);
    
    //출력 결과
    [1, 2, 3, 2]
    true
    [1, 3, 2]
    ```
