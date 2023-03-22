# API 명세
### 1. 블로그 검색

입력한 키워드에 블로그 검색 결과를 카카오 및 네이버 블로그 검색 API를 통하여 제공합니다.
(기본적으로 카카오 API를 이용하며, 카카오 API 장애 시 네이버 API를 이용하여 결과를 반화합니다.)

### 기본 정보
GET /search/blogs
Host : http://localhost:8080

## Request
|Name|Type|Description|Required|
|---|---|---|---|
|query|String|검색을 원하는 질의어|O|
|sort|String|결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy|X|
|page|Integer|결과 페이지 번호, 1~50 사이의 값, 기본 값 1|X|
|size|Integer|한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10|X|

## Response
## pageInfo
|Name|Type|Description|
|---|---|---|
|totalCount|Integer|검색된 문서 수|
|totalPages|Integer|전체 페이지|
|currentPage|Integer|현재 페이지|X|

## blogs
|Name|Type|Description|
|---|---|---|
|blogname|String|블로그의 이름
|contents|String|블로그 글 요약
|datetime|Datetime|블로그 글 작성시간, ISO 8601( [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz])
|thumbnail|String|검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음
|title|String|블로그 글 제목
|url|String|블로그 글 URL

## Example
```bash
{
    "pageInfo": {
        "totalCount": 800,
        "totalPages": 267,
        "currentPage": 1
    },
    "blogs": [
        {
            "blogname": "위클리쿡 Weekly Cook",
            "contents": "마녀<b>스프</b>는 다이어트를 하고 있는 다이어터들 사이에서 유행하는 <b>수프</b>입니다. 몸에 붙은 지방들을 단기간에 뺄 수 있다는 의미에서 그 이름이 탄생했으며 1980년대 미국 배우 기네스 펠트로의 다이어트 방법으로도 주목받았습니다. 이후 한국에서도 단기간 다이어트에 좋은 마녀<b>스프</b> 레시피가 유행 중입니다. 오늘은...",
            "datetime": "2023-02-21T23:25:03.000+09:00",
            "thumbnail": "https://search2.kakaocdn.net/argon/130x130_85_c/FFKYD6nWZVn",
            "title": "마녀<b>스프</b> 레시피",
            "url": "http://weeklycook.tistory.com/62"
        },
        {
            "blogname": "천안전자담배터미널",
            "contents": "일교차가 크기 때문에 얇게입어버리면 감기에 걸리니 따뜻하게 입고다니길 바라겠습니다!! ​ 음료수 사이다 그대로 청량함 가득 - 깔끔함 맥스 프라우드 <b>스프</b>롸잇 ​ 향긋한 적포도 과일 호불호가 갈리지않아 인기 만점 프라우드 포도믹스 ​ 탄산처럼 톡 쏘는 사이다 맛과 호불호가 안갈리는 포도 맛 구성 입니다 ♥ ​ 과하지...",
            "datetime": "2023-03-21T17:44:00.000+09:00",
            "thumbnail": "https://search3.kakaocdn.net/argon/130x130_85_c/82XQU4WhbWs",
            "title": "프라우드 <b>스프</b>롸잇 / 포도믹스",
            "url": "https://blog.naver.com/hotdealvape/223051444350"
        },
        {
            "blogname": "온종일 스토리",
            "contents": "농심 가락 우동 건더기<b>스프</b> 업소용 구매 한번 해봤습니다 500그램 8000원대 택배비 포함 10000원 초반대 한다고 보시면 됩니다 식자매 매장 같은 곳도 아마도 판매는 할 겁니다 특히 우동집 또는 분식집에 가면 국물에 건더기<b>스프</b>를 위에 넣어서 먹기도 하는데요 라면 끓일 때 넣어서 먹어 볼까 해서 한봉지만 주문...",
            "datetime": "2023-02-26T18:21:27.000+09:00",
            "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/LNdWDzKsB9f",
            "title": "농심 가락 우동건더기<b>스프</b> 후기",
            "url": "http://pk0025.tistory.com/2998"
        }
    ]
}
```




### 1. 인기 검색어 목록

사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다.
(30초마다 갱신됩니다.)

### 기본 정보
GET /keywords/popular
Host : http://localhost:8080

## Response
|Name|Type|Description|
|---|---|---|
|keyword|String|검색 키워드|
|count|Integer|검색 횟수|

## Example
```bash
[
    {
        "keyword": "스프링",
        "count": 9
    },
    {
        "keyword": "부트",
        "count": 6
    },
    {
        "keyword": "JPA",
        "count": 3
    },
    ...
]
```
