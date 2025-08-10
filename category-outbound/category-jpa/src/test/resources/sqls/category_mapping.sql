INSERT INTO displays (id, name, description) VALUES (1, '테스트 페이지', '테스트용 카테고리');

INSERT INTO categories (id, name) VALUES
(1, '의류'),
(2, '상의'),
(3, '하의'),
(4, '티셔츠');

INSERT INTO display_category_context_mappings (category_id, display_id, parent_id, ordering) VALUES
(1, 1, NULL, 1),
(2, 1, 1, 1),
(3, 1, 1, 2),
(4, 1, 2, 1);
