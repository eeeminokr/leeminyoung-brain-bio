- 대상자관리(csbr1010)
* 질본청검사지 mb_fmh 가족력  테이블 생성 


-대상자 관리 
  선별검사,정밀검사 레이아웃 및 쿼리 조회 부분 합병
  
  
 선별검진 / 정밀검진  mb_etccd 기존 있는 값들 DESC3 보류값으로 지정 
 선별검진 / 정밀검진 조회시 DESC3으로 해당값 조회 예정 
  
  참여동의 테이블 mb_ic
  흡연 테이블 mb_sm
 음주 테이블 mb_dr dr_2_05_a~s  social history A.2번문제 부분 컬럼 추가 SELECT태그 OPTION태그로 생성해야함
 신체활동 테이블 mb_pha 
수면 테이블 mb_sp 설계완성
삶의질 테이블 mb_qol 설계완성
K-MMSE2 mb_kmmse2 설계완성
신체계측 및 활력징후 mb_pe 설계완성

질병력(mb_mh) 
컬럼추가
mh_new04_a_p
mh_new04_b_p
mh_new04_c_p
mh_new04_d_p
mh_new04_a_n
mh_new04_b_n
mh_new04_c_n
mh_new04_d_n

gads name값넣고 value값넣기 앞단
+-++


/* 2022년 08월 10일  th.kim 
     요구사항 : 대상자관리탭에서 비고란에 데이터 입력시 저장되는 csbrain2.mb.opinion 테이블에서 사용하는 컬럼(remark)의 데이터타입 사이즈가 varchar(20)이여서 이후에 데이터 입력시 저장되지 않는다.
     원인과 해결: mb.opinion 테이블에서 대상자 정보 조회시 하나의 행에서 insert_date의 값이 0000-00-00 으로 들어가 있었다. MySQL에서 제공하는 datetime의 제공범위는 1000-01-01 ~ 9999-12-31 까지이므로 
                기존 0000-00-00 일때는 remark 컬럼의 데이터타입 사이즈를 변경할 수 없었다. 그래서 제공범위에 맞게 값을 수정하니 그때서야 remark의 데이터타입 사이즈를 변경할 수 있었다.
     비고 : 현재는 CSBRAIN2 (개발) DB에만 반영되었기때문에 향후에 CSBRAIN2 (운영) DB도 수정이 필요하다. */

 
   