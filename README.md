# blogsearch
## 서비스 개요
- 카카오 API를 활용한 블로그 검색 서비스. 

## 요구사항 분석
1. 키워드를 통해 블로그 검색을 한다.
2. sorting 방식 2가지 지원한다.
   1. 정확도순
   2. 최신순
3. pagination 형태 제공한다.
4. 검색 소스는 카카오 API를 활용한다. 
   1. 관련 링크 : https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog
5. 인기 검색어를 관리한다.
   1. 사용자가 많이 검색한 순으로 10개 키워드를 제공한다.
   2. 검색어 별로 검색된 횟수도 표기한다.

## 검색 소스 API 명세서
- URL : https://dapi.kakao.com/v2/search/blog
- method : GET
- HTTP/1.1
- Authorization : KakaoAK ${REST_API_KEY}

###REQUEST PARAMETER
|NAME|TYPE|DESCRIPTION|REQUIRED|
|:---|:---|:----------|:-------|
|query|String|검색 질의어.<br> 특정 블로그 글만 검색하고 싶은 경우, 블로그 url과 검색어를 공백('')구분자로 넣을 수 있음.|O|
|sort|String|정렬방식으로 accuracy(정확도순) 또는 recency(최신순) 선택|X|
|page|Integer|페이지 번호, 1~50, default 1|X|
|size|Integer|페이지 당 문서 수, 1~50, default 10|X|
####sample
```markdown
curl -v -X GET "https://dapi.kakao.com/v2/search/blog" \
--data-urlencode "query=https://brunch.co.kr/@tourism 집짓기" \
-H "Authorization: KakaoAK ${REST_API_KEY}"
```
###RESPONSE
|NAME|NAME|TYPE|DESCRIPTION|
|:---|:---|:---|:----------|
|meta|-|Object|meta data|
|-|total_count|Integer|검색된 문서 수
|-|pageable_count|Integer|total_count 중 노출 가능한 문서 수
|-|is_end|Boolean|현제 페에지가 마지막 페이지인지 여부, 값이 false 이면 page를 증가시켜 다음 페이지 요청 가능.
|documents|-|Array|문서 객체를 담은 배열
|-|title|String|블로그 글 제목
|-|contents|String|블로그 글 요약
|-|url|String|블로그 글 URL
|-|blogname|String|블로그의 이름
|-|thumbnail|String|검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음.
|-|datetime|Datetime|블로그 글 작성 시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
####sample
```json
{
  "meta": {
    "total_count": 5,
    "pageable_count": 5,
    "is_end": true
  },
  "documents": [
    {
    "title": "작은 <b>집</b> <b>짓기</b> 기본컨셉 - <b>집</b><b>짓기</b> 초기구상하기",    
    "contents": "이 점은 <b>집</b>을 지으면서 고민해보아야 한다. 하지만, 금액에 대한 가성비 대비 크게 문제되지 않을 부분이라 생각하여 설계로 극복하자고 생각했다. 전체 <b>집</b><b>짓기</b>의 기본방향은 크게 세 가지이다. 우선은 여가의 영역 증대이다. 현대 시대 일도 중요하지만, 여가시간 <b>집</b>에서 어떻게 보내느냐가 중요하니깐 이를 기본적...",
    "url": "https://brunch.co.kr/@tourism/91",
    "blogname": "정란수의 브런치",
    "thumbnail": "http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc",
    "datetime": "2017-05-07T18:50:07.000+09:00"
    }
  ]
}
```

## FLOW
- CLIENT -> blogsearch SERVER -> KAKAO API SERVER

## blogsearch API 명세서
- URL : https://localhost:10080/v1/search/blog
- method : POST

###REQUEST
|NAME|TYPE|DESCRIPTION|REQUIRED|
|:---|:---|:----------|:-------|
|query|String|검색 질의어.<br> 특정 블로그 글만 검색하고 싶은 경우, 블로그 url과 검색어를 공백('')구분자로 넣을 수 있음.|O|
|sort|String|'accuracy'(정확도순) 또는 'recency'(최신순)|X|
|page|Integer|페이지 번호, 1~50, default 1|X|
|size|Integer|페이지 당 문서 수, 1~50, default 10|X|

###RESPONSE
|NAME|NAME|NAME|TYPE|DESCRIPTION|
|:---|:---|:---|:---|:----------|
|resultCode|-|-|String|응답 코드
|resultMessage|-|-|String|응답 메세지
|resultData|-|-|Object|응답 데이터
|-|meta|-|Object|meta data
|-|-|total_count|Integer|검색된 문서 수
|-|-|pageable_count|Integer|total_count 중 노출 가능한 문서 수
|-|-|is_end|Boolean|현제 페에지가 마지막 페이지인지 여부, 값이 false 이면 page를 증가시켜 다음 페이지 요청 가능.
|-|documents|-|Array|문서 객체를 담은 배열
|-|-|title|String|블로그 글 제목
|-|-|contents|String|블로그 글 요약
|-|-|url|String|블로그 글 URL
|-|-|blogname|String|블로그의 이름
|-|-|thumbnail|String|검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음.
|-|-|datetime|Datetime|블로그 글 작성 시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]

## 응답코드, 응답 메세지
- 0000 : SUCCESS
- 5000 : BAD REQUEST
- 9000 : INTERNAL SERVER ERROR