# 투 포인터 & 슬라이딩 윈도우
![tpsw PNG](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/53993041/3fc2e0d5-d7d6-4fb3-8221-87d43d849a53)
- 공통점
  - 시간 복잡도 : O(N)
    - 선형 공간(1차원 배열)을 2회 이상 반복적으로 탐색해야 할 경우, O(N^2) 이상 걸릴 시간 복잡도를 부분 배열을 활용하여 O(N)으로 줄일 수 있다.
  - 부분 배열의 합을 구할때 유용하게 사용할 수 있다.

- 차이점
  - 부분 배열 길이의 변화 여부
    - 길이 변화 X : 슬라이딩 윈도우
    - 길이 변화 O : 투 포인터

# 슬라이딩 윈도우

- **일정한 길이의 범위**를 이동하여 조건에 맞는 값을 찾는 알고리즘이다.
- 한 칸씩 이동 하기 때문에 공통된 부분은 유지하고 **처음과 끝 원소만 갱신**한다.
    
![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb2CWOH%2Fbtq4RVfK580%2FXHj7HfOVxm0CDNcWxek72k%2Fimg.jpg)
    

![image](https://blog.kakaocdn.net/dn/bdvVkW/btrsW92CXSO/08PAFS6ET6tttAWLcJJ9c0/img.png)

**주어진 배열 :** **3 -2 -4 -9 0 3 7 13 8 -3**

**배열의 길이 : 2**

길이가 2인 윈도우를 한 칸씩 밀어가며 확인

![image](https://blog.kakaocdn.net/dn/WLqDY/btrsTc0nKZi/QSxIIfZFqBUxoTk0HiW78k/img.png)

![image](https://blog.kakaocdn.net/dn/cTtmdu/btrsXFz9ax0/wlpppuWeZhYH3xnbOLfQfK/img.png)

![image](https://blog.kakaocdn.net/dn/b3QF5L/btrsXr9SNZQ/dXzPJes0KSp8cSrRffdkrK/img.png)

- 코드
```java
public static int slidingWindow() {
    	int max = 0;
    	int sum = 0;
    	
    	for(int i = 0; i < n; i++) {
    		sum += arr[i];
    		
    		// 최초에 나온 합을 최댓값으로 잡아놓음
    		if(i == k - 1) {
    			max = sum;				
    		}
    		
    		// 처음 길이를 벗어났을 때 부터 한칸씩 밀어주면서 최댓값 비교
    		if(i >= k) {
    			sum -= arr[i - k];
    			max = Math.max(max, sum);   
    		}
    	}   	
    	return max;
    }
```

 
# 투 포인터
- 데이터에 순차적으로 접근해야 할 때 **두 개의 점 위치를 조절**하여 조건에 부합하는지 판단하는 알고리즘이다.
- 공통 부분을 제외하고 **포인터로 이동하는 원소의 처리**만 하면 되므로 유용하게 쓰일 수 있다.
- 투 포인터의 2가지 형태
    1. 두 개의 포인터가 같은 방향으로 진행
    2. 포인터 하나는 왼쪽 끝 → 오른쪽 & 다른 하나는 오른쪽 끝 → 왼쪽으로 진행

1. **두 포인터가 같은 방향으로 진행하는 경우**
![image](https://blog.kakaocdn.net/dn/be6ejH/btrsTcTxrPR/3gTcfA7DcLeYP5JWFeSgzK/img.png)

(**Left**, **Right**) 혹은 (**start**, **end**) 두 개의 포인터를 사용한다.

초기 상태에선 둘 다 arr[0] 값을 가리킨다.

목표합이 **M** 일 때,

1. L ~ R 까지의 부분배열의 합이 M 보다 **크면** L + 1
2. L ~ R 까지의 부분배열의 합이 M 보다 **작으면** R + 1
3. L ~ R 까지의 부분배열의 합이 M **이면** L + 1, 결과 카운트 + 1


![image](https://blog.kakaocdn.net/dn/be6ejH/btrsTcTxrPR/3gTcfA7DcLeYP5JWFeSgzK/img.png)

1 < M 이므로 → R 한 칸 이동

![image](https://blog.kakaocdn.net/dn/CHSkV/btrsW83E971/pKvmBYXCdUPik5jm4t1Mf1/img.png)

3 < M 이므로 → R 한 칸 이동

![image](https://blog.kakaocdn.net/dn/c8Rjas/btrsUcZ3Dp9/Ns93K08TjHNT3RmIk59FQK/img.png)

6 > M 이므로 → L 한 칸 이동

![image](https://blog.kakaocdn.net/dn/ZKUyq/btrsTd5WCWU/8y0eMmBj2c10AUcHDPEzOK/img.png)

현재 정답을 찾았으므로 정답 카운트를 +1 해주고

다시 탐색하기 위하여 L도 한 칸 이동한다.

- 코드
```java
public static int twoPointer() {
    	int left = 0; 
    	int right = 0;
    	int sum = 0;
    	int count = 0;
    	
    	while (true) {
    		
      		// 1 + 3. L ~ R 까지의 부분배열의 합이 M 보다 크거나 같으면 L + 1
      		if(sum >= m) {
      			sum -= arr[left++];
      		}
      		// 종료 조건 (right이 마지막 범위를 넘어갔을 경우)
      		else if(right == n) {
      			break;
      		}
      		// 2. L ~ R 까지의 부분배열의 합이 M 보다 작으면 R + 1
      		else if(sum < m) {
      			sum += arr[right++];
      		}
      		// 3.L ~ R 까지의 부분배열의 합이 M 이면 결과 카운트 + 1
      		if(sum == m){
      			count++;
      		}
    	}	
    	return count;
}
```

2. **두 포인터가 서로 다른 방향으로 진행하는 경우**
- 두 수의 합 구하기 등에 사용

- 코드
```java
int left = 0; 
int right = arr.length-1;
while(left < right) {
    int sum = arr[left] + arr[right];
    // 가장 큰 원소와 더했는데 목표합보다 작거나 같으면 left++
    if(sum <= x) {
        left++;
    }
    // 가장 큰 원소와 더했는데 목표합보다 크면 r--
    else if(sum > x) {
        right--;
    }
    if(sum == x) answer++;
  
}
```
# 주의!
- 정렬하면 안 되는데, 사용했는지 확인하기
- 이분탐색과 혼동하지 말 것
