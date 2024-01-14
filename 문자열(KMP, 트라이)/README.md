일일강사 : 정혜진

# KMP와 트라이

문자열을 빠르게 탐색하는 방법들

# KMP(Knuth-Morris-Pratt)

## KMP 알고리즘을 들어가기 전… 사전개념

### 접두사(Prefix) 개념

<banana의 접두사>

b

ba

ban

bana

banan

banana

→ 이 6개가 banana의 접두사

### 접미사(Suffix) 개념

<banana의 접미사>

a

na

ana

nana

anana

banana

→ 이 6개가 banana의 접미사

### pi[i] 배열 개념

pi[i] 배열 : 주어진 문자열의 0~i 까지의 부분 문자열 중에서 prefix == suffix 가 될 수 있는 부분 문자열 중에서 가장 긴 것의 길이

(이때 prefix가 0~i까지의 부분 문자열과 같으면 안된다.)

### (예시) 문자열 “ABAABAB”의 pi배열

![Untitled](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/3d5188fe-d62e-48e3-be1d-74b0a2e4ee49)

### (예시) 문자열 “AABAA”의 pi배열

![Untitled (1)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/26744236-cd4a-48bc-8e74-d8fcda18cbb2)

## KMP 알고리즘의 원리

### **(예시) 텍스트 “ABCDABCDABEE” 에서 패턴 “ABCDABE”를 찾는 상황**

**첫 번째 시도** : 패턴의 0~5 부분 문자열은 텍스트와 일치했지만 6번째 인덱스의 E가 텍스트와 불일치

![Untitled (2)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/3205975b-3916-4250-831e-6e144f9f1ff1)

**첫 번째 시도에서 주목해야할 사실** : 아래의 박스 부분은 일치한다 → KMP알고리즘의 핵심

![Untitled (3)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/d7cbde9e-1b4c-414b-b54d-7788627fc483)

**접두사 AB와 접미사 AB가 일치** 

(접두사와 접미사가 일치하는 최대 길이 ⇒ 패턴 “ABCDABE”의 pi[5] = 2 )

![Untitled (4)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/bae21d69-445e-47b0-bd5f-ef6679a1d819)

**따라서 다음 단계로 껑충 건너뛸 수 있음**(i는 텍스트의 현재 비교 위치, j는 패턴의 현재 비교 위치를 나타냄)

![Untitled (5)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/d008394e-2c62-4283-8381-b6880b32eb32)

**빠진 중간 단계들을 살펴보자.**

- 중간단계 1

![Untitled (6)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/0a21eb28-70a3-4b37-b97f-beb0cca8e80f)

- 중간 단계 2

![Untitled (7)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/32e5180c-b265-4ef6-8114-96e7a3d1cd2d)

- 중간 단계 3

![Untitled (8)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/4e935065-3a2f-4d09-a228-d59a0d2ecdce)

- 중간 단계 4
    
    현재 비교 위치도 인덱스 4에서 시작할 필요가 없음
    
    p[5] = 2 라는 정보는 접미사 AB가 접두사 AB와 같다는 것을 말함. 따라서 우리는 패턴”ABCDABE”에서 “AB”는 이미 텍스트와 일치한다는 사실을 알고 있음. 
    
    따라서 현재 비교위치를 인덱스 4번이 아닌 4+2 = 6번에서 시작할 것임
    

![Untitled (9)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/68f44848-1e2d-487c-8be9-921efc6a8081)

**KMP로 건너뛴 최종단계!**

![Untitled (10)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/89f0c4ac-d4af-486a-af7d-b9c5bbf98062)

<aside>
🌟 KMP 알고리즘은 틀렸다는 사실이 아니라 조금이라도 일치했던 정보에 주목한다.

조금이라도 일치했던 정보들을 pi배열을 통해 전처리 해두고, 많은 중간 시도를 건너뛸 수 있게 한다.

</aside>

## KMP의 구현

두 파트로 나누어 구현한다.

- getPi 함수
    - 접두사와 접미사가 일치하는 최대 길이(pi배열)를 계산
- kmp 함수
    - pi배열을 이용해 문자열 검색

### (예시) 백준 1786 찾기

