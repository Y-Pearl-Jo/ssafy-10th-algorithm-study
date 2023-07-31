## 리스트란?

리스트란 크기를 변경할 수 있는 자료구조

## 리스트의 특징

- 내부를 보면 배열을 사용하여 데이터를 저장
- 리스트의 길이가 변할 때 처리시간이 오래 걸림
    - 길이를 늘린 배열에 기존 배열의 데이터를 복사하는 구조
- 각 요소의 삭제처리 시간이 오래 걸림
- 주요 사용처
    - 리스트에 저장될 데이터의 양이 일관되고, 입력 - 삭제가 적은 곳
    - 데이터의 접근 속도가 빨라야 하는 곳

## 리스트의 선언

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
class List{
	public static void main(String[] args){
		List<Object o> name = new ArrayList<>();	
		List<Object o> name = new LinkedList<>();	
	}
}
```

- 리스트는 사용 방식에 따라 ArrayList와 LinkedList로 구현
    - ArrayList : 기본 배열 구조와 비슷
    - LinkedList : 각 요소가 다음 요소의 주소를 가지고 있는 구조

## 리스트의 메서드

- add(Element e) : 데이터를 리스트에 추가하고 true를 반환
    
    ```java
    list.add(1);
    //[1]
    System.out.println(list.add(2));
    //[1, 2]
    System.out.println(list);
    
    //출력 결과
    true
    [1, 2]
    ```
    
- get(int index) : 리스트의 index번째 요소를 반환
    - 배열과 마찬가지로 0부터 시작
    
    ```java
    list.add(1);
    //[1]
    list.add(3);
    //[1, 3]
    System.out.println(list.get(1));
    
    //출력 결과
    3
    ```
    
- set(int index, Element e) : index번째 데이터를 e로 바꾸고, 기존 데이터를 반환
    
    ```java
    list.add(1);
    //[1]
    list.add(3);
    //[1, 3]
    System.out.println(list.set(1, 4));
    System.out.println(list);
    
    //출력 결과
    4
    [1, 4]
    ```
    
- remove(int index) : index번째 데이터를 삭제하고, 그 데이터를 반환
    
    ```java
    list.add(1);
    //[1]
    list.add(3);
    //[1, 3]
    System.out.println(list.remove(1));
    
    //출력 결과
    3
    [1]
    ```
    
- clear() : list의 모든 요소를 삭제
    
    ```java
    list.add(1);
    //[1]
    list.add(3);
    //[1, 3]
    list.clear()
    System.out.println(list);
    
    //출력 결과
    []
    ```
    

## 연결 리스트(Linked List)

- 데이터의 저장을 배열이 아닌 ‘노드(node)’를 사용한 자료구조
- 노드와 노드가 서로 연결되어 있는 형태로 데이터를 저장
    - 각 노드가 다음 노드의 참조값을 저장
- 데이터의 입력, 삭제가 빠르다
- 단방향 탐색으로 인해 데이터의 탐색 속도가 느림

## 이중 연결 리스트(Double Linked List)

- 다음 노드뿐 아니라 이전 노드의 참조값을 저장함으로써 양방향 탐색 가능
- 기본 연결 리스트보다 탐색 속도가 빠름

### 노드의 추가

1. 25의 다음 노드로 30 연결
2. 30의 이전 노드로 25 연결
3. 20의 다음 노드로 25 연결
4. 25의 이전 노드로 20 연결

### 노드의 제거
1. 
2.
3.
4.
