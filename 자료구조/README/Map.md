## 맵이란?

맵이란 순차적으로 저장하는 List나 Array와 달리 Key와 Value 방식으로 저장하는 자료구조

## 맵의 특징

- 순차적으로 접근해서 값을 구하지 않고 Key를 통해 Value를 얻는 구조
- Value는 중복이 허용되나 Key는 중복이 허용 되지 않는다.
- 위의 특성으로 인해 순서를 유지 할 필요가 없다.

## 맵의 선언

```java
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
class Map{
	public static void main(String[] args){
		Map<Object key, Object value> name = new HashMap<>();	
		Map<Object key, Object value> name = new TreeMap<>();	
		Map<Object key, Object value> name = new LinkedHashMap<>();	
	}
}
```

- 맵은 HashMap, TreeMap, LinkedHashMap에 구현되어 있다.
    - MashMap : Map을 구현하는 가장 기본 클래스
        - 데이터의 검색 속도가 빠르다.
    - TreeMap : Key와 Value를 한 쌍으로 이진트리 기법으로 저장
        - 데이터의 추가, 삭제 속도가 빠르다.
        - Key를 기준으로 한 정렬을 통한 탐색에 용이
    - LinkedHashMap : 데이터를 입력한 순서대로 저장
        - 배열과 리스트처럼 인덱스로 접근 할 수 있다

## 맵의 메서드

- put(K key, V value) : 데이터를 맵에 추가
    - 기존에 Key에 해당하는 Value값이 있으면 해당 Value를 반환
    - 기존에 Key가 없었을 경우 null 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    System.out.println(map.put(2, 3));
    //[1 = 2, 2 = 3]
    System.out.println(map.put(1, 4));
    //[1 = 4, 2 = 3]
    
    //출력 결과
    null
    4
    ```
    
- putIfAbsent(K key, V value) : 기존 데이터에 key가 없으면 저장
    - 기존에 Key에 해당하는 Value값이 있으면 해당 Value를 반환
    - 기존에 Key가 없었을 경우 null 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    System.out.println(map.putIfAbsent(2, 3));
    //[1 = 2, 2 = 3]
    System.out.println(map.putIfAbsent(1, 4));
    //[1 = 2, 2 = 3]
    
    //출력 결과
    null
    2
    ```
    
- remove( Object Key ) : key와 일치하는 데이터를 삭제하고 해당 value를 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    System.out.println(map.remove(2));
    //[1 = 2]
    
    //출력 결과
    3
    ```
    
- remove(Object Key, Object Value) : key와 value가 일치하는 데이터를 삭제
    - 삭제 성공 여부를 true, false(boolean) 형식으로 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    System.out.println(map.remove(2, 4));
    //[1 = 2, 2 = 3]
    System.out.println(map.remove(2, 3));
    //[1 = 2]
    
    //출력 결과
    false
    true
    ```
    
- replace(K key, V value) : key와 일치하는 기존 데이터의 value를 변경하고 기존 value를 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    System.out.println(map.replace(2, 4));
    //[1 = 2, 2 = 4]
    
    //출력 결과
    3
    ```
    
- replace(K key, V oldValue, V newValue) :
    - key와 oldValue가 동시에 일치하는 데이터의 value를 newValue로 변경합니다.
    - 수정 성공 여부를 true, false(boolean) 형식으로 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    System.out.println(map.replace(2, 4, 3));
    System.out.println(map.replace(2, 3, 4));
    //[1 = 2, 2 = 4]
    
    //출력 결과
    false
    true
    ```
    
- containsKey(Object key) : key와 일치하는 데이터가 있는지 여부를 boolean으로 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    System.out.println(map.containsKey(2));
    
    //출력 결과
    true
    ```
    
- containsValue(Object value) : value와 일치하는 데이터가 있는지 여부를 boolean으로 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    map.put(3, 3);
    //[1 = 2, 2 = 3, 3 = 3]
    System.out.println(map.containsValue(2));
    
    //출력 결과
    true
    ```
    
- get(Object key) : key에 맵핑되어 있는 value를 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    System.out.println(map.get(2));
    
    //출력 결과
    3
    ```
    
- getOrDefault(Object key, V defaultValue)
    - key와 맵핑 된 value를 반환하고 없으면 defaultValue를 반환
    
    ```java
    map.put(1, 2);
    //[1 = 2]
    map.put(2, 3);
    //[1 = 2, 2 = 3]
    System.out.println(map.getOrDefault(2, 4));
    System.out.println(map.getOrDefault(3, 4));
    
    //출력 결과
    3
    4
    ```
