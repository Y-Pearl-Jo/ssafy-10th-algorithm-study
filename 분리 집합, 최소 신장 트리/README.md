
**스패닝 트리는 그래프 내의 모든 정점을 연결하지만 사이클이 없는 트리**입니다.

밑의 그래프는 예시로 만들어본 그냥 트리입니다. 정점 4개와 6개의 간선으로 이루어져있습니다.

[##_Image|kage@cZ6GvM/btsCEHvsFNQ/N3iejvMJNTpnxYe8fIbUc0/img.png|CDM|1.3|{"originWidth":1381,"originHeight":999,"style":"alignCenter","width":431,"height":312,"caption":"그냥 트리"}_##]

다음 그래프는 정점 4개와 3개의 간선으로 이루어져있습니다. **최소한의 간선의 갯수로 이루어져 있고 모든 정점이 연결된 스패닝 트리**의 형태입니다. 스패닝 트리는 다음과 같이 **간선의 갯수가 정점의 갯수 - 1 개**라는 특징이 있습니다.

[##_Image|kage@bP6Jq2/btsCKpnjo8K/TenSAt8EeCyUJDgq7zp3ik/img.png|CDM|1.3|{"originWidth":1426,"originHeight":1000,"style":"alignCenter","width":442,"height":310}_##]

**최소 스패닝 트리는 이러한 스패닝 트리 중 사용된 가중치 합이 최소인 트리**를 뜻합니다.

> **스패닝 트리**  
>  **- 최소한의 간선의 갯수로 이루어져 있고 모든 정점이 연결됨**  
>  **- 간선의 갯수 = 정점의 갯수 - 1**  
>   
> **최소 스패닝 트리**  
>  **- 스패닝 트리 중 사용된 가중치 합이 최소인 트리** 

최소 스패닝 트리를 구하는 알고리즘은 크루스칼, 프림 두가지가 있습니다.



> **개념**

프림 알고리즘은 **하나의 시작점으로 구성된 트리에 간선을 하나씩 추가**하는 형태입니다.

정점 선택을 기반으로 하며 이전단계에서 만들어진 신장트리를 확장합니다.

정점의 수에 영향을 받기에 **정점의 수가 적고 간선의 수가 많은 그래프에서 효과적**입니다.

**프림**

-   하나의 시작점으로 구성된 트리에 간선을 추가 -> 이전단계에서 만들어진 신장 트리를 확장
-   정점 선택 기반
-   정점(노드)의 수에 영향을 받기에 정점의 수가 적고 간선의 수가 많은 그래프에서 효과적

> **구현 예시**

[##_Image|kage@bIubXq/btsCAgL1B3E/a1hhxiBvCwpHG5899VTZv0/img.png|CDM|1.3|{"originWidth":1214,"originHeight":1000,"style":"alignCenter","width":613,"height":505}_##]

위키백과에서 프림을 설명하기 위한 그래프 예시를 가져왔습니다. 

먼저 임의의 점을 선택해 줍니다.

편하게 A를 시작점으로 설정하겠습니다.

A에는 A와 B를 연결하는 가중치 7인 간선과 A와 D를 연결하는 가중치 5인 간선이 있습니다.

가중치가 적은 간선을 연결합니다.

[##_Image|kage@cDjtB0/btsCKsRUifM/38YT5YmK4GByvlWu72nM81/img.png|CDM|1.3|{"originWidth":1167,"originHeight":1000,"style":"alignCenter","width":610,"height":523}_##]

연결된 정점, 간선은 빨간색으로 표시하고 사용할 수 있는 간선은 파란색으로 표시했습니다.

사용할 수 있는 간선 중 가중치가 가장 낮은 간선을 연결해줍니다.

[##_Image|kage@CUcVn/btsCzS5C6XQ/Zx0VxS8FrzSihKj712xpnK/img.png|CDM|1.3|{"originWidth":1160,"originHeight":1000,"style":"alignCenter","width":643,"height":554}_##]

A, D, F가 연결되었습니다.

다시 파란 간선중 가중치가 가장 낮은 간선을 연결합니다.

[##_Image|kage@cqmHgm/btsCzReBvJw/Unn6uCQapsskonQtiahCpk/img.png|CDM|1.3|{"originWidth":1096,"originHeight":999,"style":"alignCenter","width":622,"height":567}_##]

A, B, D, F가 연결되었습니다.

또한 D와 B를 연결하던 가중치 9인 간선은 의미가 없어지므로 배제합니다.

위의 과정들을 반복하면 다음과 같은 최소 스패닝 트리가 완성됩니다.

[##_Image|kage@bvEPWh/btsCCx7LX3f/SMBiOtSJ7iyaQIhXyZTgU1/img.png|CDM|1.3|{"originWidth":1087,"originHeight":1000,"style":"alignCenter","width":599,"height":551}_##]

과정을 요약해보겠습니다.

> 1\. 임의의 정점을 선택  
> 2\. 가중치가 가장 낮은 간선 연결  
> 3\. 사용할 수 있는 간선 중 연결된 정점 제외하고 가중치가 가장 낮은 간선 연결  
> 4\. 모든 정점이 연결될 때까지 반복

