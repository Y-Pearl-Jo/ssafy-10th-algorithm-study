# 합공식

```java
public class Main2 {
	static int[] sumArr;

	public static void main(String[] args) {

		int[] arr = new int[] { 4, 5, 13, 43, 22, 1, 5, 7, 8 };
		sumArr = new int[arr.length];
		sumArr[0] = arr[0];// 구간합 첫번째 인자 셋팅

		// 구간합 구하기
		for (int i = 1; i < arr.length; i++) {
			sumArr[i] = sumArr[i - 1] + arr[i];
		}

		// 배열 3 ~ 5의 구간합은?
		int start = 3;
		int end = 5;

		// 구간합 공식 사용
		System.out.println(sumArr[end] - sumArr[start - 1]);

		// 실제 루프 사용
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += arr[i];
		}
		System.out.println(sum);

	}
}
```

# 피보나치수

<aside>
💡 1, 1, 2, 3, 5, 8, 13, 21 . . . 그 뒤의 모든 항은 바로 앞 두 항의 합인 수열이다

</aside>

```java
public class Main2 {
	public static int fibonacci(int num) {
		int result = 0;

		if (num == 1) { // 1행
			result = 1;
		} else if (num == 2) { // 2행
			result = 1;
		} else if (num >= 3) { // 3행
			result = fibonacci(1) + fibonacci(2);
		}

		return result;
	}
}
```

# 최대공약수와 최소공배수

### 유클리드 호제법

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/53c8d24c-73ff-4944-b81c-0c607dd7c026/Untitled.png)

```java
// 최대공약수 반복문 방식
	int gcd(int a, int b) {

		while (b != 0) {
			int r = a % b; // 나머지를 구해준다.

			// GCD(a, b) = GCD(b, r)이므로 변환한다.
			a = b;
			b = r;
		}
		return a;
	}

	// 최대공약수 재귀 방식
	int gcd(int a, int b) {
		if (b == 0)
			return a;
		// GCD(a, b) = GCD(b, r)이므로 (r = a % b)
		return gcd(b, a % b);
	}

	// 최소공배수 : Least Common mulitple
	int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
```

# 소수

```java
public class Main {
    public static boolean isPrime(int num){
        for(int i=2; i<num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
       System.out.println(isPrime(80));
       System.out.println(isPrime(79));
    }
}
```

```java
// 자기자신을 제외하고 절반을 초과하는 숫자에서 나눴을때 나머지가 0이되는 숫자는 나올수가 없다.

public class Main {
    public static boolean isPrime(int num){
        for(int i=2; i<=num/2; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
       System.out.println(isPrime(80));
       System.out.println(isPrime(79));
    }
}
```

```java
// 2에서부터 √N의 값까지 검색을한다며 이후의 값은 확인할 필요가 없다

public class Main {
    public static boolean isPrime(int num){
        for(int i=2; i*i<=num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
       System.out.println(isPrime(80));
       System.out.println(isPrime(79));
    }
}
```
