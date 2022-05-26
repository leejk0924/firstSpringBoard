Insert INTO article(id, title, content) VALUES (1, '가가가가', '1111');
Insert INTO article(id, title, content) VALUES (2, '나나나나', '2222');
Insert INTO article(id, title, content) VALUES (3, '다다다다', '3333');

-- article 더미 데이터
Insert INTO article(id, title, content) VALUES (4, '당신의 인생 영화는?', '댓글 ㄱㄱㄱ');
Insert INTO article(id, title, content) VALUES (5, '당신의 소울 푸드는?', '댓글 ㄱㄱㄱ');
Insert INTO article(id, title, content) VALUES (6, '당신의 취미는?', '댓글 ㄱㄱㄱ');

-- comment 더미 데이터
---- 4번 게시글의 댓글
Insert INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'Park', '굿 윌 헌팅');
Insert INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'Kim', '아이 엠 샘');
Insert INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'Choi', '쇼생크의 탈출');
---- 5번 게시글의 댓글
Insert INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'Park', '치킨');
Insert INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'Kim', '피자');
Insert INTO comment(id, article_id, nickname, body) VALUES (6, 5, 'Choi', '스시');
---- 6번 게시글의 댓글
Insert INTO comment(id, article_id, nickname, body) VALUES (7, 6, 'Park', '조깅');
Insert INTO comment(id, article_id, nickname, body) VALUES (8, 6, 'Kim', '유튜브');
Insert INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'Choi', '독서');
