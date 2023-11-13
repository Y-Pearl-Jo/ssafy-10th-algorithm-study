일일강사 : 정혜진
# 구간합

합 배열을 이용하여 시간 복잡도를 줄이기 위해 사용하는 알고리즘.

### **배열 A가 있을 때, 합배열 S의 정의**

A[0] 부터 A[i]까지의 합

`S[i] = A[0] + A[1] + A[2] + … + A[i-1] + A[i]` 

ex.

| 인덱스 | 0 | 1 | 2 | 3 | 4 | 5 |
| --- | --- | --- | --- | --- | --- | --- |
| 배열 A | 15 | 13 | 10 | 7 | 3 | 12 |
| 합 배열 S | 15 | 28 | 38 | 45 | 48 | 60 |

### **A[i] 부터 A[j] 까지의 배열 합을 구할 때, 시간복잡도**

합 배열 없이 구하는 경우 : 최악의 경우 ( i가 0이고 j가 N인 경우) O(N)

합 배열을 사용한 경우 : O(1)

### **합 배열 S를 만드는 공식**

`S[i] = S[i-1] + A[i]`

### **구간 합을 구하는 공식**

i에서 j까지 구간 합

`S[i] - S[i-1]`

ex. A[2]부터 A[5]까지의 구간 합 = S[5]-S[1] 

![Untitled](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/edd284e5-8431-4e49-85d8-7806228a4e7f)


# 세그먼트 트리

### 0. 세그먼트 트리란?

주어진 데이터의 **구간 합**과 **데이터 업데이트**를 빠르게 수행하기 위해 고안해낸 자료구조

![Untitled (1)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/509c3564-f0bd-4ea2-8303-612d7e379fec)

구간 합 트리

**종류** : 구간 합, 최대/최소 구하기

**구현 단계**

1. 트리 배열의 크기 구하기

2. 리프노드에 원본 데이터 입력하기

3. 데이터 업데이트하기

4. 구간값(구간합,최댓값,최솟값)구하기

**시간복잡도**

트리의 높이가 log(N)이므로 구하는 시간 복잡도도 O(logN)

### 1. **트리 배열의 크기 구하기**

- 세그먼트 트리는 이진 트리의 형태이다.
    
    리프노드에 원본 배열을 담을 것이므로 리프노드의 개수가 데이터의 개수(N) 이상이 되어야한다. 
    
    모든 노드가 꽉 차있는 이진 트리를 완전 이진 트리라고 하는데, 이 경우가 제일 많은 데이터를 가지는 경우이다. 
    
    **따라서, 원본배열의 크기 n이 주어졌을 때, 세그먼트 트리의 크기는 완전 이진 트리의 크기를 구하면 된다.**
    
- 트리 배열의 높이는 2^h≥ N을 만족하는 h의 최솟값을 구한 후 2^(h+1) 를 트리 배열의 크기로 정의하면 된다
    
    ex. N = 8일 때 → 16, N = 5일 때 → 16
    
- cf. 쉽고 간단하게 트리 배열의 크기를 대충 N*4 로 만드는 사람도 있다.

```java
static int getTreeSize() {
		int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
		return (int) Math.pow(2, h); // 인덱스 1부터
}
```



### 2. 리프노드에 원본 데이터 입력하기

리프 노드에서부터 시작하여 상위 노드로 올라가며 각 노드에 값을 누적시킨다.

```java
// init(1, n, 1); // 세그먼트 트리 초기화

static long init(int start, int end, int node) {

		// 리프노드에 도달했을 경우, 리프노드에 원본 데이터를 입력한다.
		if (start == end)
			return tree[node] = arr[start];

		// 리프노드가 아니라면, 자식노드로 이동하여 구간 합을 더한다.
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
}
```

- init(1, n, 1);에서 시작구간이 1에서 n까지인 이유는 원본 배열의 인덱스와 트리의 노드 인덱스를 맞추기 위함이다!!
- 첫 번째 ‘1’ 은 원본 배열 arr의 가장 왼쪽 인덱스를 나타내고 ‘n-1’은 가장 오른쪽 인덱스를 나타낸다.
- 두 번째 ‘1’ 은 세그먼트 트리의 루트노드의 인덱스를 나타낸다.
    
    → 트리의 1번노드(루트노드)는 원본 배열의 1번 인덱스부터 n번 인덱스까지의 구간합이라는 사실을 기억하면 도움이 된다.
    

