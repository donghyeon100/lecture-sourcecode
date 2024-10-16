/* 계정 생성(관리자) */

ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 계정 생성
CREATE USER spring_bdh IDENTIFIED BY spring1234;

-- 권한 부여
GRANT CONNECT, RESOURCE, CREATE VIEW TO spring_bdh;

-- 객체 생성 공간 할당
ALTER USER spring_bdh DEFAULT TABLESPACE USERS
QUOTA 20M ON USERS;

------------------------------------------------------------------
-- "" 작성 시 대/소문자 구분
--> "" 붙이는걸 권장

-- VARCHAR2(10) : 10바이트 문자열 (바이트 단위), 최대 4000바이트
-- NVARCHAR2(10) : 10글자 문자열 (유니코드), 최대 4000바이트


/* MEMBER 테이블 생성 */
CREATE TABLE "MEMBER"(
	"MEMBER_NO" NUMBER CONSTRAINT MEMBER_PK PRIMARY KEY,
	"MEMBER_EMAIL" VARCHAR2(50) NOT NULL,
	"MEMBER_PW" VARCHAR2(100) NOT NULL,
	"MEMBER_NICKNAME" NVARCHAR2(10) NOT NULL,
	"MEMBER_TEL" CHAR(11) NOT NULL,
	"MEMBER_ADDRESS" NVARCHAR2(200),
	"PROFILE_IMG" VARCHAR2(300),
	"ENROLL_DATE" DATE DEFAULT SYSDATE NOT NULL,
	"MEMBER_DEL_FL" CHAR(1) DEFAULT 'N' CHECK("MEMBER_DEL_FL" IN ('Y', 'N')),
	"AUTHORITY" NUMBER DEFAULT 1 CHECK("AUTHORITY" IN (1,2))
);

COMMENT ON COLUMN "MEMBER"."MEMBER_NO" 		 IS '회원 번호(SEQ_MEMBER_NO)';
COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" 	 IS '회원 이메일(ID역할)';
COMMENT ON COLUMN "MEMBER"."MEMBER_PW" 		 IS '비밀번호(암호화)';
COMMENT ON COLUMN "MEMBER"."MEMBER_NICKNAME" IS '별명';
COMMENT ON COLUMN "MEMBER"."MEMBER_TEL" 	 IS '전화번호(-없음)';
COMMENT ON COLUMN "MEMBER"."MEMBER_ADDRESS"  IS '주소';
COMMENT ON COLUMN "MEMBER"."PROFILE_IMG" 	 IS '프로필 이미지 저장 경로';
COMMENT ON COLUMN "MEMBER"."ENROLL_DATE" 	 IS '가입일';
COMMENT ON COLUMN "MEMBER"."MEMBER_DEL_FL" 	 IS '탈퇴여부(Y:탈퇴, N:정상)';
COMMENT ON COLUMN "MEMBER"."AUTHORITY" 		 IS '권한(1:일반, 2:관리자)';


-- 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE;

-- 샘플 계정 추가
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'member01@naver.com', 'pass01',
	'회원1', '01012341234', 
	'04540^^^서울시 중구 남대문로 120^^^2층',
	NULL, DEFAULT, DEFAULT, DEFAULT
);

COMMIT;

SELECT * FROM "MEMBER"
WHERE MEMBER_NO = 3;


-- 로그인
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NICKNAME ,
	MEMBER_TEL , MEMBER_ADDRESS , PROFILE_IMG , AUTHORITY ,
	TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"') AS ENROLL_DATE 
FROM "MEMBER"
WHERE MEMBER_DEL_FL = 'N'
AND MEMBER_EMAIL = member01@naver.com
AND MEMBER_PW = 'pass01'
;


-- 샘플 회원 비밀번호 변경(암호화 적용)
UPDATE "MEMBER" SET
MEMBER_PW = '$2a$10$9ocVv7Fdvm.k04WK72/h5egQKpBzJu0WU7MTk3cvouvlSlgl2MdMi'
WHERE MEMBER_NO = 1;

COMMIT;


