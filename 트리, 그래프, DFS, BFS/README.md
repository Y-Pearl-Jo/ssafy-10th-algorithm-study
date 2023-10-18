## 트라이란?

- **문자열을 저장하고 효율적으로 탐색**하기 위한 트리 형태의 정렬된 자료구조
- Digital Tree, Radix Tree, Prefix Tree
- 텍스트 자동 완성 기능과 같이 문자열을 저장하고 탐색하는데 유용
- 루트 노드는 **특정 문자를 의미하지 않고 자식 노드만 가짐**
    - 루트노드에 값이 있으려면 트리가 여러개 되는데
    - 결국 루트노드를 비우는 것과 다름 없는 구조가 됨
- **자식 노드를 맵<Key k, Value v> 형태**로 가짐

## 이진 탐색과는 다른점

- 기존 이진 탐색의 경우 노드를 방문 후에 문자열을 확인하는 과정이 필요함
- **노드의 개수 N, 문자열의 길이 M**이라 할 때,
    - 노드를 **방문하는데 필요한 시간복잡도 O(log N)**
    - 문자열이 **일치하는지 확인하는데 O(M)**의 시간복잡도를 가짐
    - **총합 O(Mlog N)의 시간복잡도**
- **Trie**의 경우 노드에 문자를 저장함으로써 **시간복잡도를 O(M)**으로 줄일 수 있음

## 트라이 구현

1. 구현을 위해 **Trie**와 이를 구성할 **TrieNode 클래스** 각각 필요
- **TrieNode**
    - **자식 노드인 맵과 현재 노드가 마지막 글자인지 여부**를 포함
        - **마지막 글자 여부를 사용하는 이유는 DEV를 저장해뒀을 때, DE가 true가 되지 않기 위함**
    - **private으로 설정하기** 때문에 **Getter / Setter 필요**
        - **자식노드는 Trie 차원에서 생성해서 넣을 것이기 때문에 G 만 생성**
        - **마지막 글자 여부**는 노드 삭제 과정에서 필요하기 때문에 **G / S 모두 생성**
    - **TrieNode 기본 구현 코드**
        
        ```java
        public class TrieNode {
        		// 자식 노드 맵
        		private Map<Character, TrieNode> childNodes = new HashMap<>();
        		// 마지막 글자인지 여부
        		private boolean isLastChar;
        
        		Map<Character, TrieNode> getChildNodes() {
        			return this.childNodes;
        		}
        
        		boolean getIsLastChar() {
        			return this.isLastChar;
        		}
        
        		void setIsLastChar(boolean isLastChar) {
        			this.isLastChar = isLastChar;
        		}
        	}
        ```
        