**ex. 원본 배열이 {5, 8, 4, 3, 7, 2, 1, 6}**

![Untitled (2)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/c629862a-1b6a-491f-9095-8d0c18cab289)

![Untitled (3)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/1f7be686-f5af-4d1f-b42c-0a87c83b39ef)

**실제 트리 모양으로 구조화한 모습**

![Untitled (4)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/2faed589-8f28-4fd6-a625-247b7e205404)

### 3. 데이터 업데이트하기

주어진 인덱스(idx)에 해당하는 리프 노드부터 시작하여 상위 노드로 올라가며 값을 업데이트해야 한다.

**ex. 5번 데이터의 값을 7에서 10으로 업데이트**

![Untitled (5)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/3bd77caa-50c6-4627-88f3-4792a3c64c64)
진한 주황색이 업데이트 구간이다.

트리를 탐색하면서 idx를 포함하고 있는 업데이트 구간을 찾아 갱신해주면 된다. 

`if(start <= idx && idx <= end)`

```java
static void update(int start, int end, int node, int idx, long diff) {

		// 업데이트할 구간에 현재 노드가 속한다면
		if (start <= idx && idx <= end) {
			tree[node] += diff; // 현재 노드에 변경된 값만큼 더한다.
		} else
			return; // 업데이트할 구간에 현재 노드가 속하지 않으면 재귀 종료

		// 리프노드에 도달했을 경우, 재귀 종료
		if (start == end)
			return;

		// 리프노드가 아니라면, 자식노드로 이동하여 업데이트를 수행한다.
		int mid = (start + end) / 2;
		// 왼쪽 자식과 오른쪽 자식으로 나누어 업데이트를 수행한다.
		update(start, mid, node * 2, idx, diff);
		update(mid + 1, end, node * 2 + 1, idx, diff);

}
```

- 구간 합 : 원래 데이터와 변경 데이터의 차이만큼 부모 노드로 올라가면서 업데이트
- 최댓값 구하기 : 변경 데이터와 (같은 부모를 가지고 있는) 다른 자식 노드와 비교해 더 큰 값으로 업데이트
- 최솟값 구하기 : 변경 데이터와 (같은 부모를 가지고 있는) 다른 자식 노드와 비교해 더 작은 값으로 업데이트
    
    

### 4. 구간값(구간합,최댓값,최솟값)구하기

주어진 구간에 해당하는 노드들의 값을 합산하여 반환한다.

```java
static long pSum(int start, int end, int node, int l, int r) {

		// (1) [ l ~ r ]이 [ start ~ end ]와 겹치지 않는 경우
		if (r < start || l > end)
			return 0;

		// (2) [ l ~ r ]이 [ start ~ end ]를 완전히 포함하는 경우
		if (l <= start && end <= r)
			// 구간 합 반환
			return tree[node];

		// (3) [ start ~ end ]가 [ l ~ r ]를 완전히 포함하는 경우
		// (4) [ l ~ r ]와 [ start ~ end ]가 겹쳐져 있는 경우
		// 자식노드로 이동하여 왼쪽 자식과 오른쪽 자식의 구간 합을 더한다.
		int mid = (start + end) / 2;
		return pSum(start, mid, node * 2, l, r) + pSum(mid + 1, end, node * 2 + 1, l, r);
}
```

![Untitled (6)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/a2328c20-9662-4867-a04c-638befc4cde2)

- 구간 합 : 모두 더한다.
- 최댓값 구하기 : 선택된 노드 중 MAX값을 선택해 출력
- 최솟값 구하기 : 선택된 노드 중 MIN값을 선택해 출력

## 최종코드

