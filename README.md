# hello-spring 
#### 이 repository는 <김영한의 스프링 완전 정복> 강의를 들으며 공부한 내용을 정리하는 공간입니다.


* 9/9 ~ 9/10 **회원 관리 예제**
```
1. 람다와 스트림 (feat. 함수형 인터페이스)
2. static 변수와 static 함수
3. new ArrayList<>() vs Arrays.asList()
```

* 9/14 **회원 레포지토리 테스트 케이스 작성**
```
JUnit 단위 테스트 도구 사용
```

* 10/3 **회원 서비스 로직 작성 & 테스트 케이스 작성**
```
1. JUnit 단위 테스트 도구 사용
2. 회원가입 테스트 케이스 작성
3. 회원가입시 예외 발생 테스트 케이스 작성
4. service단에서의 repository 의존성 주입을 통해 정확한 테스트 코드 작성
```

* 10/3 **컴포넌트 스캔과 자동의존 관계 설정 & 자바 코드로 직접 스프링 빈 등록하기**
```
1. 컴포넌트 어노테이션과 @Autowired 어노테이션을 사용해 스프링 빈으로 등록하고 의존관계를 주입한다.
2. SpringConfig 파일을 만들고 자바 코드로 스프링 빈을 등록한다.
3. 자바 코드로 의존관계를 주입했을 때의 이점에 대해 생각해본다. 
4. 다형성의 개념과 이점을 되새기며! 자바 코드로 스프링빈 등록시 편리함에 대해 생각해본다. 
```

* 10/3 **회원 웹 기능**
```
1. 회원 데이터를 입력 받고 리스트 출력 화면 구현
 ( *새로웠던건 폼에서 입력한 데이터를 컨트롤러에서 받을 때 Member형 으로 받지 않고 MemberForm 클래스를 따로 생성해서 받았다는 점)
```

* 10/3 **H2 데이터베이스 설치**
```
1. H2 DB를 설치해보고 ddl sql문은 파일로 관리한다.
```