```jsx
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KMPExample {
    private static ArrayList<Integer> pi; // pi 배열
    private static String s; // 문자열 텍스트
    private static String p; // 패턴

    private static int n; // 문자열 길이
    private static int m; // 패턴 길이

    // getPi 함수 : 주어진 패턴에 대한 pi배열을 계산
    private static void getPi() {
        pi = new ArrayList<>(m);

        // 초기화
        for (int i = 0; i < m; i++) {
            pi.add(0);
        }

        int j = 0; // 일치하는 접두사의 길이
        // 패턴 순회하면서 접두사 == 접미사 최대길이 찾음
        for (int i = 1; i < m; i++) { // 1부터 시작
            while (j > 0 && p.charAt(i) != p.charAt(j)) { // 현재 위치의 문자와 j의 위치의 문자가 일치하지 않는 경우
                j = pi.get(j - 1); // pi배열에서 이전위치(j-1)를 가져와 j 업데이트
            }
            if (p.charAt(i) == p.charAt(j)) { // 현재 위치의 문자가 j의 위치 문자와 일치하는 경우
                j++; // 일치하는 접두사의 길이 1 증가
                pi.set(i, j); // pi 배열 업데이트 (각 위치 i마다 계산된 j값을 pi배열에 저장)
            }
        }
    }

    // kmp 함수 : 주어진 문자열 s에서 패턴 p를 찾고 인덱스를 반환
    public static ArrayList<Integer> kmp() {
        getPi(); // pi배열 계산
        ArrayList<Integer> ans = new ArrayList<>();

        int j = 0;
        // 문자열 순회하면서 패턴 일치하는지 확인
        for (int i = 0; i < n; i++) {
            // 현재 문자열의 문자와 패턴의 문자가 불일치한다면
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = pi.get(j - 1); // pi배열을 사용해서 다음 비교 위치로 건너뛴다
            }
            if (s.charAt(i) == p.charAt(j)) {
                if (j == m - 1) { // 패턴 끝에 도달한 경우 = 완전히 일치하는 부분을 찾은 경우
                    ans.add(i - m + 1); // 일치하는 부분의 시작 인덱스를 저장
                    j = pi.get(j); // 다음 일치하는 부분을 찾기 위해 j 업데이트
                } else { // 아직 패턴의 끝에 도달하지 않은 경우
                    j++; // 패턴의 다음 문자로 넘어감
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        p = br.readLine();

        n = s.length();
        m = p.length();

        ArrayList<Integer> matched = kmp(); // 일치하는 위치들이 저장되어있는 배열

        System.out.println(matched.size());
        for (int i : matched) {
            System.out.print((i + 1) + " ");
        }

        br.close();
    }
}
```

# 트라이(Trie)

트라이 : 문자열을 저장하고 효율적으로 탐색하기 위한 트리형태의 자료구조

## 트라이의 원리

![Untitled (11)](https://github.com/soberdam/ssafy-10th-algorithm-study/assets/140683237/9091ce34-a591-443c-9f6c-e1e9c5b69a2d)

**트리의 루트에서부터 자식들을 따라가면서 생성된 문자열들이 트라이 자료구조에 저장되어 있음.**

**저장된 단어는 끝을 표시하는 변수를 추가하여 단어의 끝을 구분함.**

DFS를 해보면 to, tea, ted, ten, A, i, in, inn 이라는 단어들이 자료구조에 들어가 있음을 알 수 있음.

장점 : 문자열 빠르게 탐색 가능

단점 : 저장공간 크기가 큼

## 트라이의 구현

- TrieNode 클래스
- insert 함수 : 주어진 단어를 트라이에 삽입
    - 각 문자를 순회하면서 해당 문자에 대한 자식 노드를 만들고
    - 마지막 문자에 도달하면 isEndOfWord를 true로 설정함.
- search 함수 : 주어진 단어가 트라이에 존재하는지 검색
    - 각 문자에 대해 해당 자식 노드가 존재하는지 확인하고
    - 마지막 문자에서 isEndOfWord가 true인지 확인 (둘 다 마지막 문자인지 확인)

### (예시) hello, world를 트리구조에 저장하고 hello, world, hellohj이 존재하는지 확인하고 싶은 상황

```jsx
class TrieNode {
    private TrieNode[] children;
    private boolean isEndOfWord;

    // TrieNode 생성자
    public TrieNode() {
        children = new TrieNode[26]; // 알파벳 a-z에 대한 자식 노드
        isEndOfWord = false;
    }

    // 단어 삽입
    public void insert(String word) {
        TrieNode current = this; // current를 루트노드(this)로 초기화
        for (char ch : word.toCharArray()) { // 단어의 각 문자를 순회
            int index = ch - 'a'; //문자를 인덱스로 변환
            if (current.children[index] == null) { // 해당 자식노드가 트리에 없으면 자식노드를 새로 만듦
                current.children[index] = new TrieNode();
            }
            current = current.children[index]; // 자식노드 인덱스로 current를 이동
        }
        current.isEndOfWord = true; // 마지막 문자까지 이동하면 true로 설정해서 단어의 끝임을 표시
    }

    // 단어 검색
    public boolean search(String word) {
        TrieNode current = this;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) { // 해당문자가 트리에 없는 문자면 단어가 트라이에 없다는 뜻임 false
                return false;
            }
            current = current.children[index]; // 자식노드 인덱스로 current를 이동
        }
        return current.isEndOfWord; // 검색하고 싶은 단어의 isEndOfWord를 반환 (단어의 마지막까지 도달했는지 확인)
    }
}

public class SimpleTrie {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        root.insert("hello");
        root.insert("world");

        System.out.println(root.search("hello")); // true
        System.out.println(root.search("world")); // true
        System.out.println(root.search("hellohj"));  // false
    }
}
```
