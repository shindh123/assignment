# Category Core Module

이 모듈은 카테고리 도메인의 핵심 비즈니스 로직을 포함합니다.

## Domain & Usecases

### Category Domain Usecases
카테고리 도메인과 관련된 유스케이스입니다.

- `CreateCategory`: 새로운 카테고리 생성
- `UpdateCategory`: 카테고리 정보 수정
- `DeleteCategory`: 카테고리 삭제
- `GetCategory`: 단일 카테고리 조회
- `GetCategories`: 카테고리 목록 조회

### Display Domain Usecases
전시 도메인과 관련된 유스케이스입니다.

- `CreateDisplay`: 새로운 전시 생성
- `UpdateDisplay`: 전시 정보 수정
- `DeleteDisplay`: 전시 삭제
- `GetDisplay`: 단일 전시 조회
- `GetDisplays`: 전시 목록 조회

### DisplayCategoryContext Domain Usecases
카테고리-전시 매핑/컨텍스트 정보 도메인과 관련된 유스케이스입니다.

- `AssignCategoryToDisplay`: 카테고리를 전시에 할당
- `UpdateCategoryDisplayMapping`: 카테고리-전시 매핑 정보 수정 (순서, 깊이 등)
- `RemoveCategoryFromDisplay`: 전시에서 카테고리 제거
- `GetCategoryDisplayMapping`: 특정 매핑 정보 조회
- `GetCategoryDisplayMappings`: 전시에 할당된 카테고리 매핑 목록 조회

## Read Models

실제 서비스에서 사용되는 조회 전용 모델들입니다.
### DisplayCategoryNode
카테고리의 계층을 표현하는 노드입니다.
- `GetDisplayCategoryTree`: 전체 카테고리 계층 구조 조회
- `GetDisplayCategorySubTree`: 특정 카테고리 하위 계층 구조 조회
- `GetDisplayCategoryPath`: 특정 카테고리까지의 경로 조회

### DisplayCategoryGroup
전시 목적으로 그룹화된 카테고리 정보입니다.
- `GetDisplayCategoryGroup`: 특정 전시의 카테고리 정보 조회
- `GetDisplayCategoryGroups`: 여러 그룹의 카테고리 정보 조회
- `GetDisplayCategoryGroupTree`: 그룹 내 카테고리 계층 구조 조회