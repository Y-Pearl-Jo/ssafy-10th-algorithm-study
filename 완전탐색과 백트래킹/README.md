알고리즘 종류

설명

장점

단점

---

브루트 포스

‘모든 경우의 수를 탐색’하면서 원하는 결과를 얻는 알고리즘

가능한 모든 경우를 다 검색하기 때문에 예상된 결과를 얻을 수 있음

경우의 수가 많을 경우 시간이 오래 걸림

---

비트마스크

‘모든 경우의 수’를 이진수료 표현하고 ‘비트 연산’을 통해 원하는 결과를 빠르게 얻는 알고리즘

이진수 연산을 이용하여 계산 속도가 빠름

경우의 수가 많아질수록 메모리 사용량이 늘어남

---

**백트래킹**

결과를 얻기 위해 진행하는 도중에 ‘막히게 되면’ 그 지점으로 다시 돌아가서 ‘다른 경로를 탐색’하는 방식을 의미합니다. 결국 모든 가능한 경우의 수를 탐색하여 해결책을 찾는다.

경우의 수를 줄이면서도 모든 경우를 탐색할 수 있음

재귀 함수를 이용하기 때문에 스택 오버플로우가 발생할 가능성 있음

---

**순열**

‘순열’을 이용하여 모든 경우의 수를 탐색하는 방법입니다. 순열은 서로 다른 n개 중에서 r개를 선택하여 나열하는 방법을 의미한다.

경우의 수가 적을 때 사용하면 유용함

경우의 수가 많을 경우 시간이 오래 걸림

---

**재귀함수**

자기 자신을 호출하여 모든 가능한 경우의 수를 체크하면서 최적의 해답을 얻는 방식을 의미한다.

코드가 간결하며, 이해하기 쉽습니다.

스택 오버플로우가 발생할 가능성이 있음

---

**DFS/BFS**

깊이 우선 탐색(DFS: Depth-First Search)

- 루트 노드에서 시작하여 다음 분기로 넘어가기 전에 해당 분기를 완벽하게 탐색하는 방법을 의미합니다.

**너비 우선 탐색(Breadth-First Search: BFS)**

- 루트 노드에서 시작하여 인접한 노드를 먼저 탐색하는 방법을 의미합니다.

미로 찾기 등에 유용함

최악의 경우, 모든 노드를 다 방문해야 하므로 시간이 오래 걸림

# 시간 복잡도

---

![image](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/93261483-1d07-457e-a1d1-2099800120c8)


**알고리즘**

**시간 복잡도**

---

브루트포스

O(nm)

---

비트마스크

O(2^n*n)

---

백트래킹

최악의 경우 O(n!)

---

순열

O(n!)

---

재귀함수

O(n)

---

DFS/BFS

O(V+E)

<aside>
💡

**시간 복잡도에 따른 효율적인 알고리즘 순서**

비트마스크 > DFS/BFS > Brute-Force > 재귀함수 > 순열 > 백트래킹

</aside>

# 비트마스크

## 1. 비트마스크란?

**‘이진수’를 ‘비트 연산’을 통해 경우의 수를 줄여가며 탐색하는 방식을 의미합니다**

비트 마스크를 사용하면 하나의 변수에 여러 개의 상태 정보를 저장할 수 있으며 이를 통해 복잡한 조건문을 간단하게 처리할 수 있습니다. 이 방법은 비트 연산을 사용하기 때문에 빠르게 계산할 수 있어서, 경우의 수가 많은 경우에 유용합니다.

<aside>
💡 완전 탐색에서 비트마스크란?
모든 경우의 수를 탐색하여 최적의 결과를 찾아가는 완전탐색에서 비트마스크는 모든 경우의 수를 이진수로 표현하여 빠르게 계산을 해 나아가는 방식입니다.

</aside>

## 2. 비트마스크 연산

![image](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/3e49e797-506d-4b9d-9533-3512d754d6ce)


```java
// 연산 간단한 예시
int a = 10; // 1010
int b = 12; // 1100

int result1 = a & b; // 1000
int result2 = a | b; // 1110
int result3 = a ^ b; // 0110
int result4 = a << 1; // 10100
int result5 = a >> 1; // 0101
```

