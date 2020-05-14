from selenium_class import *
import time
from selenium.webdriver.common.alert import Alert


def get_alert(driver):
    alert = WebDriverWait(driver, 1).until(
                    EC.alert_is_present(
                        ))
    return alert


def user_Table(id_input, pw_input, user_id, db):
    db_class = db
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

    time.sleep(0.5)

    try:
        driver.driver.switch_to.alert.accept()
        driver.get_default()
        driver.driver.close()
        return (Exception("아이디나 비밀번호를 확인해주세요!"), driver.driver)


            # raise Exception("아이디 비번 확인!")
            
    except Exception as e:
        print(e)

  


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
    major_dict = {}

    _foreign = None
    if tds[1].text == '정원외신입 / 특례모집' or '외국인' in tds[1].text:
        _foreign = True
    else:
        _foreign = False
        print('내국인')
    major_dict['재외국민'] = _foreign



    # 성적 취득 현황

    driver.get_default()
    driver.get_fra('left')
    driver.get_fra('MenuFrame')
    grade_tap = driver.find_by_xpath('/html/body/div/div[4]/a[2]')
    driver.click(grade_tap)

    driver.get_fra('body')
    driver.get_fra('top')

    # 위쪽 frame
    print(1)
    time.sleep(1)

    tables = driver.find_all_by_tag('tbody')    # 영역별 취득 학점, 이수중 전공 테이블 2개 담김
    print(tables)
    print(2)
    # 영역별 취득 학점
    domain_dict = {}

    domain_grade = tables[0]
    th = domain_grade.find_elements_by_tag_name('th')    # 영역 이름
    print(3)
    td = domain_grade.find_elements_by_tag_name('td')    # 이수 학점
    print(4)
    for i in range(1, len(th)):
        print(th[i].text)
        print(td[i].text)
        domain_dict[th[i].text] = td[i].text
    
    # 이수 전공

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

    courses_list = []
    global score_year 
    global score_semester

    for i in range(1, len(tr) - 1):
        if len(tr[i].text) != 8:    # 이수 년도, 학기 제외
            temp_list = []
            tds = driver.find_all_by_tag_with_obj(tr[i], 'td')  # 각 교과목 리스트 가져오기
            for i in tds:   # 리스트 별 column에 맞춰서 split
                temp_list.append(i.text.strip())
            if temp_list[5] == "F":
                continue
            courses_list.append(temp_list[1:])

        else:
            if '이수 학기' in major_dict.keys():
                major_dict['이수 학기'] += 1
            else:
                major_dict['이수 학기'] = 1     # 현재 이수 학기는 3-1일때는 4학기, 즉 총 이수학기가 나옴; 만약 현재 이수 학기를 할려면 2로

            score_time = tr[i].text.split()
            score_year = score_time[0][2:]
            score_semester = score_time[2]


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
        if '계' in trs[i].text:   # (소계) 제외
            continue
        tds = driver.find_all_by_tag_with_obj(trs[i], 'td') # 행 요소
        temp_list = []
        
        
        area_name = tds[area].text.split('(')[0]

        number_of_subject = int(tds[count].text)
        acquisition_credits = int(tds[got_credits].text)

        temp_list.append(area_name)    # 행 요소 종합
        temp_list.append(number_of_subject)    # 행 요소 종합
        temp_list.append(acquisition_credits)    # 행 요소 종합
        if len(temp_list):
            credits_list.append(temp_list)  # 교양 영역에 각 행 추가


    # 교직 이수 여부
    driver.get_default()
    driver.get_fra('left')
    driver.get_fra('MenuFrame')

    # 교직 이수 현황 탭 클릭
    teaching_btn = driver.find_by_xpath('/html/body/div/div[2]/a[11]')
    driver.click(teaching_btn)

    # 프레임 이동
    driver.get_fra('body')
    time.sleep(1)
    td1 = driver.find_all_by_tag('td')  # 교직 이수 현황 행 찾기
    print("교직", td1[0].text)
    if td1[0].text == '1':   # 순번이 있다면
        major_dict['교직'] = True
    else:
        major_dict['교직'] = False

    # print(domain_dict)  # credit 테이블 용


    # credit table insert
    val = list(domain_dict.values())
    print(val)
    _first_major = int(val[0])
    _second_major = int(val[1])
    _sub_major = int(val[2])
    _outdoor = int(val[3])
    _liberal_arts = int(val[4])
    _minor = int(val[5])
    _teaching = int(val[6])
    _optional = int(val[7])
    _total_credit = int(val[8])
    _average_score = float(val[9])

    sql_credit_search = """SELECT credit_id FROM credit WHERE user_credit={user_id};""".format(user_id=user_id)
    row = db_class.execute_all(sql_credit_search) 
    row = "" if not row else row
    if len(row):
        # 이미 credit row 존재하니깐 update문으로
        sql_credit_update = """UPDATE credit SET  
    first_major={first_major}, liberal_arts={liberal_arts}, minor={minor}, optional={optional}, out_door={out_door}, second_major={second_major}, sub_major={sub_major},
    teaching={teaching}, total_credit={total_credit}, average_score={average_score} WHERE user_credit={user_id};"""
        sql_credit_update = sql_credit_update.format(first_major=_first_major, liberal_arts=_liberal_arts, minor=_minor, optional=_optional,
    out_door=_outdoor, second_major=_second_major, sub_major=_sub_major, teaching=_teaching, total_credit=_total_credit, average_score=_average_score, user_id=user_id)
        db_class.execute_all(sql_credit_update)
    else:
        # 새로운 credit 생성, insert문으로
        sql_credit_insert = """INSERT INTO credit 
    (first_major, liberal_arts, minor, optional, out_door, second_major, sub_major,
    teaching, total_credit, average_score, user_credit) 
    VALUES ({first_major}, {liberal_arts}, {minor}, {optional}, {out_door}, {second_major}, {sub_major},
    {teaching}, {total_credit}, {average_score}, {user_id});"""
        sql_credit_insert = sql_credit_insert.format(first_major=_first_major, liberal_arts=_liberal_arts, minor=_minor, optional=_optional,
    out_door=_outdoor, second_major=_second_major, sub_major=_sub_major, teaching=_teaching, total_credit=_total_credit, average_score=_average_score, user_id=user_id)
        db_class.execute_all(sql_credit_insert)


    # user table insert
    user_year = (major_dict['이수 학기'] // 2) + 1

    intensive = False
    keys = list(major_dict.keys())
    if (user_year != 1 and len(keys) == 1) or '전공심화' in keys[1]:
        intensive = True

    if '이중전공' in keys:
        sql_user_insert ="""  UPDATE user SET major=\"{major}\", second_major=\"{second_major}\", year={year}, foreigner={foreigner}, teaching={teaching}, intensive_major={intensive}
    WHERE user_id={user_id};"""
        sql_user_insert = sql_user_insert.format(major=major_dict['1전공'], second_major=major_dict['이중전공'], year = user_year, foreigner=major_dict['재외국민'], teaching=major_dict['교직'], intensive=intensive, user_id=user_id)

    elif '전공심화(부전공)' in keys or '부전공' in keys:
        minor = None
        if intensive:
            minor = major_dict['전공심화(부전공)']
        else:
            minor = major_dict['부전공']
        sql_user_insert = """  UPDATE user SET major=\"{major}\", minor=\"{minor}\", year={year}, foreigner={foreigner}, teaching={teaching}, intensive_major={intensive}
    WHERE user_id={user_id};"""
        sql_user_insert = sql_user_insert.format(major=major_dict['1전공'], minor=minor, year = user_year, foreigner=major_dict['재외국민'], teaching=major_dict['교직'], intensive=intensive, user_id=user_id)
    else:
        sql_user_insert = """  UPDATE user SET major=\"{major}\", year={year}, foreigner={foreigner}, teaching={teaching}, intensive_major={intensive}
    WHERE user_id={user_id};"""
        sql_user_insert = sql_user_insert.format(major=major_dict['1전공'], year = user_year, foreigner=major_dict['재외국민'], teaching=major_dict['교직'], intensive=intensive, user_id=user_id)

    db_class.execute(sql_user_insert)

    print(major_dict)   # user 테이블용


    # course table insert

    for i in courses_list:
        inst_num = i[0]
        course_area = i[2]
        sql_inst_search = """SELECT instruction_id FROM instruction WHERE instruction_number LIKE \"{inst_num}%\" and subject LIKE \"%%{sub}%%\";""".format(inst_num=inst_num, sub=i[1].split('(')[0])
        inst_id = db_class.execute_all(sql_inst_search)
        inst_id = inst_id[0]['instruction_id']
        
        sql_course_insert = """INSERT INTO course (course_inst_num, user_course, course_area) VALUES ({course_inst_num}, {user_id}, \"{course_area}\");""".format(course_inst_num=inst_id, user_id=user_id, course_area = course_area)
        db_class.execute(sql_course_insert)
    
    print(courses_list)  # course 테이블용


    # liberal_art table insert

    for i in credits_list:
        area = i[0]
        count = int(i[1])
        credit = int(i[2])
        sql_lib_insert = """
        INSERT INTO liberal_art 
            (acqusition_credits, area, number_of_subject, user) 
            VALUES ({acqusition_credits}, \"{area}\", {number_of_subject}, {user_id});
        """
        sql_lib_insert = sql_lib_insert.format(acqusition_credits=credit, area=area, number_of_subject=count, user_id=user_id)
        db_class.execute(sql_lib_insert)


    # print(credits_list)  # liberal_art 테이블용



    # 창 종료
    print('FINISH')
    driver.driver.quit()

