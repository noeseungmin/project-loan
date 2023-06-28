# 대출 서비스 프로젝트

## 목차
- [개발 환경](#환경)
- [기술 세부 스택](#스택)
  + [spring boot](#spring)
- [주요내용](#내용)
- [패키징구조](#패키징)
- [다이어그램](#다이어)
- [유스케이스](#케이스)
- [API](#API)
- [실행화면](#실행)
- [핵심기능](#핵심)

## <div id="환경">개발 환경</div>

* Intellij IDEA Ultimate 2022.2.2
* Java 11
* Gradle 7.6.1
* Spring Boot 2.7.10
* Postman 10.14.9

## <div id="스택">기술 세부 스택</div>

<div id="spring">Spring Boot</div>

* Spring Web
* Spring Data JPA
* Mysql
* Lombok

## <div id="내용">주요 내용</div>
* 대출 도메인의 이해와 분석
* 대출 상담/신청 기능 구현
* 대출 심사/집행 기능 구현
* 대출 상환 기능 구현

## <div id="패키징">패키징 구조</div>
<img width="50%" src="https://github.com/noeseungmin/project-loan/assets/106221717/1fc7721c-3d06-4df1-b2d7-83099865533a"/>

## <div id="다이어">ERD</div>
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/573f3475-4296-4422-92ae-3f82d743d8bc"/>

## <div id="API">API 설계</div>
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/52a32c13-6c96-4cb7-bbc2-063a0a355d1e"/>

## <div id="케이스">유스케이스</div>

<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/6fe88b57-df19-4d79-b475-b4645a7ac00f"/>

## <div id="실행">실행 화면</div>
### 1. 상담
<img width="50%" src="https://github.com/noeseungmin/project-loan/assets/106221717/3ef7a3d3-0697-48a3-9316-032cb0eb072e"/>

#### 1.1 등록
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/f5201fa2-964f-4e01-90e1-d54d6c763582"/>

#### 1.2 수정
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/3733323f-8307-47e9-977e-5bfe8bdc0efb"/>

### 2. 신청
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/74048cfc-71ed-4d69-be86-c70e4e90606e"/>

#### 2.1.1 등록
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/2420b937-1f97-4a69-9ed3-de5ee3096e93"/>

#### 2.1.1 조회
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/9b582b91-5f06-4205-a16c-c794bc8a1288"/>

#### 2.1.3 수정
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/91d9527d-a186-4ce2-b723-770e26ece120"/>

#### 2.1.4 신청 약관 등록
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/456b1479-1db5-42a4-8fbf-e8b088ca69dd"/>

#### 2.1.5 대출 계약
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/59f0b503-dacb-41ef-b8c0-173fe55f01c5"/>

#### 2.2.1 약관 등록
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/0e213463-4c33-46e9-893d-342212b0010e"/>

#### 2.2.2 약관 전체조회
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/2d4b6d46-5d64-41ff-a393-897abb1c1f31"/>

#### 2.3.1 서류 등록
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/dc02ff72-801e-499d-a9e4-ecb06c3079e4"/>

#### 2.3.2 서류 조회
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/f7ab4b00-0692-48a7-9c38-21cca2f7c43a"/>

### 3. 심사
<img width="40%" src="https://github.com/noeseungmin/project-loan/assets/106221717/18ef0e7a-135d-47b5-ba73-8224d503366e"/>

#### 3.1 등록
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/2fded3b6-24a5-406b-91dd-1233d7ee724e"/>

#### 3.2 수정
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/550e25c1-b336-4e0a-aa4d-22d5aea75329"/>

#### 3.3 조회(신청ID)
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/5f0102c8-8115-456d-aa92-2f621300b030"/>

#### 3.4 조회(신청ID)
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/00f833d5-33c0-4d73-8804-1e1f6848258b"/>

#### 3.4 한도부여
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/b0c5b743-0335-4d3e-ac95-37fbee19415d"/>

### 3. 집행
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/6bc43de1-7420-4db0-9a47-493248588d71"/>

#### 3.1 집행
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/78a84bc2-87db-4915-a92c-cd4b5327ecbf"/>

#### 3.2 집행 조회
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/9728e3db-77e4-4a12-90ef-3bc8d308187a"/>

#### 3.3 집행 수정
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/911419cc-31dc-4a57-a3ff-82cfee23d5e5"/>

### 4. 상환
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/3e9076b0-27e9-4dcc-a923-5c374ff4d132"/>

#### 4.1 상환
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/a1446359-86be-4aec-b1e9-813c5e9a5b48"/>

#### 4.2 상환 조회
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/8a873e97-7a9a-43cd-9940-d7373e9c26e3"/>

#### 4.3 상환 수정
<img width="80%" src="https://github.com/noeseungmin/project-loan/assets/106221717/8e9956c4-8ea7-4936-a10d-2b3f4057065e"/>

## <div id="핵심">핵심 기능</div>
### API 공통 응답 포맷
```java
public enum ResultType {

    SUCCESS("0000", "success"),
    NOT_EXIST("4001", "file not exist"),
    SYSTEM_ERROR("9000", "system error");

    private final String code;
    private final String desc;
}
```

```java
public class BaseException extends RuntimeException {

    private String code = "";
    private String desc = "";
    private String extraMessage = "";

    public BaseException(ResultType resultType) {
        super(resultType.getDesc());
        this.code = resultType.getCode();
        this.desc = resultType.getDesc();
    }
}
```
* 공통 에러 데이터를 담을 사용자 정의 예외 클래스 생성

```java
public class ResultObject implements Serializable {

    public String code;
    public String desc;

    public ResultObject(ResultType resultType) {
        this.code = resultType.getCode();
        this.desc = resultType.getDesc();
    }

    public ResultObject(BaseException e) {
        this.code = e.getCode();
        this.desc = e.getDesc();
    }

    public static ResultObject getSuccess() {

        return new ResultObject(ResultType.SUCCESS);
    }
}
```
* 공통 응답 값에 사용될 resultObject 클래스 생성

```java
public class ResponseDto<T> implements Serializable {

    private ResultObject result;
    private T data;

    public ResponseDto(ResultObject result) {
        this.result = result;
    }

    public ResponseDto(T data) {
        this.data = data;
    }

    public static <T> ResponseDto<T> ok() {

        return new ResponseDto<>(ResultObject.getSuccess());
    }

    public static <T> ResponseDto<T> ok(T data) {
        return new ResponseDto<>(ResultObject.getSuccess(), data);
    }

    public static <T> ResponseDto<T> response(T data) {
        return new ResponseDto<>(ResultObject.getSuccess(), data);
    }

    public ResponseDto(BaseException ex) {
        this.result = new ResultObject(ex);
    }
}
```

```java
public abstract class AbstractController {

    protected <T> ResponseDto<T> ok(){
        return ok(null, ResultObject.getSuccess());
    }

    protected <T> ResponseDto<T> ok(T data) {
        return ok(data, ResultObject.getSuccess());
    }

    protected <T> ResponseDto<T> ok(T data, ResultObject result) {
        ResponseDto<T> obj = new ResponseDto<>();
        obj.setResult(result);
        obj.setData(data);

        return obj;
    }
}
```
* 요청에 대한 응답값을 통일화 하기 위한 추상클래스




## <div id="고찰">고찰</div>