- **Trie**
    - 생성자를 통해 빈 문자열을 가지는 루트노드 생성
    - 이후 insert 메서드를 통해 자식 노드 생성
    - **Trie 기본 구현 코드**
        
        ```java
        public class Trie {
        	// 루트 노드
        	private Trienode rootNode;
        
        	Trie() {
        		rootNode = new TrieNode();
        	}
        }
        ```
        
    - **저장(Insert) / 확인(Contains) / 삭제(Delete) 세가지 메서드 구현**
    - **저장(Insert)**
        - 입력받은 문자열을 **문자로 나누어 계층구조의 자식노드로 만들어 넣음**
        - 이때, **이미 있는 부분(공통 접두어 부분) 까지는 생성하지 않음**
    - **저장(Insert) 구현 코드**
        
        ```java
        void insert(String word) {
        	// 처음 시작은 루트
        	TrieNode thisNode = this.rootNode;
        	// 문자열의 길이 만큼 순회하며 문자 확인
        	for (int i = 0; i < word.length(); i++){
        		// computeIfAbsent()
        		// Key값에 해당하는 Value 존재 여부에 따라 새로 대입
        		thisNode = thisNode.getChildNodes().computeIfAbsent(
        																				word.charAt(i), c -> new TrieNode());
        	}
        	// 문자열을 넣고 마지막 문자의 노드 isLastChar를 ture
        	thisNode.setIsLastChar(true);
        }
        ```
        
    - **확인(Contains)**
        - **루트노드 부터 순서대로 알파벳이 일치하는 자식노드들이 존재**
        - **마지막 글자에 해당하는 노드의 isLastChar가 true**
    - **확인(Contains) 구현 코드**
        
        ```java
        boolean contains(String word) {
        	TrieNode thisNode = this.rootNode;
        
        	for (int i = 0; i < word.length(); i++) {
        		char character = word.charAt(i);
        		TrieNode node = thisNode.getChildNodes().get(character);
        		// 탐색 도중 끊긴 경우
        		if (node == null)
        			return false;
        		
        		thisNode = node;
        	}
        	// 결국 최종 노드에서 isLastChar를 확인하면 포함여부 확인 가능
        	return thisNode.getIsLastChar();
        }
        ```
        
    - **삭제(Delete)**
        - 주어진 단어를 찾아 하위 노드로 단어 길이만큼 내려감
        - **노드들이 부모노드의 정보를 가지고 있지 않음**
            - 하위 노드로 내려가며 삭제 대상 단어를 탐색 후 올라오면서 삭제
        - **자식 노드가 없어야 함**
        - **삭제하는 첫 노드는 isLastChar == true**이어야 함
        - **삭제 진행 과정 중에는 isLastChar == false**
            - 위 값이 **true이면 다른 단어와 공유 중**이므로 그때부터는 삭제 되면 안됨
    - **삭제(Delete) 구현 코드**
        
        ```java
        void delete(String word) {
        	delete(this.rootNode, word, 0)
        }
        
        private void delete(TrieNode thisNode, String word, int  idx) {
        	char character = word.charAt(idx);
        
        	// 아예 존재하지 않는 단어인 경우
        	if(!thisNode.getChildNodes().containsKey(character))
        		throw new Error("There is no [" + word + "] in this Trie.");
        	
        	TrieNode childNode = thisNode.getChildNodes().get(charcter);
        	idx++;
        
        	if(idx == word.length()) {
        		// 삭제하는 첫 노드는 마지막 단어이어야 함
        		if(!childNode.getIsLastChar())
        			throw new Error("There is no [" + word + "] in this Trie.");
        	
        		childNode.setIsLastChar(false);
        
        		// 자식 노드가 없어야 함
        		if(childNode.getChildNodes().isEmpty))
        			thisNode.getChildNodes().remove(charcter);
        
        	} else {
        		// idx가 문자열 길이가 될때까지 재귀호출 후 나오면서 노드 제거
        		delete(childNode, word, idx);
        		
        		// 자식 노드가 없고, 어떤 단어의 마지막 단어가 아니어야 함
        		if(!childNode.getIsLastChar() && childNode.getChildNodes().isEmpty())
        			thisNode.getChildNodes().remove(character);
        	}
        }
        ```
        
    - **Trie 종합 구현 코드**
        
        ```java
        public class Trie {
        
        	private TrieNode rootNode;
        
        	Trie() {
        		rootNode = new TrieNode();
        	}
        	// ----저장----
        	void insert(String word) {
        		TrieNode thisNode = this.rootNode;
        
        		for (int i = 0; i < word.length(); i++) {
        			thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        		}	
        		thisNode.setIsLastChar(true);
        	}
        	// ----확인----
        	boolean contains(String word) {
        		TrieNode thisNode = this.rootNode;
        
        		for (int i = 0; i < word.length(); i++) {
        			char character = word.charAt(i);
        			TrieNode node = thisNode.getChildNodes().get(character);
        
        			if (node == null)
        				return false;
        
        			thisNode = node;
        		}
        
        		return thisNode.getIsLastChar();
        	}
        	// ----삭제----
        	void delete(String word) {
        		delete(this.rootNode, word, 0);
        	}
        	
        	private void delete(TrieNode thisNode, String word, int index) {
        		
        		char character = word.charAt(index);
        
        		if(!thisNode.getChildNodes().containsKey(character))
        			throw new Error("There is no [" + word + "] in this Trie.");
        
        		TrieNode childNode = thisNode.getChildNodes().get(character);
        		index++;
        
        		if(index == word.length()) {
        			if (!childNode.getIsLastChar()) 
        				throw new Error("There is no [" + word + "] in this Trie.");
        
        			childNode.setIsLastChar(false);
         
        			if (childNode.getChildNodes().isEmpty())
        				thisNode.getChildNodes().remove(character);
        		}else {
        			delete(childNode, word, index);
        			
        			if(!childNode.getIsLastChar() && childNode.getChildNodes().isEmpty())
        				thisNode.getChildNodes().remove(character);
        		}
        	}
        }
        ```
