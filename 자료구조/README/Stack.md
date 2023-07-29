## 스택이란?

스택이란 ‘쌓다’, ‘쌓이다’의 뜻을 가진 용어로 흔히 접시 쌓기로 비유하는 자료구조이다.

즉, 데이터를 순서대로 쌓는 자료구조이다.

## 스택의 특징

- 후입선출(LIFO : Last In First Out) : 나중에 들어 온 데이터가 가장 먼저 빠져나가는 구조
- 단방향 입출력 구조 : 데이터가 한 방향을 통해서만 들어오고 나가는 구조
- 단일 입출력 : 데이터를 한번에 여러개 넣고 뺄 수 없다
- 깊이 우선 탐색(DFS : Depth-First Search)에 사용

## 스택의 선언

```java
import java.util.Stack;
class Stack{
	public static void main(String[] args){
		Stack<Object o> name = new Stack<>();	
	}
}
```

- 스택은 그 자체가 클래스로 구현되어져 있어 Stack으로 초기화 할 수 있다.

## 스택의 메서드

### 데이터의 추가

- add( E e ) : 데이터를 스택에 추가하고, true( boolean) 반환
    
    ```java
    stack.add(1);
    //[1]
    System.out.println(stack.add(2));
    //[1, 2]
    
    //출력 결과
    true
    ```
    
- push( E item ) : 데이터를 스택에 추가하고, 해당 값을 반환
    
    ```java
    stack.push(1);
    //[1]
    System.out.println(stack.push(2));
    //[1, 2]
    
    //출력 결과
    2
    ```
    
- peek() : 스택의 마지막 요소를 반환
    - 스택이 비어있을 경우 NoSuchElementException 예외 발생
    
    ```java
    stack.push(1);
    stack.push(2);
    //[1, 2]
    System.out.println(stack.peek());
    stack.pop();
    stack.pop();
    System.out.println(stack.peek());
    
    //출력 결과
    2
    ~~~ NoSuchElementException ~~~~
    ```
    
- pop() : 스택의 마지막 요소를 제거하고, 해당 값을 반환
    
    ```java
    stack.push(1);
    stack.push(2);
    //[1, 2]
    System.out.println(stack.pop());
    
    //출력 결과
    2
    ```
    
- empty() : 스택이 비어있는지 여부를 true, false( boolean )로 반환
    
    ```java
    System.out.println(stack.empty());
    stack.push(1);
    System.out.println(stack.empty());
    
    //출력 결과
    true
    false
    ```
    
- search( Object o ) : 메서드의 인자를 검색하여 해당 위치를 반환
    - 값이 여러개일 경우, 마지막 위치를 반환
    - 여기서 위치란 인덱스가 아닌 빠져나오는 순서를 의미
    
    ```java
    stack.push(1);
    stack.push(2);
    stack.push(3);
    //[1, 2, 3]
    System.out.println(stack.search(1));
    
    //출력 결과
    3
    ```
