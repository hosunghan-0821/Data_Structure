# Data_Structure

### About Repo
Java Collections 의 자료구조를 직접 구현해봄으로써, 각 자료구조의 특징을 이해하기 위한 Repository입니다. 


#### ArrayList
- ArrayList는 Object[] 배열을 두고 사용한다. 
- 모든 자료구조는 동적할당을 전제로 한다.
- 데이터 사이에 빈 공간읋 허락하지 않는다. (가운데 삽입,삭제시 연산이 추가적으로 필요-> 성능저하)
- 시간복잡도를 작성해보면, index 위치를 지정해서 추가,삭제, 검색할 때, O(N)을 갖는다. 이외에는 O(1)이다


#### LinkedList

- 노드라는 객체를 활용하여 연결한다.
- ArrayList는 Object[]을 활용하여 데이터를 담아두지만, S-LinkedList는 Node의 각 주소를 연결하여 chain처럼 활용한다.
- 위와 같은 특징으로, 데이터 삽입,삭제에 유리하지만, 검색에는 불리하다.
- 메모리에 적재되는 것을 생각했을 때, ArrayList 는 연속적으로 저장되고 LinkedList는 메모리가 따로따로 적재되어서 한 노드가 다른 노드를 가리키면서 연결되는 형태이다.