-- 회원 정보 수정
-- "MEMBER" 테이블에서
-- MEMBER_NO가 일치하는 회원의
-- MEMBER_NICKNAME, MEMBER_TEL, MEMBER_ADDRESS 수정
UPDATE "MEMBER" SET
MEMBER_NICKNAME = 'TEST',
MEMBER_TEL = '01012345678',
MEMBER_ADDRESS = 'AAA^^^BBB^^^CCC'
WHERE MEMBER_NO = 2
;

ROLLBACK;

-- BCrypt 암호화 시 비밀번호를 조회한 후
-- matches() 메서드를 이용해서 비교

-- 로그인한 회원의 암호화된 비밀번호 조회
SELECT MEMBER_PW 
FROM "MEMBER"
WHERE MEMBER_NO = 회원번호  ;


-- 비밀번호 변경
UPDATE "MEMBER" SET 
MEMBER_PW = '변경된 비밀번호'
WHERE MEMBER_NO = '회원 번호'  ;



-- 회원 탈퇴
UPDATE "MEMBER" SET 
MEMBER_DEL_FL = 'Y'
WHERE MEMBER_NO = 2 ;


SELECT * FROM "MEMBER";


-- 전체 회원 탈퇴 복구
UPDATE "MEMBER" SET 
MEMBER_DEL_FL = 'N';
COMMIT; 


-- 관리자 권한으로 변경
SELECT * FROM "MEMBER";

UPDATE "MEMBER" SET 
AUTHORITY = 2
WHERE MEMBER_NO = 1;

COMMIT;

-- 전체 회원 조회
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_DEL_FL
FROM "MEMBER"
ORDER BY MEMBER_NO;

-- 강제 탈퇴 처리
UPDATE "MEMBER" SET
MEMBER_DEL_FL = 'Y'
WHERE MEMBER_NO = 6;
COMMIT;

SELECT * FROM MEMBER
WHERE MEMBER_NO = 11;

UPDATE "MEMBER" SET 
AUTHORITY = DECODE(AUTHORITY,1,2,2,1)
WHERE MEMBER_NO = 6;

ROLLBACK;


-- 회원 번호로 이메일 조회
SELECT MEMBER_EMAIL
FROM "MEMBER"
WHERE MEMBER_NO = 5;


-- 이메일 중복 검사(중복 O -> 1, 중복 X -> 0)
SELECT COUNT(*) 
FROM "MEMBER"
WHERE MEMBER_DEL_FL = 'N'
AND MEMBER_EMAIL = 'member01@naver.com123123123';


-- 일부라도 일치하는 이메일 모두 조회
SELECT MEMBER_EMAIL
FROM "MEMBER"
WHERE MEMBER_EMAIL LIKE '%${keyword}%' ;




/* 이메일 인증키 테이블 */
DROP TABLE "TB_AUTH_KEY";

CREATE TABLE "TB_AUTH_KEY" (
	"KEY_NO"	NUMBER		NOT NULL,
	"AUTH_KEY"	CHAR(6)		NOT NULL,
	"EMAIL"	VARCHAR2(50)		NOT NULL,
	"CREATE_TIME"	DATE	DEFAULT SYSDATE	NOT NULL
);



COMMENT ON COLUMN "TB_AUTH_KEY"."KEY_NO" IS '인증키 구분 번호(SEQ_KEY_NO)';

COMMENT ON COLUMN "TB_AUTH_KEY"."AUTH_KEY" IS '코드';

COMMENT ON COLUMN "TB_AUTH_KEY"."EMAIL" IS '이메일';

COMMENT ON COLUMN "TB_AUTH_KEY"."CREATE_TIME" IS '인증 코드 생성 시간';

ALTER TABLE "TB_AUTH_KEY" ADD CONSTRAINT "PK_TB_AUTH_KEY" PRIMARY KEY (
	"KEY_NO"
);


CREATE SEQUENCE SEQ_KEY_NO NOCACHE;


SELECT * FROM TB_AUTH_KEY;



--------------------------------------------------------------------------------------

CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"BOARD_TITLE"	VARCHAR2(150)		NOT NULL,
	"BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"BOARD_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"BOARD_UPDATE_DATE"	DATE		NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"BOARD_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"BOARD_CODE"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글 번호(SEQ_BOARD_NO)';

COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';

COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';

COMMENT ON COLUMN "BOARD"."BOARD_WRITE_DATE" IS '게시글 작성일';

COMMENT ON COLUMN "BOARD"."BOARD_UPDATE_DATE" IS '마지막 수정일(수정 시 UPDATE)';

COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';

COMMENT ON COLUMN "BOARD"."BOARD_DEL_FL" IS '삭제 여부(N : 삭제X , Y : 삭제O)';

COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '작성자 회원 번호';

COMMENT ON COLUMN "BOARD"."BOARD_CODE" IS '게시판 코드 번호';

CREATE TABLE "BOARD_IMG" (
	"IMG_NO"	NUMBER		NOT NULL,
	"IMG_PATH"	VARCHAR2(300)		NOT NULL,
	"IMG_RENAME"	VARCHAR2(30)		NOT NULL,
	"IMG_ORIGINAL_NAME"	VARCHAR2(300)		NOT NULL,
	"IMG_ORDER"	NUMBER		NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD_IMG"."IMG_NO" IS '이미지 번호(SEQ_IMG_NO)';

COMMENT ON COLUMN "BOARD_IMG"."IMG_PATH" IS '이미지 저장 폴더 경로';

COMMENT ON COLUMN "BOARD_IMG"."IMG_RENAME" IS '변경된 이미지 파일 이름';

COMMENT ON COLUMN "BOARD_IMG"."IMG_ORIGINAL_NAME" IS '원본 이미지 파일 이름';

COMMENT ON COLUMN "BOARD_IMG"."IMG_ORDER" IS '이미지 파일 순서 번호';

COMMENT ON COLUMN "BOARD_IMG"."BOARD_NO" IS '이미지가 첨부된 게시글 번호';

CREATE TABLE "BOARD_LIKE" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD_LIKE"."BOARD_NO" IS '게시글 번호';

COMMENT ON COLUMN "BOARD_LIKE"."MEMBER_NO" IS '좋아요 누른 회원 번호';

CREATE TABLE "COMMENT" (
	"COMMENT_NO"	NUMBER		NOT NULL,
	"COMMENT_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"COMMENT_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"COMMENT_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"PARENT_NO"	NUMBER		NULL
);

COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호(SEQ_COMMENT_NO)';

COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';

COMMENT ON COLUMN "COMMENT"."COMMENT_WRITE_DATE" IS '댓글 작성일';

COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제 여부(N : 삭제X, Y : 삭제O)';

COMMENT ON COLUMN "COMMENT"."BOARD_NO" IS '댓글이 작성된 게시글 번호';

COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '댓글 작성자 회원 번호';

COMMENT ON COLUMN "COMMENT"."PARENT_NO" IS '부모 댓글 번호';

CREATE TABLE "BOARD_TYPE" (
	"BOARD_CODE"	NUMBER		NOT NULL,
	"BOARD_NAME"	VARCHAR2(300)		NOT NULL
);

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_CODE" IS '게시판 종류별 코드 번호(SEQ_BOARD_CODE)';

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_NAME" IS '게시판 이름';

CREATE TABLE "CHATTING_ROOM" (
	"CHATTING_NO"	NUMBER		NOT NULL,
	"CREATE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"OPEN_MEMBER"	NUMBER		NOT NULL,
	"PARTICIPANT"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CHATTING_ROOM"."CHATTING_NO" IS '채팅방 번호';

COMMENT ON COLUMN "CHATTING_ROOM"."CREATE_DATE" IS '채팅방 생성일';

COMMENT ON COLUMN "CHATTING_ROOM"."OPEN_MEMBER" IS '개설자 회원 번호';

COMMENT ON COLUMN "CHATTING_ROOM"."PARTICIPANT" IS '참여자 회원 번호';

CREATE TABLE "MESSAGE" (
	"MESSAGE_NO"	NUMBER		NOT NULL,
	"MESSAGE_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"READ_FL"	CHAR	DEFAULT 'N'	NOT NULL,
	"SEND_TIME"	DATE	DEFAULT SYSDATE	NOT NULL,
	"SENDER_NO"	NUMBER		NOT NULL,
	"CHATTING_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "MESSAGE"."MESSAGE_NO" IS '메세지 번호';

COMMENT ON COLUMN "MESSAGE"."MESSAGE_CONTENT" IS '메세지 내용';

COMMENT ON COLUMN "MESSAGE"."READ_FL" IS '읽음 여부';

COMMENT ON COLUMN "MESSAGE"."SEND_TIME" IS '메세지 보낸 시간';

COMMENT ON COLUMN "MESSAGE"."SENDER_NO" IS '보낸 회원의 번호';

COMMENT ON COLUMN "MESSAGE"."CHATTING_NO" IS '채팅방 번호';



ALTER TABLE "MEMBER" ADD CONSTRAINT "PK_MEMBER" PRIMARY KEY (
	"MEMBER_NO"
);

ALTER TABLE "BOARD" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY (
	"BOARD_NO"
);

ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "PK_BOARD_IMG" PRIMARY KEY (
	"IMG_NO"
);

ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "PK_BOARD_LIKE" PRIMARY KEY (
	"BOARD_NO",
	"MEMBER_NO"
);

ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY (
	"COMMENT_NO"
);

ALTER TABLE "BOARD_TYPE" ADD CONSTRAINT "PK_BOARD_TYPE" PRIMARY KEY (
	"BOARD_CODE"
);

ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "PK_CHATTING_ROOM" PRIMARY KEY (
	"CHATTING_NO"
);

ALTER TABLE "MESSAGE" ADD CONSTRAINT "PK_MESSAGE" PRIMARY KEY (
	"MESSAGE_NO"
);

ALTER TABLE "TB_AUTH_KEY" ADD CONSTRAINT "PK_TB_AUTH_KEY" PRIMARY KEY (
	"KEY_NO"
);



ALTER TABLE "BOARD" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_BOARD_TYPE_TO_BOARD_1" FOREIGN KEY (
	"BOARD_CODE"
)
REFERENCES "BOARD_TYPE" (
	"BOARD_CODE"
) ON DELETE CASCADE;

ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "FK_BOARD_TO_BOARD_IMG_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
) ON DELETE CASCADE;

ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_BOARD_TO_BOARD_LIKE_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
) ON DELETE CASCADE;

ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_LIKE_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_BOARD_TO_COMMENT_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
) ON DELETE CASCADE;

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_MEMBER_TO_COMMENT_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_COMMENT_TO_COMMENT_1" FOREIGN KEY (
	"PARENT_NO"
)
REFERENCES "COMMENT" (
	"COMMENT_NO"
) ON DELETE CASCADE;

ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "FK_MEMBER_TO_CHATTING_ROOM_1" FOREIGN KEY (
	"OPEN_MEMBER"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "FK_MEMBER_TO_CHATTING_ROOM_2" FOREIGN KEY (
	"PARTICIPANT"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "MESSAGE" ADD CONSTRAINT "FK_MEMBER_TO_MESSAGE_1" FOREIGN KEY (
	"SENDER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "MESSAGE" ADD CONSTRAINT "FK_CHATTING_ROOM_TO_MESSAGE_1" FOREIGN KEY (
	"CHATTING_NO"
)
REFERENCES "CHATTING_ROOM" (
	"CHATTING_NO"
) ON DELETE CASCADE;

-------------------------------------------------------
/*시퀀스 생성*/
CREATE SEQUENCE SEQ_BOARD_NO NOCACHE; -- 게시글 번호
CREATE SEQUENCE SEQ_BOARD_CODE NOCACHE; -- 게시판 종류 번호
CREATE SEQUENCE SEQ_IMG_NO NOCACHE; -- 게시글 이미지 번호
CREATE SEQUENCE SEQ_COMMENT_NO NOCACHE; -- 댓글 번호


-------------------------------------------------------
/*게시판 종류 추가*/
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '공지사항');
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '자유 게시판');
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '질문 게시판');
COMMIT;

