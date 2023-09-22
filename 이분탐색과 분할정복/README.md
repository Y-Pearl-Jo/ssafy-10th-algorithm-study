# 1. 이분 탐색이란?

- `이진 탐색(이분 탐색)` 알고리즘은 **정렬되어 있는 리스트에서 탐색 범위를 절반씩 좁혀가며 데이터를 탐색하는 방법**이다.
- `이진 탐색`은 **배열 내부의 데이터가 정렬되어 있어야만 사용할 수 있는** 알고리즘이다.
- 변수 3개(`start, end, mid`)를 사용하여 탐색한다. **찾으려는 데이터와 중간점 위치에 있는 데이터를 반복적으로 비교해서 원하는 데이터를 찾는 것**이 `이진 탐색`의 과정이다.

# 2. 시간 복잡도

- **시간 복잡도는 `O(logN)`이다.** **(여기서 log는 log₂이다.)**
- 단계마다 탐색 범위를 반으로(÷2) 나누는 것과 동일하므로 위 시간 복잡도를 가지게 된다.

<aside>
💡 예를 들어 처음 데이터의 개수가 32개라면, 이론적으로 1단계를 거치면 약 16개의 데이터가 남고, 2단계에서 약 8개, 3단계에서 약 4개의 데이터만 남게 된다.즉, `이진 탐색(이분 탐색)`은 탐색 범위를 절반씩 줄이고, `O(logN)`의 시간 복잡도를 보장한다.

</aside>

# 3. 검색 과정

![binary-and-linear-search-animations](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/60a3e398-ea4f-4dd8-b1e6-1b113aef09d8)


## - 먼저 배열의 가운데를 결정한다.
![Untitled](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/3f44dfcd-3144-4ef5-9733-0f97bbc8310f)
<aside>
💡 mid  = low + (high - low) / 2

= 0 + (9-0)/2

= 4

</aside>


## - 중앙 값과 검색 값을 비교한다.
![Untitled (1)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/404579c8-6f75-48a1-9cce-9850ffddf1f8)
<aside>
💡 A [4] < key 이므로 배열의 오른쪽 구간을 검색 범위로 정합니다.

low = mid + 1

= 4 + 1

= 5

</aside>


## - 중앙 값을 결정한다.
![Untitled (2)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/60468ea5-09f2-4d7b-a116-d4c9814d0e5a)
<aside>
💡 mid = 5+ (9-5)/2

= 7

</aside>


## - 중앙 값과 검색 값을 비교한다.
![Untitled (3)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/43daf7f1-a3f6-4841-974f-18cdaee142cf)
<aside>
💡 A [7] > key 이므로 배열의 왼쪽 구간을 탐색 범위로 정합니다.

high = mid -1

= 7 - 1

= 6

</aside>


## - 중앙 값을 결정한다.
![Untitled (4)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/3142d386-78be-4965-9706-ff28d0a0ed20)
<aside>
💡 mid = 5 + (6-5)/2

= 5

</aside>

## - A[5] = Key 이므로 탐색 종료


# 4. 구현

- 반복문

```java
int binarySearch (int arr[], int low, int high, int key) {
  while (low <= high) {
    int mid = low + (high-low) / 2;
    
    if (arr[mid] == key) // 종료 조건1 검색 성공.
      return mid; 
    else if (arr[mid] > key) 
      high = mid - 1;      
    else 
      low = mid + 1;
  }
  return -1 ; // 종료 조건2 (low > high) 검색 실패.
}
```

- 재귀

```java
int binarySearch (int arr[], int low, int high, int key) {
  
  if (low > high) // 종료 조건2 검색 실패.
    return -1;  

  int mid = low + (high-low)/2;

  if (arr[mid] == key) // 종료 조건1 검색 성공.
    return mid;
  else if (arr[mid] > key)
    return binarySearch(arr, low, mid-1, key);
  else
    return binarySearch(arr, mid+1, high, key);
}
```

## ✅중간 값 구할 때 팁

> int mid = low + (high - low) / 2
> 

단순히 다음 방식으로 중간 값을 구할 수도 있지만

> int mid = (low + high) / 2
> 

두번째 방식은 low + high 값이 int 값 (2^31 -1)의 범위보다 크다면 음수 값으로 오버플로우 될 것이고

이 음수 값을 2로 나누면 mid 값은 음수가 되기 때문에 문제가 될 수 있다.

low + high값이 범위를 넘어서는 경우가 있다면 첫번째 방식으로 중간 값을 구해야 한다.

그렇지 않다면 두번째 방식이 연산이 간단하기 때문에 첫번째 방식보다 효율적이다

---

# 1. 분할 정복이란?

- 해결할 문제를 **여러 개의 작은 부분으로 나누고**(divde), **나눈 작은 문제를 해결**(Conquer)하여, 필요하다면 그 해를 통합(Combine)하는 형식의 문제 풀이를 말한다.


    ![img](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/8c6c9b6f-138d-43ee-b32b-e212cff3fa13)

<aside>
💡 ① 분할 : 전체 데이터를 반으로 지속적으로 분할한다. 직접 문제가 해결되는 수준까지(1개 남을 때까지)

   ② 정복 : 데이터가 1개가 남으면 그 자체로 이미 정렬된 상태이다. 분할된 2개의 데이터를 정렬한다.(하위 문제 해결)

   ③ 병합 : 정렬된 하위 문제를 병합하여 전체 내역을 정렬한다.

</aside>

# 1. 합병 정렬
![Untitled (5)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/2e25aee6-0f77-45ca-ab18-8475cb345fbb)


## 분할 ( 가장 작은 단위까지 분할)
![Untitled (6)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/16928895-a698-479c-b935-4ae9cbd15699)


🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻
![Untitled (7)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/48e3611b-3ce9-475f-8a4e-93e04cb52639)


🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻🔻
![Untitled (8)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/0ae8cc32-cec1-496d-841e-83ac211c0642)
<aside>
💡 배열을 반으로 나누고
가장 작은 단위가 나올때까지 쪼갠다.
</aside>


## 정렬
![Untitled (9)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/44c66abc-70f9-47fb-9979-97eac4a8caa8)
<aside>
💡 각 구간에서 쪼개지기 전에 같이 있던 원소들끼리 비교하여 정렬한다.
</aside>


![Untitled (10)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/09a24e1a-1b62-43d9-a675-565f3d6e088c)
<aside>
💡 2개를 4개로 정렬병합 한다.
</aside>


![Untitled (11)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/29ee293c-01f4-49f5-884d-60f77f53fb52)
<aside>
💡 합병할 때 정렬이 되는 로직
![Untitled (12)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/1e293220-e3ec-488c-a1d4-29768d80a695)
각 요소의 첫번째 원소끼리 비교를 한다.

  
  ![Untitled (13)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/859c6001-52ae-464f-8c67-a100b792d631)
작은 수를 넣고, i는 한칸 이동한다.


![Untitled (14)](https://github.com/JG-1011/ssafy-10th-algorithm-study/assets/116864863/b8918deb-33c2-4bfd-8bb7-a1c63610e761)

한쪽이 배열 범위를 넘어가면 멈추고 남은 원소를 뒤에 집어넣는다.

</aside>

# 2. 분할정복과 동적 프로그래밍

### 공통점

- 주어진 문제를 작게 쪼개서 하위 문제를 해결하고 연계적으로 큰 문제를 해결한다.

### 차이점

- 분할정복은 하위 문제가 동일하게 중복이 일어나지 않은 경우에 쓰며
- 동일한 중복이 일어나면 동적 프로그래밍을 쓴다.
- 분할정복은 Top-Down만 가능하지만 동적 프로그래밍은 Bottom-Up도 가능

<aside>
💡 예를 들어
병합 정렬을 수행 시에 작은 하위 문제로 쪼개지지만 중복하여 하위 문제가 발생하지 않는다. 무슨 말이냐면, **분할된 부분 부분은 모두 독립적이고, 동일한 부분을 중복하지 않는다**는 것이다.

중복되는 경우는 어떤 것인가? 피보나치 수열을 생각해보자.

피보나치 수열은 $fn = fn-1 + fn-2$ 라는 수식을 갖는다.

즉, n이 어떤 수가 되던, n-1번째 수와 n-2번째 수를 더해야 한다.

즉, n=5일 때, n-1은 4이고, n-2는 3인데, 3을 구하기 위해선 n-2가 1인 경우까지 하위 문제로 내려가야 한다. 즉, **n이 어떤 수이든, 그 하위 수를 구하는 부분은 중복**해서 나타난다!

**그래서 병합 정렬은 분할 정복으로, 피보나치 수열은 동적 프로그래밍으로 해결이 가능하다.**

</aside>

<aside>
💡 Top-Down vs Bottom-Up

- Top-down
    
    가장 큰 문제를 방문 후 작은 문제를 호출 하여 답을 찾는 방식
    
    재귀 호출을 이용해 구현
    
    점화식을 이해하기 쉽다.
    
    ```java
    # 한 번 계산된 결과를 메모이제이션(Memoization)하기 위한 리스트 초기화
      d = [0] * 100
    // 메모이제이션
    // 한 번 구한 결과를 메모리 공간에 메모해두고, 같은 식을 다시 호출하면 메모한 결과를 그대로 가져오는 기법
    
      # 피보나치 함수(Fibonacci Function)를 재귀함수로 구현 (탑다운 다이나믹 프로그래밍)
      def fibo(x):
          # 종료 조건(1 혹은 2일 때 1을 반환)
          if x == 1 or x == 2:
              return 1
          # 이미 계산한 적 있는 문제라면 그대로 반환
          if d[x] != 0:
              return d[x]
          # 아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환
          d[x] = fibo(x - 1) + fibo(x - 2)
          return d[x]
    
      print(fibo(99))
    ```
    
- Bottom-up
    
    가장 작은 문제들 부터 답을 구해가며 전체 문제의 답을 찾는 방식
    
    반복문을 이용해 구현
    
    함수를 재귀 호출하지 않기 때문에 시간과 메모리 효율성이 좋다.
    
    ```java
    # 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
      d = [0] * 100
    
      # 첫 번째 피보나치 수와 두 번째 피보나치 수는 1
      d[1] = 1
      d[2] = 1
      n = 99
    
      # 피보나치 함수(Fibonacci Function) 반복문으로 구현(보텀업 다이나믹 프로그래밍)
      for i in range(3, n + 1):
          d[i] = d[i - 1] + d[i - ㅕㄴ
    ```
    
</aside>

# 3. 구현

```java
public class MergeSort{
    public static void main(String[] args){
        int[] arr = new int[100];
        for(int i=0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * 100);
        }
        
        mergeSort(arr, 0, arr.length-1);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }
    
    public static void mergeSort(int[] arr, int start, int end){
        if(start == end) return;
        
        int mid = (start+end)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);
        
        int[] temp = new int[end-start+1];
        int idx = 0;
        int left = start;
        int right = mid+1;
        while(left <= mid && right <= end){
            temp[idx++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        while(left <= mid){
            temp[idx++] = arr[left++];
        }
        while(right <= end){
            temp[idx++] = arr[right++];
        }
        for(int i=start; i <= end; i++){
            arr[i] = temp[i-start];
        }
    }
}
```