```java
package 연습;

import java.io.*;
import java.util.StringTokenizer;

public class 세그먼트트리 {

	static int n; // 배열의 크기
	static long[] tree, arr; // 세그먼트 트리와 원본 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 배열의 크기
		int m = Integer.parseInt(st.nextToken()); // 변경 연산의 개수
		int k = Integer.parseInt(st.nextToken()); // 구간 합 연산의 개수

		arr = new long[n + 1];
		tree = new long[getTreeSize()]; // 세그먼트 트리 배열 초기화
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Long.parseLong(br.readLine()); // 원본 배열 입력 (인덱스 1부터)
		}

		init(1, n, 1); // 세그먼트 트리 초기화
		while (true) {
			if (m == 0 && k == 0)
				break;

			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if (op == 1) {
				int idx = Integer.parseInt(st.nextToken()); // 변경할 위치
				long num = Long.parseLong(st.nextToken()); // 변경할 값

				long dif = num - arr[idx]; // 변경된 값과 기존 값의 차이 계산
				update(1, n, 1, idx, dif); // 세그먼트 트리 업데이트
				arr[idx] = num; // 배열 값 업데이트

				m--; // 변경 연산 횟수 감소

			} else {
				int left = Integer.parseInt(st.nextToken()); // 구간 합의 시작 인덱스
				int right = Integer.parseInt(st.nextToken()); // 구간 합의 끝 인덱스

				long sum = pSum(1, n, 1, left, right); // 구간 합 계산
				sb.append(sum + "\n");

				k--; // 구간 합 연산 횟수 감소
			}
		}

		System.out.println(sb.toString());
	}

	// 1. 트리 사이즈 구하기
	static int getTreeSize() {
		int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
		return (int) Math.pow(2, h); // 인덱스 1부터
	}

	// 2. 리프노드에 원본 데이터 입력하기
	static long init(int start, int end, int node) {

		// 리프노드에 도달했을 경우, 리프노드에 원본 데이터를 입력한다.
		if (start == end)
			return tree[node] = arr[start];

		// 리프노드가 아니라면, 자식노드로 이동하여 구간 합을 더한다.
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}

	// 3. 데이터 업데이트하기
	static void update(int start, int end, int node, int idx, long diff) {

		// 업데이트할 구간에 현재 노드가 속한다면
		if (start <= idx && idx <= end) {
			tree[node] += diff; // 현재 노드에 변경된 값만큼 더한다.
		} else
			return; // 업데이트할 구간에 현재 노드가 속하지 않으면 재귀 종료

		// 리프노드에 도달했을 경우, 재귀 종료
		if (start == end)
			return;

		// 리프노드가 아니라면, 자식노드로 이동하여 업데이트를 수행한다.
		int mid = (start + end) / 2;
		// 왼쪽 자식과 오른쪽 자식으로 나누어 업데이트를 수행한다.
		update(start, mid, node * 2, idx, diff);
		update(mid + 1, end, node * 2 + 1, idx, diff);

	}

	// 4. 구간값 구하기 : [ l ~ r ] 구간 합 구하기
	static long pSum(int start, int end, int node, int l, int r) {

		// (1) [ l ~ r ]이 [ start ~ end ]와 겹치지 않는 경우
		if (r < start || l > end)
			return 0;

		// (2) [ l ~ r ]이 [ start ~ end ]를 완전히 포함하는 경우
		if (l <= start && end <= r)
			// 구간 합 반환
			return tree[node];

		// (3) [ start ~ end ]가 [ l ~ r ]를 완전히 포함하는 경우
		// (4) [ l ~ r ]와 [ start ~ end ]가 겹쳐져 있는 경우
		// 자식노드로 이동하여 왼쪽 자식과 오른쪽 자식의 구간 합을 더한다.
		int mid = (start + end) / 2;
		return pSum(start, mid, node * 2, l, r) + pSum(mid + 1, end, node * 2 + 1, l, r);
	}

```

cf. 구간합 → 최댓값, 최솟값 구하기로 변형한 코드