SELECT * FROM "BOARD_TYPE";

-------------------------------------------------------

/*BOARD 테이블 샘플 데이터 삽입(PL/SQL)*/
BEGIN
   FOR I IN 1..1500 LOOP
      INSERT INTO BOARD 
      VALUES( SEQ_BOARD_NO.NEXTVAL,
              SEQ_BOARD_NO.CURRVAL || '번째 게시글',
              SEQ_BOARD_NO.CURRVAL || '번째 게시글 내용 입니다.',
              DEFAULT, DEFAULT, DEFAULT, DEFAULT, 1, 
              CEIL(DBMS_RANDOM.VALUE(0,3))
      );
   END LOOP;
END;

SELECT COUNT(*) FROM "BOARD";

COMMIT;

-- BOARD_CODE가 1(공지사항)인 게시글을 최신순으로 조회
-- 단, 삭제된 글을 제외
SELECT * FROM "BOARD"
WHERE BOARD_CODE = 1
AND BOARD_DEL_FL = 'N'
ORDER BY BOARD_NO DESC;

------------------------------------------------------

-- COMMENT 테이블 샘플 데이터 삽입(PL/SQL)
BEGIN
   FOR I IN 1..500 LOOP
	   INSERT INTO "COMMENT" 
		VALUES(SEQ_COMMENT_NO.NEXTVAL, 
				SEQ_COMMENT_NO.CURRVAL || '번째 댓글',
				DEFAULT, DEFAULT,
				 CEIL(DBMS_RANDOM.VALUE(0,1500)),
				 1, NULL);
   END LOOP;
