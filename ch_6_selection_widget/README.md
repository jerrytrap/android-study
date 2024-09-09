## Keywords
- **Custom View**: 뷰 클래스를 확장해서 만든 사용자 정의 뷰이다.
  - 주요 생명주기 함수는 다음과 같다.
    - onAttachToWindow: 뷰가 윈도우에 추가될 때 호출된다.
    - onMeasure: 뷰와 자식 뷰의 크기를 측정할 때 호출된다.
    - onLayout: 뷰의 각 자식 뷰의 크기와 위치를 할당할 떄 호출된다.
    - onDraw: 화면에 뷰가 렌더링될 때 호출된다.
  - invalidate: 전체 뷰를 다시 그리기 위해 사용한다. onDraw 함수가 다시 호출된다.
  - requestLayout: 뷰의 크기나 위치가 변경되었을 때 레이아웃을 다시 계산하기 위해 호출한다. onMeasure -> onLayout 순으로 호출된다.
- **Selection Widget**(선택 위젯): 여러 개의 아이템 중에 하나를 선택할 수 있는 리스트 모양의 위젯이다. 어댑터 패턴을 사용한다. 
- **RecyclerView**: 데이터를 리스트 형태로 보여줄 수 있는 위젯이다. ListView와는 다르게 뷰를 재사용한다는 특징이 있다.
- **Adapter**: 각 아이템을 표시할 뷰와 데이터를 연결해주기 위해 사용한다.
- **ViewHolder**: 아이템을 표시하기 위한 뷰를 매번 새로 생성하지 않고 재사용하기 위해 사용한다.
- **Spinner**: 목록 중에서 하나를 선택할 수 있는 위젯이다.

## Questions
Q. ListView와 RecyclerView의 차이가 뭘까?   
A. 
- RecyclerView만 뷰를 재활용할 것같지만, 사실 ListView도 뷰를 재활용한다. ListView Adapter에 있는 getView함수의 convertView 매개변수를 통해 뷰를 재사용할 수 있다.
- RecyclerView에서는 ViewHolder를 필수적으로 사용해야 하고, ListView에서는 선택이다. ViewHolder를 사용하는 이유는 뷰를 매번 findViewById로 참조하면 성능에 좋지 않기 때문이며, 
이를 ViewHolder에서 뷰의 참조를 가지고 있고 재사용할 때는 데이터만 바꿈으로써 해결할 수 있다.
- RecyclerView에서는 LayoutManager로 세로, 가로, 그리드 등 여러 형태로 목록을 표시할 수 있고, ItemDecoration이나 애니메이션도 지정할 수 있다.
- RecyclerView에서는 DiffUtil을 사용해서 아이템을 효율적으로 업데이트할 수 있어 성능에 이점이 있다.

Q. DiffUtil과 ListAdapter는 뭘까?   
A. 
- DiffUtil: 데이터가 변경되었을 때, 변경된 항목만을 효율적으로 업데이트하기 위해 사용하는 클래스이다.   
areItemsTheSame이 true이고, areContentsTheSame이 false이면(즉, 같은 항목인데 데이터가 다르다면) 항목이 업데이트된다.   
반면 areItemsTheSame이 false면 항목이 삭제된 것으로 보고 새로 항목을 추가한다.
- ListAdapter: DiffUtil을 활용하여 리스트를 업데이트하는 기능이 추가된 Adapter이다. 내부적으로 AsyncListDiffer를 사용하는데, 
AsyncListDiffer는 백그라운드 스레드에서 비동기로 데이터의 변경사항을 확인한다.