```java
import java.io.*;
import java.util.StringTokenizer;

public class 세그먼트트리 {

    static int n; // 배열의 크기
    static long[] maxTree, minTree, arr; // 세그먼트 트리와 원본 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 배열의 크기
        int m = Integer.parseInt(st.nextToken()); // 변경 연산의 개수
        int k = Integer.parseInt(st.nextToken()); // 구간 최댓값 및 최솟값 연산의 개수

        arr = new long[n + 1];
        maxTree = new long[getTreeSize()]; // 최댓값 세그먼트 트리 배열 초기화
        minTree = new long[getTreeSize()]; // 최솟값 세그먼트 트리 배열 초기화
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Long.parseLong(br.readLine()); // 원본 배열 입력 (인덱스 1부터)
        }

        init(1, n, 1); // 세그먼트 트리 초기화
        while (true) {
            if (m == 0 && k == 0)
                break;

            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int idx = Integer.parseInt(st.nextToken()); // 변경할 위치
                long num = Long.parseLong(st.nextToken()); // 변경할 값

                long dif = num - arr[idx]; // 변경된 값과 기존 값의 차이 계산
                update(1, n, 1, idx, dif); // 최댓값 세그먼트 트리 업데이트
                updateMin(1, n, 1, idx, dif); // 최솟값 세그먼트 트리 업데이트
                arr[idx] = num; // 배열 값 업데이트

                m--; // 변경 연산 횟수 감소

            } else {
                int left = Integer.parseInt(st.nextToken()); // 구간 최댓값 및 최솟값의 시작 인덱스
                int right = Integer.parseInt(st.nextToken()); // 구간 최댓값 및 최솟값의 끝 인덱스

                long maxVal = getMax(1, n, 1, left, right); // 구간 최댓값 계산
                long minVal = getMin(1, n, 1, left, right); // 구간 최솟값 계산
                sb.append(maxVal + " " + minVal + "\n");

                k--; // 구간 최댓값 및 최솟값 연산 횟수 감소
            }
        }

        System.out.println(sb.toString());
    }

    // 1. 트리 사이즈 구하기
    static int getTreeSize() {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        return (int) Math.pow(2, h); // 인덱스 1부터
    }

    // 2. 리프노드에 원본 데이터 입력하기 (최댓값 세그먼트 트리)
    static long init(int start, int end, int node) {
        if (start == end)
            return maxTree[node] = arr[start];

        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
    }

    // 2-1. 리프노드에 원본 데이터 입력하기 (최솟값 세그먼트 트리)
    static long initMin(int start, int end, int node) {
        if (start == end)
            return minTree[node] = arr[start];

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
    }

    // 3. 데이터 업데이트하기 (최댓값 세그먼트 트리)
    static void update(int start, int end, int node, int idx, long diff) {
        if (start <= idx && idx <= end) {
            maxTree[node] += diff;
        } else
            return;

        if (start == end)
            return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, diff);
        update(mid + 1, end, node * 2 + 1, idx, diff);
    }

    // 3-1. 데이터 업데이트하기 (최솟값 세그먼트 트리)
    static void updateMin(int start, int end, int node, int idx, long diff) {
        if (start <= idx && idx <= end) {
            minTree[node] += diff;
        } else
            return;

        if (start == end)
            return;

        int mid = (start + end) / 2;
        updateMin(start, mid, node * 2, idx, diff);
        updateMin(mid + 1, end, node * 2 + 1, idx, diff);
    }

    // 4. 구간 최댓값 구하기
    static long getMax(int start, int end, int node, int l, int r) {
        if (r < start || l > end)
            return Long.MIN_VALUE; // 구간과 상관 없는 초기값 반환

        if (l <= start && end <= r)
            return maxTree[node];

        int mid = (start + end) / 2;
        return Math.max(getMax(start, mid, node * 2, l, r), getMax(mid + 1, end, node * 2 + 1, l, r));
    }

    // 4-1. 구간 최솟값 구하기
    static long getMin(int start, int end, int node, int l, int r) {
        if (r < start || l > end)
            return Long.MAX_VALUE; // 구간과 상관 없는 초기값 반환

        if (l <= start && end <= r)
            return minTree[node];

        int mid = (start + end) / 2;
        return Math.min(getMin(start, mid, node * 2, l, r), getMin(mid + 1, end, node * 2 + 1, l, r));
    }
}
```
