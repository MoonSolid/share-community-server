-- 일정 데이터 
insert into sc_plan(plan_id, place, cont, memo, cost)
  values(101, '장소', '내용', '메모', '비용');
insert into sc_plan(plan_id, place, cont, memo, cost)
  values(102, '장소', '내용', '메모', '비용');
insert into sc_plan(plan_id, place, cont, memo, cost)
  values(103, '장소', '내용', '메모', '비용');
  



-- 회원  데이터
insert into sc_member(member_id, name, email, pwd) 
  values(11, 'user1', 'user1@test.com', password('1111'));
insert into sc_member(member_id, name, email, pwd) 
  values(12, 'user2', 'user2@test.com', password('1111'));
insert into sc_member(member_id, name, email, pwd) 
  values(13, 'user3', 'user3@test.com', password('1111'));

-- 게시물 데이터
insert into sc_board(board_id, conts) values(1, '내용1');
insert into sc_board(board_id, conts) values(2, '내용2');
insert into sc_board(board_id, conts) values(3, '내용3');
insert into sc_board(board_id, conts) values(4, '내용4');
insert into sc_board(board_id, conts) values(5, '내용5');

-- 일정 사진 게시글 데이터
insert into sc_photo(photo_id, plan_id, titl) 
  values(1, 101, '내용1');
insert into sc_photo(photo_id, plan_id, titl) 
  values(2, 101, '내용2');
insert into sc_photo(photo_id, plan_id, titl) 
  values(3, 101, '내용3');
insert into sc_photo(photo_id, plan_id, titl) 
  values(4, 104, '내용4');
insert into sc_photo(photo_id, plan_id, titl) 
  values(5, 104, '내용5');

-- 일정 사진 게시물 첨부 파일 데이터
insert into sc_photo_file(photo_file_id, photo_id, file_path)
  values(1, 1, 'a1.gif');
insert into sc_photo_file(photo_file_id, photo_id, file_path)
  values(2, 1, 'a2.gif');
insert into sc_photo_file(photo_file_id, photo_id, file_path)
  values(3, 1, 'a3.gif');