END;

COMMIT;
 
SELECT count(*) FROM "COMMENT";

-------------------------------------------------------


SELECT BOARD_NO, BOARD_TITLE, MEMBER_NICKNAME, READ_COUNT, 
			
		CASE  
			WHEN SYSDATE - BOARD_WRITE_DATE < 1/24/60
			THEN FLOOR( (SYSDATE - BOARD_WRITE_DATE) * 24 * 60 * 60 ) || '초 전'
			WHEN SYSDATE - BOARD_WRITE_DATE < 1/24
			THEN FLOOR( (SYSDATE - BOARD_WRITE_DATE) * 24 * 60) || '분 전'
			WHEN SYSDATE - BOARD_WRITE_DATE < 1
			THEN FLOOR( (SYSDATE - BOARD_WRITE_DATE) * 24) || '시간 전'
			ELSE TO_CHAR(BOARD_WRITE_DATE, 'YYYY-MM-DD')
		END BOARD_WRITE_DATE ,
	
	(SELECT COUNT(*) FROM "COMMENT" C
	 WHERE C.BOARD_NO = B.BOARD_NO) COMMENT_COUNT,
	 
	(SELECT COUNT(*) FROM BOARD_LIKE L
	 WHERE L.BOARD_NO = B.BOARD_NO) LIKE_COUNT,
	 
	(SELECT IMG_PATH || IMG_RENAME FROM BOARD_IMG I
	WHERE I.BOARD_NO = B.BOARD_NO
	AND IMG_ORDER = 0) THUMBNAIL
FROM "BOARD" B
JOIN "MEMBER" USING(MEMBER_NO)
WHERE BOARD_DEL_FL = 'N'
AND BOARD_CODE = 1
ORDER BY BOARD_NO DESC;

-------------------------------------------------------------


SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, BOARD_CODE,
	READ_COUNT, MEMBER_NICKNAME, MEMBER_NO, PROFILE_IMG,
	TO_CHAR(BOARD_WRITE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') BOARD_WRITE_DATE,
	TO_CHAR(BOARD_UPDATE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') BOARD_UPDATE_DATE,
	(SELECT COUNT(*) 
	FROM "BOARD_LIKE" L
	WHERE L.BOARD_NO = B.BOARD_NO) LIKE_COUNT
FROM "BOARD" B
JOIN "MEMBER" USING(MEMBER_NO)
WHERE BOARD_DEL_FL = 'N'
AND BOARD_CODE = 2
AND BOARD_NO = 1500;




SELECT LEVEL, C.* FROM
	(SELECT COMMENT_NO, COMMENT_CONTENT,
	    TO_CHAR(COMMENT_WRITE_DATE, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"') COMMENT_WRITE_DATE,
	    BOARD_NO, MEMBER_NO, MEMBER_NICKNAME, PROFILE_IMG, PARENT_NO, COMMENT_DEL_FL
	FROM "COMMENT"
	JOIN MEMBER USING(MEMBER_NO)
	WHERE BOARD_NO = 1500) C
WHERE COMMENT_DEL_FL = 'N'
START WITH PARENT_NO IS NULL
CONNECT BY PRIOR COMMENT_NO = PARENT_NO
ORDER SIBLINGS BY COMMENT_NO;



SELECT COUNT(*) FROM "BOARD_LIKE"
WHERE BOARD_NO = 1500
AND MEMBER_NO = 1;



-- 게시글 이미지 번호 생성 함수
CREATE OR REPLACE FUNCTION NEXT_IMG_NO
RETURN NUMBER 
IS NUM NUMBER;
BEGIN 
	SELECT SEQ_IMG_NO.NEXTVAL 
	INTO NUM
	FROM DUAL;
	
	RETURN NUM;
END;



SELECT BOARD_NO, NEXT_IMG_NO() FROM "BOARD"
WHERE BOARD_NO = 1500;


INSERT INTO "BOARD_IMG"
SELECT NEXT_IMG_NO(), '1', '2', '3', 4, 1500 FROM DUAL
UNION 
SELECT NEXT_IMG_NO(), '1', '2', '3', 4, 1500 FROM DUAL
UNION 
SELECT NEXT_IMG_NO(), '1', '2', '3', 4, 1500 FROM DUAL;

SELECT * FROM "BOARD_IMG";

ROLLBACK;

SELECT * FROM MEMBER;


--------------------------------------------------

CREATE TABLE "CHATTING_ROOM" (
	"CHATTING_NO"	NUMBER		NOT NULL,
	"CREATE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"OPEN_MEMBER"	NUMBER		NOT NULL,
	"PARTICIPANT"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CHATTING_ROOM"."CHATTING_NO" IS '채팅방 번호';

COMMENT ON COLUMN "CHATTING_ROOM"."CREATE_DATE" IS '채팅방 생성일';

COMMENT ON COLUMN "CHATTING_ROOM"."OPEN_MEMBER" IS '개설자 회원 번호';

COMMENT ON COLUMN "CHATTING_ROOM"."PARTICIPANT" IS '참여자 회원 번호';

CREATE TABLE "MESSAGE" (
	"MESSAGE_NO"	NUMBER		NOT NULL,
	"MESSAGE_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"READ_FL"	CHAR	DEFAULT 'N'	NOT NULL,
	"SEND_TIME"	DATE	DEFAULT SYSDATE	NOT NULL,
	"SENDER_NO"	NUMBER		NOT NULL,
	"CHATTING_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "MESSAGE"."MESSAGE_NO" IS '메세지 번호';

COMMENT ON COLUMN "MESSAGE"."MESSAGE_CONTENT" IS '메세지 내용';

COMMENT ON COLUMN "MESSAGE"."READ_FL" IS '읽음 여부';

COMMENT ON COLUMN "MESSAGE"."SEND_TIME" IS '메세지 보낸 시간';

COMMENT ON COLUMN "MESSAGE"."SENDER_NO" IS '보낸 회원의 번호';

COMMENT ON COLUMN "MESSAGE"."CHATTING_NO" IS '채팅방 번호';



CREATE SEQUENCE SEQ_ROOM_NO NOCACHE;
CREATE SEQUENCE SEQ_MESSAGE_NO NOCACHE;


ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "PK_CHATTING_ROOM" PRIMARY KEY (
	"CHATTING_NO"
);

ALTER TABLE "MESSAGE" ADD CONSTRAINT "PK_MESSAGE" PRIMARY KEY (
	"MESSAGE_NO"
);

ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "FK_MEMBER_TO_CHATTING_ROOM_1" FOREIGN KEY (
	"OPEN_MEMBER"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "CHATTING_ROOM" ADD CONSTRAINT "FK_MEMBER_TO_CHATTING_ROOM_2" FOREIGN KEY (
	"PARTICIPANT"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "MESSAGE" ADD CONSTRAINT "FK_MEMBER_TO_MESSAGE_1" FOREIGN KEY (
	"SENDER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "MESSAGE" ADD CONSTRAINT "FK_CHATTING_ROOM_TO_MESSAGE_1" FOREIGN KEY (
	"CHATTING_NO"
)
REFERENCES "CHATTING_ROOM" (
	"CHATTING_NO"
) ON DELETE CASCADE;




-- 알림 테이블
DROP TABLE "NOTIFICATION";

CREATE TABLE "NOTIFICATION" (
	"NOTIFICATION_NO"	NUMBER		NOT NULL,
	"NOTIFICATION_CONTENT"	NVARCHAR2(500)		NOT NULL,
	"NOTIFICATION_CHECK"	CHAR	DEFAULT 'N'	NOT NULL,
	"NOTIFICATION_DATE"	DATE	DEFAULT CURRENT_DATE	NOT NULL,
	"NOTIFICATION_URL"	NVARCHAR2(500)		NOT NULL,
	"SEND_MEMBER_PROFILE_IMG"	VARCHAR(300)		NULL,
	"SEND_MEMBER_NO"	NUMBER		NOT NULL,
	"RECEIVE_MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_NO" IS '알림 구분 번호';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_CONTENT" IS '알림 내용';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_CHECK" IS '알림 읽음 여부(N/Y)';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_DATE" IS '알림 생성 시간';

COMMENT ON COLUMN "NOTIFICATION"."NOTIFICATION_URL" IS '알림 클릭 시 연결할 페이지 주소';

COMMENT ON COLUMN "NOTIFICATION"."SEND_MEMBER_NO" IS '알림 보낸 회원 번호';

COMMENT ON COLUMN "NOTIFICATION"."RECEIVE_MEMBER_NO" IS '알림 받는 회원 번호';

ALTER TABLE "NOTIFICATION" ADD CONSTRAINT "PK_NOTIFICATION" PRIMARY KEY (
	"NOTIFICATION_NO"
);


CREATE SEQUENCE SEQ_NOTI_NO NOCACHE;

-- 알림 목록 조회 --

SELECT NOTIFICATION_NO, NOTIFICATION_CONTENT, NOTIFICATION_CHECK,
	NOTIFICATION_URL, SEND_MEMBER_PROFILE_IMG, 
	CASE
			 WHEN CURRENT_DATE - NOTIFICATION_DATE < 1 / 24 / 60 
			 THEN FLOOR((CURRENT_DATE - NOTIFICATION_DATE) * 24 * 60 * 60)  || '초 전'
			 
			 WHEN CURRENT_DATE - NOTIFICATION_DATE < 1 / 24 
			 THEN FLOOR((CURRENT_DATE - NOTIFICATION_DATE)* 24 * 60) || '분 전'
			 
			 WHEN CURRENT_DATE - NOTIFICATION_DATE < 1
			 THEN FLOOR((CURRENT_DATE - NOTIFICATION_DATE) * 24) || '시간 전'
			 
			 ELSE TO_CHAR(NOTIFICATION_DATE, 'YYYY-MM-DD')
		 	
		 END NOTIFICATION_DATE 
FROM "NOTIFICATION"
WHERE RECEIVE_MEMBER_NO = 1
ORDER BY NOTIFICATION_NO DESC
;



SELECT BOARD_TITLE, MEMBER_NO, MEMBER_NICKNAME
FROM "BOARD"
JOIN "MEMBER" USING(MEMBER_NO)
WHERE BOARD_NO = 1501;



-- 읽지 않은 알림 개수
SELECT COUNT(*) 
		FROM "NOTIFICATION"
		WHERE RECEIVE_MEMBER_NO = 1
		AND NOTIFICATION_CHECK = 'N';

SELECT * FROM "COMMENT";


SELECT * FROM "MEMBER";

-- 1번 회원의 권한을 2(관리자)로 수정
UPDATE "MEMBER" SET AUTHORITY = 2
WHERE MEMBER_NO = 1;
COMMIT;




