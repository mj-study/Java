`Object`의 기본 `toString` 메서드에서는 우리가 원하는 정보를 보통은 반환하지 않는다.
⇒ `클래스명@16진수로표시한해시코드` 로 반환함

### `toString` 왜 재정의할까?

- 디버깅과 로깅시 편의
    - 디버깅과 로깅시 객체의 상태를 쉽게 확인할 수 있음
    - 의미 있는 정보를 제공하여 문제 해결을 도움
- 가독성 향상
    - 객체의 중요한 정보를 쉽게 확인할 수 있음
    - 코드 리뷰나 유지 보수시 이해하기 쉬운 정보를 제공
- 문서화
    - `toString`의 메서드 출력값이 객체의 주요 속성들을 포함하면 클래스의 사용 방법을 이해하는데 도움이 됨

### `toString` 구현시 주의사항

- `toString`은 사람이 읽기 쉬운 형태의 정보를 반환해야함
- 재정의하지 않았다면 쓸모 없는 메시지만 로그에 남을 것임
- `toString`은 그 객체가 가진 주요 정보를 모두 반환하는게 좋음
    - 하지만 객체가 크거나 상태를 문자열로 표현하기 적합하지 않다면 무리가 있음
      ⇒ 이런 상황이라면 그 객체를 설명할 수 있는 문자열 같은 정보를 요약해서 담아야함
- 반환값의 포맷을 문서화 할지 정해야 함
    - 전화번호나 행렬 같은 값 클래스라면 문서화하기를 권장
      ⇒ 포맷을 명시하면 그 객체는 표준적이고, 명확하고, 사람이 읽을 수 있게 됨
    - 포맷을 명시하기로 했다면 명시한 포맷에 맞는 문자열과 객체를 **상호 전환할 수 있는 정적 팩토리나 생성자**를 함께 제공해주면 좋다
    - 단점도 있는데, 포맷을 명시하면 그 포맷에 계속 얽매이게 됨
      ⇒ 향후 코드를 변경한다하면 그 코드를 종속해서 사용하던 코드들도 다 바꿔야함

### `toString` 메서드 작성 원칙

- 의미 있는 정보 포함
    - 객체의 중요한 필드 값을 포함하여 객체의 상태를 명확하게 나타냄
- 형식 명시
    - 반환되는 문자열 형식을 명확히 정의하여 일관된 형식을 유지함
- 자동 생성 도구 사용
    - IDE나 라이브러리등을 활용하여 `toString`을 생성
    - 도구를 사용하면 `Object`에서 기본 제공하는 `toString`보다는 효용성있는 값 사용 가능
- 누락된 정보 방지
    - 중요한 필드가 `toString`에서 빠지지 않게 유의
    - 필요시 예외 상황을 처리하여 `null` 값 등을 명확히 표시
- 보안 고려
    - 민감한 정보 (비밀번호 등) 포함하지 않도록 주의
    - 필요시 필드를 가리거나 제한된 정보를 포함