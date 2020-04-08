from .selenium_class import *
from .models import User, Credit

def user_Table(id_input, pw_input):
    
    driver = Driver()
    driver.get_url("https://wis.hufs.ac.kr/src08/jsp/index.jsp")    # 종합정보시스템 페이지 로딩


    # ID 입력
    id_box = driver.find_by_name('user_id')
    id_box.send_keys(id_input)
    # PW 입력
    pw_box = driver.find_by_name('password')
    pw_box.send_keys(pw_input)
    # 로그인
    login_btn = driver.find_by_xpath('/html/body/div/form[3]/div[2]/div/div[2]/div/a')
    driver.click(login_btn)




    # 비밀번호 변경 안내 예외 처리
    try:
        chg_btn = driver.find_by_class('gray')
        driver.click(chg_btn)

    except:
        pass



    # 왼쪽 메뉴 리스트 frame으로 옮기기
    driver.get_fra('left')
    driver.get_fra('MenuFrame')

    ent_info = driver.find_by_xpath('/html/body/div/div[1]/a[1]')
    driver.click(ent_info)

    driver.get_fra('body')

    ent = driver.find_by_link('입학')
    driver.click(ent)

    driver.get_fra('ifr_tab1')

    tds = driver.find_all_by_tag('td')
    if tds[1].text == '정원외신입 / 특례모집' or '외국인' in tds[1].text:
        print('재외국민')
    else:
        print('내국인')



    # 성적 취득 현황

    driver.get_default()
    driver.get_fra('body')
    driver.get_fra('list')
    grade_tap = driver.find_by_xpath('/html/body/div/div[4]/a[2]')
    driver.click(grade_tap)

    driver.get_fra('body')
    driver.get_fra('top')

    # 위쪽 frame

    tables = driver.find_all_by_tag('table')    # 영역별 취득 학점, 이수중 전공 테이블 2개 담김

    # 영역별 취득 학점
    domain_dict = {}

    domain_grade = tables[0]
    th = driver.find_all_by_tag_with_obj(domain_grade, 'th')    # 영역 이름
    td = driver.find_all_by_tag_with_obj(domain_grade, 'td')    # 이수 학점

    for i in range(len(th)):
        domain_dict[th[i].text] = td[i].text

    print(domain_dict)

    
    # 이수 전공
    major_dict = {}

    domain_grade = tables[1]
    td = driver.find_by_tag_with_obj(domain_grade, 'td')    # 이수 전공
    major_list = td.text.split('\n')    # 1전공, 이중/부전공 분리

    for i in major_list:    # major_dict에 저장
        text = i.split()
        major_dict[text[1].strip('[]')] = text[2]


    # 아래쪽 frame
    # 년도/학기별 취득 성적
    driver.get_default()
    driver.get_fra('body')
    driver.get_fra('list')

    tables = driver.find_all_by_tag('table')[1]    # 년도/학기별 취득 성적 테이블

    tr = driver.find_all_by_tag_with_obj(tables, 'tr')  # 테이블 요소
    columns = tr[0].text.split()    # columns 구분

    courses_list = []

    for i in range(1, len(tr) - 1):
        if len(tr[i].text) != 8:    # 이수 년도, 학기 제외
            temp_list = []
            tds = driver.find_all_by_tag_with_obj(tr[i], 'td')  # 각 교과목 리스트 가져오기
            for i in tds:   # 리스트 별 column에 맞춰서 split
                temp_list.append(i.text.strip())
            courses_list.append(temp_list[1:])  # 수강 과목 리스트에 각 과목 추가
        else:
            if '이수 학기' in major_dict.keys():
                major_dict['이수 학기'] += 1
            else:
                major_dict['이수 학기'] = 1     # 현재 이수 학기는 3-1일때는 4학기, 즉 총 이수학기가 나옴; 만약 현재 이수 학기를 할려면 2로

            score_time = tr[i].text.split()
            year = score_time[0][2:]
            semester = score_time[1]

    # 유저 데이터 프레임 생성
    # user_df = pd.DataFrame(courses_list, columns=columns[1:])
    # # pd.set_option('display.max_columns', 500)
    # user_df.set_index(columns[1], inplace=True)

    print(major_dict)
    print(courses_list)
    # print(user_df)




    # 교양 영역별 취득 현황
    driver.get_default()
    driver.get_fra('left')
    driver.get_fra('MenuFrame')

    # 프레임 페이지 변화
    liberal_tap = driver.find_by_xpath('/html/body/div/div[4]/a[3]')
    driver.click(liberal_tap)
    driver.get_fra('body')


    tables = driver.find_by_tag('table')    # 취득 현황 테이블

    trs = driver.find_all_by_tag_with_obj(tables, 'tr')     # 테이블 요소
    columns = trs[0].text.split()   # columns 구분

    area = columns.index('교양영역') - 2
    count = columns.index('취득과목수') - 2
    got_credits = columns.index('취득학점') - 2

    credits_list = []   # 교양 영역 담길 리스트

    for i in range(1, len(trs) - 1):    # 각 교양 행 탐색
        tds = driver.find_all_by_tag_with_obj(trs[i], 'td') # 행 요소
        temp_list = []
        if '(' in tds[area].text:   # (소계) 제외
            continue
        temp_list.append(tds[area].text)    # 행 요소 종합
        temp_list.append(tds[count].text)    # 행 요소 종합
        temp_list.append(tds[got_credits].text)    # 행 요소 종합
        if len(temp_list):
            credits_list.append(temp_list)  # 교양 영역에 각 행 추가
    print(credits_list)


    # 교직 이수 여부
    driver.get_default()
    driver.get_fra('left')
    driver.get_fra('MenuFrame')

    # 교직 이수 현황 탭 클릭
    teaching_btn = driver.find_by_xpath('/html/body/div/div[2]/a[11]')
    driver.click(teaching_btn)

    # 프레임 이동
    driver.get_fra('body')

    td = driver.find_all_by_tag('td')  # 교직 이수 현황 행 찾기

    if td[0].text == '1':   # 순번이 있다면
        print('교직 이수 중')
    else:
        print('교직 이수 x')

    # 창 종료
    driver.driver.quit()

