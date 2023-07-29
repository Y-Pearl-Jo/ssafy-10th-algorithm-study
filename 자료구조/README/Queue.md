## 큐란?

큐란 ‘무엇을 기다리는 사람’라는 뜻을 가진 용어로 흔히 줄 서기로 비유하는 자료구조이다.

즉, 데이터를 순서대로 줄 세우는 자료구조이다.

## 큐의 특징

- 후입선출(FIFO : Last In First Out) : 먼저 들어 온 데이터가 가장 먼저 빠져나가는 구조
- 양방향 입출력 구조 : 데이터가 들어오는 방향과 빠져나가는 방향이 다른 구조
- 단일 입출력 : 데이터를 한번에 여러개 넣고 뺄 수 없다
- 그래프의 너비 우선 탐색(BFS : Breadth-Fist Search)에 사용
- 컴퓨터의 버퍼에서 사용되는 방식

## 큐의 선언

```java
import java.util.LinkedList;
import java.util.Queue;
class Stack{
	public static void main(String[] args){
		Queue<Object o> name = new LinkedList<>();	
	}
}
```

- 큐는 LinkedList에 구현되어 있으므로 Queue와 LinkedList 모두 import 해야 사용 할 수 있다.

## 큐의 메서드

### 데이터의 추가

- add(E e) : 데이터를 큐에 추가하고, true( boolean) 반환
    
    ```java
    queue.add(1);
    //[1]
    System.out.println(queue.add(2));
    //[1, 2]
    
    //출력 결과
    true
    ```
    
- offer( E e ) : 데이터를 큐에 추가하고, true( boolean) 반환
    
    ```java
    queue.offer(1);
    //[1]
    System.out.println(queue.offer(2));
    //[1, 2]
    
    //출력 결과
    2
    ```
    
- peek() : 큐의 첫번째 요소를 반환
    - 큐가 비어있을 경우 NoSuchElementException 예외 발생
    
    ```java
    queue.offer(1);
    queue.offer(2);
    //[1, 2]
    System.out.println(queue.peek());
    queue.poll();
    queue.poll();
    System.out.println(queue.peek());
    
    //출력 결과
    1
    ~~~ NoSuchElementException ~~~~
    ```
    
- poll() / remove() : 큐의 첫번째 요소를 제거하고, 해당 값을 반환
    
    ```java
    queue.offer(1);
    queue.offer(2);
    //[1, 2]
    System.out.println(queue.poll());
    
    //출력 결과
    1
    ```
    
- remove( Object o ) : 큐에서 해당 요소를 제거하고, true, false( boolean ) 반환
    - 중복 시 가장 먼저 들어간 요소 제거
    - 큐는 선입선출이지만 자바에서 큐는 LinkedList로 구현되어있어서 가능한듯
    
    ```java
    queue.offer(2);
    queue.offer(3);
    queue.offer(2);
    //[2, 3, 2]
    System.out.println(queue);
    System.out.println(queue.remove(2));
    System.out.println(queue);
    
    //출력 결과
    [1, 2, 3, 2]
    true
    [1, 3, 2]
    ```
    
- isempty() : 큐가 비어있는지 여부를 true, false( boolean )로 반환
    
    ```java
    System.out.println(queue.isempty());
    stack.push(1);
    System.out.println(queue.isempty());
    
    //출력 결과
    true
    false
    ```
    
- clear() : 큐를 초기화
    
    ```java
    queue.offer(1);
    System.out.println(queue);
    queue.clear();
    System.out.println(queue);
    
    //출력결과
    [1]
    []
    ```
