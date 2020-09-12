#charset 설정
SET NAMES utf8mb4;

#DB 생성
DROP DATABASE IF EXISTS whc;
CREATE DATABASE whc;
USE whc;

# 카테고리 테이블 생성
DROP TABLE IF EXISTS cateItem;
CREATE TABLE cateItem (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    `name` CHAR(100) NOT NULL UNIQUE
);
# 카테고리 추가
INSERT INTO cateItem SET regDate = NOW(), `name` = 'IT/일반'; #1
INSERT INTO cateItem SET regDate = NOW(), `name` = 'IT/JAVA'; #2
INSERT INTO cateItem SET regDate = NOW(), `name` = 'JSP'; #3
INSERT INTO cateItem SET regDate = NOW(), `name` = 'Android'; #4
INSERT INTO cateItem SET regDate = NOW(), `name` = 'JavaScript'; #5
INSERT INTO cateItem SET regDate = NOW(), `name` = '일상/취미'; #6

#게시물 테이블 생성
DROP TABLE IF EXISTS artucle;
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    cateItemId INT(10) UNSIGNED NOT NULL,
    displayStatus TINYINT(1) UNSIGNED NOT NULL,
    `title` CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 6,
displayStatus = 1,
title = '블로그를 시작합니다',
`body` = '# 내용\n## 내용 입니다'

SELECT * FROM article