## 3. 비트마스크의 사용 예시

### 1. 권한 관리

보통 권한은 4가지 이상으로 구분되어 있는 경우가 많습니다. 이때 각 권한을 비트로 표현하여 하나의 정수값으로 나타내면 매우 간편해집니다.

```java
public static final int PERMISSION_READ = 1 << 0; // 0001
public static final int PERMISSION_WRITE = 1 << 1; // 0010
public static final int PERMISSION_DELETE = 1 << 2; // 0100
public static final int PERMISSION_EXECUTE = 1 << 3; // 1000

int userPermission = PERMISSION_READ | PERMISSION_WRITE; // 0011
int groupPermission = PERMISSION_READ | PERMISSION_EXECUTE; // 1001

boolean hasReadPermission = (userPermission & PERMISSION_READ) != 0; // true
boolean hasDeletePermission = (groupPermission & PERMISSION_DELETE) != 0; // false
```

### 2. 집합 관리

집합을 비트로 표현하여 메모리를 절약할 수 있습니다. 예를 들어, 0부터 31까지의 정수 중에서 3, 5, 7번째 원소를 포함하는 집합을 나타내면 다음과 같이 표현할 수 있습니다.

```java
int set = (1 << 3) | (1 << 5) | (1 << 7); // 0010 1010 1000

boolean hasElement5 = (set & (1 << 5)) != 0; // true
boolean hasElement6 = (set & (1 << 6)) != 0; // false
```

### 3. 상태 플래그 관리

여러 상태를 하나의 정수 값으로 나타내어 관리할 수 있습니다. 예를 들어, 주어진 수가 2의 거듭제곱인지 여부를 판단할 때 다음과 같은 방법을 사용할 수 있습니다.

```java
public static final int FLAG_POWER_OF_TWO = 1 << 0; // 0001
public static final int FLAG_NEGATIVE = 1 << 1; // 0010

int number = 8; // 2의 거듭제곱
int flags = 0;

if ((number & (number - 1)) == 0) { // 2의 거듭제곱인 경우
    flags |= FLAG_POWER_OF_TWO;
}

if (number < 0) { // 음수인 경우
    flags |= FLAG_NEGATIVE;
}

if ((flags & FLAG_POWER_OF_TWO) != 0) {
    System.out.println(number + " is power of two");
}

if ((flags & FLAG_NEGATIVE) != 0) {
    System.out.println(number + " is negative");
}
```

# 백트래킹이란?

---

해를 찾는 도중 해가 아니어서 막히면, 되돌아가서 다시 해를 찾아가는 기법을 말한다. (최적화 문제와 결정 문제를 푸는 방법이 된다)

![image](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/883887af-69d6-4bba-93b7-aa5ea0042847)


# DFS vs 백트래킹

---

DFS

깊이 우선 탐색 DFS는 **가능한 모든 경로(후보)를 탐색**한다.

불필요할 거 같은 경로를 사전에 차단하지 않기 때문에 **경우의 수를 최적으로 줄이지 못한다.**

따라서 N!의 경우의 수를 가지는 문제는 DFS로 처리하지 못할 가능성이 매우 크다.

백트래킹

백트래킹은 **해를 찾아가는 도중에 지금의 경로가 해가 될 거 같지 않으면, 더 이상 깊이 들어가지 않고 이전 단계로 다시 돌아간다.** 이를 **가지치기**라고 하는데, **불필요한 부분을 쳐내고 최대한 올바른 방향으로 나아가는 방식**이기 때문에 DFS보다 효율적이다.

<aside>
💡 완전 탐색에서 백트래킹이란?
모든 경우의 수를 탐색하여 최적의 결과를 찾아가는 완전탐색에서 백트래킹은 해결책을 찾아가는 도중에 막히게 되면 다시 돌아가서 다른 경로로 탐색을 하여 결국 모든 가능한 경우의 수를 탐색하여 해결책을 찾아가는 방식으로 사용된다.

</aside>
