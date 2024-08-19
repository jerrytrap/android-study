## Keywords
- **TextView**: 텍스트를 표시할 수 있는 뷰이다. 텍스트는 String Resource로 참조하는 것이 권장된다.
- **Button**: 클릭하여 작업을 수행할 수 있는 뷰이다. 텍스트뷰를 상속받는다.
- **EditText**: 텍스트를 입력하고 수정하기 위해 사용하는 뷰이다. inputType, hint 등을 설정할 수 있고, 텍스트가 변경되었을 때 작업을 수행할 Listener를 지정할 수 있다.
- **ImageView**: 이미지를 표시할 수 있는 뷰이다. 이미지 색상을 지정하거나 크기를 조절할 수 있다.
- **ImageButton**: 텍스트 대신 이미지를 표시할 수 있는 버튼이다. selector 드로어블을 사용하면 눌렸을 때와 눌리지 않았을 때 각각 다른 이미지를 지정할 수 있다.
- **Drawable**: 화면에 시각적 콘텐츠를 그리는 데 사용되는 객체를 정의하는 추상 클래스이다. 종류에는 bitmap, shape, scale, layer-list, state-list 등이 있다.
- **Event**: 사용자와 애플리케이션 간의 상호작용을 처리하기 위해 발생하는 특정 동작이나 상태 변화를 의미한다.
  - Touch Event: 화면이 터치되었을 때 발생하는 이벤트이다.
  - Click Event: UI 요소가 클릭되었을 때 발생하는 이벤트이다.
  - Key Event: 키(키보드, 디바이스 버튼 등)가 눌렸을 때 발생하는 이벤트이다.
  - Gesture Event: 제스처 동작이 발생했을 때 발생하는 이벤트이다. (드래그, 스와이프, 스크롤 등)
  - Focus Event: UI 요소가 포커스를 받을 때 발생하는 이벤트이다. (예를 들면, 텍스트 상자를 선택하면 입력할 수 있는 상태가 된다. 즉, 사용자와 상호작용 할 수 있는 상태를 의미한다.)
  - **Conguration Changes**: 시스템 환경의 변화로 인해 UI나 동작이 달라지는 상황을 의미한다. Activity가 파괴 후 재생성되기 때문에, 작성하고 있던 텍스트 등 데이터가 사라질 수 있으므로, 적절히 처리해주어야한다. Activity를 재생성하지 않고 수동으로 처리해줄 수도 있다.
    다음과 같은 상황에서 configuration change가 발생한다.
    - 화면 회전
    - 화면 크기, 해상도 변경
    - 언어 및 지역 변경
    - 키보드 상태 변경 (하드/소프트)
    - 다크 모드 변경
- **Toast** - 간단한 메시지를 잠깐 보여주는 뷰이다. 모양이나 위치를 바꿀 수도 있다.
- **Snackbar** - 사용자에게 메시지를 보여주며, 추가 작업을 수행할 수 있는 버튼을 제공할 수도 있다. 또한 사용자가 직접 닫을 수 있다.
- **Dialog** - 사용자와 상호작용하기 위한 팝업 창이다. 사용자에게 메시지를 보여주고 입력을 받을 수 있다.

## Questions
Q. 왜 String Resource를 사용해야 할까?  
A. 다음과 같은 이유가 있다.
- 언어별로 관리함으로써 다국어 지원이 가능하다.
- 유지보수에 용이하다. (예를 들면, 여러 번 사용하는 문자열을 한 번에 수정 가능하다.)

---

Q. ImageView에 클릭 리스너를 설정하는 것과 ImageButton랑 같은거 아닌가? ImageView와 ImageButton은 어떤 차이가 있을까?  
A. ImageButton은 기본 background가 제공된다. 또한 Clickable로 이미 설정되어 있고, 선택/비선택 등 상태를 기본적으로 제공한다.  

---

Q. 이벤트는 어떻게 전파될까?  
A. 전파되는 순서는 다음 그림과 같다.  
<img width="500" src="https://github.com/user-attachments/assets/cd8e14b6-0f2d-41f1-a06a-9875336251d8">   
실제로 로그를 찍어봤고 그 결과는 다음과 같다.   
<img width="300" src="https://github.com/user-attachments/assets/5a391269-744e-4f06-ad07-6c33eb8ac2f3">   
dispatchTouchEvent, onInterceptTouchEvent, onTouchEvent 함수의 반환값은 Boolean인데, true를 반환하면 이벤트를 전파하지 않고 소비한다. false라면 계속 전파한다.   

---

Q. onTouchEvent 함수를 오버라이드 하면 performClick 함수를 오버라이드 하지 않았다는 경고가 뜬다. 이유가 뭘까?     
A. performClick은 클릭 이벤트를 발생시키는 함수이다. 다음과 같은 이유가 있다.
- 접근성 도구가 클릭 가능 여부와 클릭시 동작을 제대로 인식하게 하기 위해서이다.
- UI 테스트 시 performClick 함수로 클릭 동작을 시뮬레이션 하기 때문이다.    

(참고) [[SampleCustomView]](https://github.com/jerrytrap/android-study/blob/main/ch2_widget_drawable/SampleCustomView.kt) 클래스처럼 구현할 수 있다.  

---

Q. background, backgroundTint, backgroundTintMode 속성의 차이는 뭘까?   
A.
- background: 배경으로 사용할 drawable을 지정한다. 색상만 지정할 수도 있다.
- backgroundTint: 배경에 적용한 색상을 지정한다.
- backgroundTintMode: 배경 색상을 어떻게 적용할지 지정한다. 기본 값은 src_over 속성이며, 배경 위에 색조가 덮이는 방식이다.   

---

Q. Button에 background가 제대로 적용되지 않는 이유가 무엇일까?   
A. 호환성 때문에 제대로 적용되지 않을 수 있다고 한다. 따라서 AppCompatButton 사용을 권장한다.