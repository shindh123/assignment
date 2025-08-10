# 데이터베이스 명세

## 테이블 구조

### 1. categories 테이블

카테고리 정보를 저장하는 테이블입니다.

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|-------|------------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 카테고리 고유 식별자 |
| name | VARCHAR | NOT NULL, UNIQUE | 카테고리 이름 |

**유니크 제약조건**:
- `uk_name`: name 컬럼에 대한 유니크 제약조건

### 2. displays 테이블

전시 정보를 저장하는 테이블입니다.

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|-------|------------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 전시 고유 식별자 |
| name | VARCHAR | NOT NULL, UNIQUE | 전시 이름 |
| description | VARCHAR | NULL 허용 | 전시 설명 |

**유니크 제약조건**:
- `uk_name`: name 컬럼에 대한 유니크 제약조건

### 3. display_category_context_mappings 테이블

전시와 카테고리 간의 연결 관계를 저장하는 매핑 테이블입니다. 계층 구조와 순서 정보를 함께 관리합니다.

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|-------|------------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 매핑 고유 식별자 |
| display_id | BIGINT | NOT NULL, FK | displays 테이블의 id 참조 (전시 ID) |
| category_id | BIGINT | NOT NULL, FK | categories 테이블의 id 참조 |
| parent_id | BIGINT | NULL 허용 | 부모 매핑 ID (계층 구조 표현) |
| ordering | INT | NOT NULL | 정렬 순서 |

**유니크 제약조건**:
- `uk_display_category_context_mappings`: display_id와 category_id 조합에 대한 유니크 제약조건

## 관계 설명

1. **전시와 카테고리 관계**:
   - 하나의 전시는 여러 카테고리와 연결될 수 있습니다. (1:N)
   - 하나의 카테고리는 여러 전시에 연결될 수 있습니다. (1:N)
   - 이 두 엔티티 간의 다대다(N:M) 관계는 `display_category_context_mappings` 테이블을 통해 구현됩니다.

2. **계층 구조**:
   - `display_category_context_mappings` 테이블의 `parent_id` 컬럼을 통해 카테고리 간의 계층 구조를 표현합니다.
   - 루트 카테고리의 경우 `parent_id`가 NULL입니다.
   - 이를 통해 전시 내에서 카테고리의 트리 구조를 구현할 수 있습니다.

3. **순서 지정**:
   - 같은 depth의 카테고리들은 `ordering` 컬럼을 통해 순서를 정할 수 있습니다.
   - 낮은 값일수록 높은 우선순위를 가집니다.

## 제약사항

1. 카테고리 이름과 디스플레이 이름은 각각 중복될 수 없습니다. (유니크 제약조건)
2. 하나의 디스플레이에 동일한 카테고리가 중복해서 매핑될 수 없습니다. (유니크 제약조건)
3. `display_id`와 `category_id`는 각각 `displays`와 `categories` 테이블의 외래 키로 참조 무결성을 유지해야 합니다.

## 인덱스 권장사항

1. `display_category_context_mappings` 테이블의 `display_id`, `category_id`, `parent_id` 컬럼에 대한 인덱스 생성을 권장합니다.
2. 자주 조회되는 패턴에 따라 복합 인덱스 추가를 고려할 수 있습니다.


# 데이터베이스 명세

## 테이블 구조

### 1. categories 테이블

카테고리 정보를 저장하는 테이블입니다.

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|-------|------------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 카테고리 고유 식별자 |
| name | VARCHAR | NOT NULL, UNIQUE | 카테고리 이름 |

**유니크 제약조건**:
- `uk_name`: name 컬럼에 대한 유니크 제약조건

### 2. displays 테이블

전시 정보를 저장하는 테이블입니다.

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|-------|------------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 전시 고유 식별자 |
| name | VARCHAR | NOT NULL, UNIQUE | 전시 이름 |
| description | VARCHAR | NULL 허용 | 전시 설명 |

**유니크 제약조건**:
- `uk_name`: name 컬럼에 대한 유니크 제약조건

### 3. display_category_context_mappings 테이블

전시와 카테고리 간의 연결 관계를 저장하는 매핑 테이블입니다. 계층 구조와 순서 정보를 함께 관리합니다.

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|-------|------------|---------|------|
| id | BIGINT | PK, AUTO_INCREMENT | 매핑 고유 식별자 |
| display_id | BIGINT | NOT NULL, FK | displays 테이블의 id 참조 (전시 ID) |
| category_id | BIGINT | NOT NULL, FK | categories 테이블의 id 참조 |
| parent_id | BIGINT | NULL 허용 | 부모 매핑 ID (계층 구조 표현) |
| ordering | INT | NOT NULL | 정렬 순서 |

**유니크 제약조건**:
- `uk_display_category_context_mappings`: display_id와 category_id 조합에 대한 유니크 제약조건

## 관계 설명

1. **전시와 카테고리 관계**:
   - 하나의 전시는 여러 카테고리와 연결될 수 있습니다. (1:N)
   - 하나의 카테고리는 여러 전시에 연결될 수 있습니다. (1:N)
   - 이 두 엔티티 간의 다대다(N:M) 관계는 `display_category_context_mappings` 테이블을 통해 구현됩니다.

2. **계층 구조**:
   - `display_category_context_mappings` 테이블의 `parent_id` 컬럼을 통해 카테고리 간의 계층 구조를 표현합니다.
   - 루트 카테고리의 경우 `parent_id`가 NULL입니다.
   - 이를 통해 전시 내에서 카테고리의 트리 구조를 구현할 수 있습니다.

3. **순서 지정**:
   - `ordering` 컬럼을 통해 디스플레이 내에서 카테고리의 표시 순서를 지정합니다.
   - 낮은 값일수록 높은 우선순위를 가집니다.

## 제약사항

1. 카테고리 이름과 디스플레이 이름은 각각 중복될 수 없습니다. (유니크 제약조건)
2. 하나의 디스플레이에 동일한 카테고리가 중복해서 매핑될 수 없습니다. (유니크 제약조건)
3. `display_id`와 `category_id`는 각각 `displays`와 `categories` 테이블의 외래 키로 참조 무결성을 유지해야 합니다.

## 인덱스 
1. `display_category_context_mappings` 테이블에서 (`display_id`, `parent_id`) 컬럼에 대한 인덱스 생성이 필요해 보입니다
   (카테고리 특성 상 화면에 보여질 만큼보다 많은 양을 다루진 않을 것 같아 인덱스는 제외했습니다) 
2. 데이터가 늘어나고 access pattern에 따라 인덱스는 추가로 고려할 수 있습니다.
