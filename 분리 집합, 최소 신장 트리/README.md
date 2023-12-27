일일강사 : 오건영

스패닝 트리는 그래프 내의 모든 정점을 연결하지만 사이클이 없는 트리입니다.

밑의 그래프는 예시로 만들어본 그냥 트리입니다. 정점 4개와 6개의 간선으로 이루어져있습니다.
<img width="328" alt="image" src="https://github.com/soberdam/ssafy-10th-algorithm-study/assets/118809296/e8cf117f-0bb5-4848-acb5-742a7245588d">

 

다음 그래프는 정점 4개와 3개의 간선으로 이루어져있습니다. 최소한의 간선의 갯수로 이루어져 있고 모든 정점이 연결된 스패닝 트리의 형태입니다. 스패닝 트리는 다음과 같이 간선의 갯수가 정점의 갯수 - 1 개라는 특징이 있습니다.
<img width="425" alt="image" src="https://github.com/soberdam/ssafy-10th-algorithm-study/assets/118809296/4d466f2a-1c41-4fc8-a76b-672c9719b953">



 

최소 스패닝 트리는 이러한 스패닝 트리 중 사용된 가중치 합이 최소인 트리를 뜻합니다.

 

스패닝 트리
 - 최소한의 간선의 갯수로 이루어져 있고 모든 정점이 연결됨
 - 간선의 갯수 = 정점의 갯수 - 1

최소 스패닝 트리
 - 스패닝 트리 중 사용된 가중치 합이 최소인 트리 
 

최소 스패닝 트리를 구하는 알고리즘은 크루스칼, 프림 두가지가 있습니다.


 

개념
 

프림 알고리즘은 하나의 시작점으로 구성된 트리에 간선을 하나씩 추가하는 형태입니다.

정점 선택을 기반으로 하며 이전단계에서 만들어진 신장트리를 확장합니다.

정점의 수에 영향을 받기에 정점의 수가 적고 간선의 수가 많은 그래프에서 효과적입니다.

 

프림

하나의 시작점으로 구성된 트리에 간선을 추가 -> 이전단계에서 만들어진 신장 트리를 확장
정점 선택 기반
정점(노드)의 수에 영향을 받기에 정점의 수가 적고 간선의 수가 많은 그래프에서 효과적
 

구현 예시
 <img width="541" alt="image" src="https://github.com/soberdam/ssafy-10th-algorithm-study/assets/118809296/5bacb1bd-b364-4556-a810-7454fd9ce7b5">


위키백과에서 프림을 설명하기 위한 그래프 예시를 가져왔습니다. 

먼저 임의의 점을 선택해 줍니다.

편하게 A를 시작점으로 설정하겠습니다.

A에는 A와 B를 연결하는 가중치 7인 간선과 A와 D를 연결하는 가중치 5인 간선이 있습니다.

가중치가 적은 간선을 연결합니다.
<img width="534" alt="image" src="https://github.com/soberdam/ssafy-10th-algorithm-study/assets/118809296/8ad9dc58-8dcc-41c3-98b1-af9faa7144fe">


연결된 정점, 간선은 빨간색으로 표시하고 사용할 수 있는 간선은 파란색으로 표시했습니다.

사용할 수 있는 간선 중 가중치가 가장 낮은 간선을 연결해줍니다.
<img width="588" alt="image" src="https://github.com/soberdam/ssafy-10th-algorithm-study/assets/118809296/3bdef618-2085-448d-9b6f-cc7d11d891e8">


 

A, D, F가 연결되었습니다.

다시 파란 간선중 가중치가 가장 낮은 간선을 연결합니다.
<img width="574" alt="image" src="https://github.com/soberdam/ssafy-10th-algorithm-study/assets/118809296/c069f725-277e-4293-b017-2bd7c26b0cbf">

 


A, B, D, F가 연결되었습니다.

또한 D와 B를 연결하던 가중치 9인 간선은 의미가 없어지므로 배제합니다.

위의 과정들을 반복하면 다음과 같은 최소 스패닝 트리가 완성됩니다.
<img width="559" alt="image" src="https://github.com/soberdam/ssafy-10th-algorithm-study/assets/118809296/a2c92bc1-0356-44d3-9e72-31ce6c6dbf92">

 


 

과정을 요약해보겠습니다.

1. 임의의 정점을 선택
2. 가중치가 가장 낮은 간선 연결
3. 사용할 수 있는 간선 중 연결된 정점 제외하고 가중치가 가장 낮은 간선 연결
4. 모든 정점이 연결될 때까지 반복
