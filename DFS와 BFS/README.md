## 그래프를 표현하는 3가지 방법

1. **엣지 리스트(Edge List)**
2. **인접 행렬(Adjacency Matrix)**
3. **인접 리스트(Adjacency List)**

## 1. 엣지 리스트(Edge List)

- **엣지를 중심**으로 그래프를 표현
- **벨만 포드나 크루스칼 알고리즘**에 사용 (**노드 사이 최단 거리 알고리즘**)
- 노드 중심 알고리즘에는 잘 사용하지 않음

### 가중치가 없는 경우

- **엣지가 N개일 때 N * 2 크기의 배열**로 표현 가능
- **출발 노드와 도착 노드**를 저장
- 방향이 없는 경우 [1, 2] 와 [2, 1]은 같으나 방향이 있는 경우 다른 표현이 됨

![image](https://github.com/jaesin463/ssafy-10th-algorithm-study/assets/117919180/6d849960-f443-493e-9053-0cfa97d51908)


### 가중치가 있는 경우

- **엣지가 N개일 때 N * 3크기의 배열**로 표현 가능
- **출발 노드와 도착 노드, 가중치**를 저장

![image](https://github.com/jaesin463/ssafy-10th-algorithm-study/assets/117919180/0041726e-2e8c-4185-8f30-d2a175985dbd)


## 2. 인접 행렬(Adjacency Matrix)

- **노드를 중심**으로 그래프 표현
- **노드가 N개일 때 N * N 크기의 배열** 필요
- 가중치의 유무에 따라 배열에 저장하는 값이 달라짐
- **장점** :
    - **구현이 쉽다**
    - 에지의 여부와 가중치 값을 **배열에 직접 접근하면 바로 확인** 가능
- **단점** :
    - **노드와 관련 된 엣지를 탐색하려면 N번 접근**해야함
    - 노드 개수에 비해 엣지가 적을 때 **공간 효율성이 떨어짐**
    - 노드의 개수가 많은 경우 2차원 배열 선언이 안 될 수도 있음
        - 노드가 **3만개가 넘으면 java heap space 에러** 발생

### 가중치가 없는 경우
![image](https://github.com/jaesin463/ssafy-10th-algorithm-study/assets/117919180/11ec87ad-f943-4034-9f30-52e7de614c4d)


### 가중치가 있는 경우

![image](https://github.com/jaesin463/ssafy-10th-algorithm-study/assets/117919180/3e84b379-85ae-4538-a998-45297e485265)


## 3. 인접 리스트(Adjacency List)

- **노드의 개수만큼 ArrayList**를 선언 ( ArrayList<Object o>[N] )
- **장점** :
    - 노드와 연결되어 있는 **에지를 탐색하는 시간이 매우 뛰어남**
    - 노드의 개수가 많아도 **공간 효율이 좋아 메모리 초과 에러가 발생하지 않음**
- **단점** :
    - 다른 방법에 비해 **구현이 복잡**
- 실제 **코딩 테스트에서 선호하는 그래프 구현 방식**

### 가중치가 없는 경우

![image](https://github.com/jaesin463/ssafy-10th-algorithm-study/assets/117919180/0e4bbc14-f85b-4213-bfa8-9599550674da)


### 가중치가 있는 경우

![image](https://github.com/jaesin463/ssafy-10th-algorithm-study/assets/117919180/d3c23954-4c53-41b1-b6b2-ab1bc3de5388)
