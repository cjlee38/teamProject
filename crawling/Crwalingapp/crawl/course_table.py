from .selenium_class import *
from selenium.webdriver.support.ui import Select
from bs4 import BeautifulSoup as bs
from .models import Insturction
import time


def crawl_Table(rq_year, rq_semester):
    driver = Driver()

    driver.get_url('https://wis.hufs.ac.kr/src08/jsp/lecture/LECTURE2020L.jsp') # 여기 URL 년도 언제 바뀌는지 알아야할 것 같은데

    semester_trans = {'1': '1', '2': '3', '여름': '2', '겨울': '4'}

    # rq_year, rq_semester = input('년도/학기 선택(ex : 20-1) : ').split('-')

    # 년도 select
    year = driver.find_by_name('ag_ledg_year')
    year_obj = Select(year)
    year_obj.select_by_value('20' + rq_year)

    # 학기 select
    semester = driver.find_by_name('ag_ledg_sessn')
    semester_obj = Select(semester)
    semester_obj.select_by_value(semester_trans[rq_semester])

    # 전공 리스트 추출
    dept_list = []
    dept_eles = driver.find_by_name('ag_crs_strct_cd')
    for i in driver.find_all_by_tag_with_obj(dept_eles, 'option'):
        dept_list.append(i.text.strip()[6:].split('(')[0].strip())

    dept_eles = Select(dept_eles)

    for k in range(len(dept_list)):
        dept_eles.select_by_index(k)

        dept_eles = driver.find_by_name('ag_crs_strct_cd')
        dept_eles = Select(dept_eles)

        html = bs(driver().page_source, 'html.parser')

        tbody = html.findAll('table')
        
        trs = tbody[-1].findAll('tr')[1:]

        for i in trs:
            tds = i.findAll('td')
            area = tds[1].get_text().strip()
            year = int(tds[2].get_text().strip())
            course_num = tds[3].get_text().strip()

            subject_name = tds[4].get_text().strip().splitlines()
            subject_name = " ".join(subject_name)    # 영어도 함께

            try:
                syllabus = tds[5].find('a')['href'].split('\'')
                ag_1 = syllabus[1]
                ag_2 = syllabus[3]
                ag_3 = syllabus[5]
                ag_4 = syllabus[7]
                syllabus = "https://wis.hufs.ac.kr/src08/jsp/lecture/syllabus.jsp?mode=print&ledg_year=" \
                        + str(ag_1) + "&ledg_sessn=" + str(ag_2) + "&org_sect=" + str(ag_3) + "&lssn_cd=" + str(ag_4)
            except:
                syllabus = 'None'

            required = tds[6]
            if required.find('img'):
                required = True
            else:
                required = False

            # online = tds[7]
            # if online.find('img'):
            #     online = 'O'
            # else:
            #     online = online.get_text()

            # foreign = tds[9]
            # if foreign.find('img'):
            #     foreign = 'O'
            # else:
            #     foreign = foreign.get_text()

            # team_teaching = tds[10]
            # if team_teaching.find('img'):
            #     team_teaching = 'O'
            # else:
            #     team_teaching = team_teaching.get_text()

            prof = tds[11].get_text().strip().split('(')[0].strip()
            if len(prof) == 0:
                continue

            credit = int(tds[12].get_text().strip())
            time2 = int(tds[13].get_text().strip())

            class_time = tds[14].get_text().split('(')[0].split()
            class_time = "".join(class_time)
            restrict_num = tds[15].get_text().split('/')[1].strip()
            note = tds[16].get_text().strip()
            try:
                obj = Insturction.objects.filter(instruction_number = course_num)
                if len(obj):
                    obj.area = area
                    obj.year = year
                    obj.subject = subject_name
                    obj.url = syllabus
                    obj.required = required
                    obj.number_of_people = restrict_num
                    obj.note = note
                    obj.time = time2
                    obj.professor = prof
                    obj.credit = credit
                    obj.dept = dept_list[k]
                else:
                    Insturction(area=area, year=year, instruction_number=course_num, subject=subject_name, url=syllabus, required = required, class_time=class_time,
                    number_of_people=restrict_num, note=note, time = time2, professor=prof, credit=credit, dept=dept_list[k]).save()

            except Exception as err:
                print(err)
                print(subject_name, prof, class_time)

    # 교양
    button = driver.find_by_xpath('/html/body/div/form/div[1]/table/tbody/tr[5]/th/input')
    time.sleep(0.5)
    driver.click(button)

    lib_list = []
    lib_eles = driver.find_by_name('ag_compt_fld_cd')
    for i in driver.find_all_by_tag_with_obj(lib_eles, 'option'):
        lib_list.append(i.text.strip().split('(')[0])

    lib_eles = Select(lib_eles)

    for k in range(len(lib_list)):
        lib_eles.select_by_index(k)

        lib_eles = driver.find_by_name('ag_compt_fld_cd')
        lib_eles = Select(lib_eles)

        html = bs(driver.driver.page_source, 'html.parser')

        tbody = html.findAll('tbody')
        trs = tbody[-1].findAll('tr')[1:]

        for i in trs:
            tds = i.findAll('td')
            
            area = tds[1].get_text().strip()
            year = None
            try:
                year = int(tds[2].get_text().strip())
            except:
                year = None
            course_num = tds[3].get_text().strip()

            subject_name = tds[4].get_text().strip().splitlines()
            subject_name = " ".join(subject_name)    # 영어도 함께

            try:
                syllabus = tds[5].find('a')['href'].split('\'')
                ag_1 = syllabus[1]
                ag_2 = syllabus[3]
                ag_3 = syllabus[5]
                ag_4 = syllabus[7]
                syllabus = "https://wis.hufs.ac.kr/src08/jsp/lecture/syllabus.jsp?mode=print&ledg_year=" \
                        + str(ag_1) + "&ledg_sessn=" + str(ag_2) + "&org_sect=" + str(ag_3) + "&lssn_cd=" + str(ag_4)
            except:
                syllabus = 'None'
            required = tds[6]
            if required.find('img'):
                required = True
            else:
                required = False
                
            prof = tds[11].get_text().strip().split('(')[0].strip()
            if len(prof) == 0:
                continue

            credit = int(tds[12].get_text().strip())
            time2 = int(tds[13].get_text().strip())

            class_time = tds[14].get_text().split('(')[0].split()
            class_time = "".join(class_time)
            restrict_num = tds[15].get_text().split('/')[1].strip()
            note = tds[16].get_text().strip()
            try:
                obj = Insturction.objects.filter(instruction_number = course_num)
                if len(obj):
                    obj.area = area
                    obj.year = year
                    obj.subject = subject_name
                    obj.url = syllabus
                    obj.required = required
                    obj.number_of_people = restrict_num
                    obj.note = note
                    obj.time = time2
                    obj.professor = prof
                    obj.credit = credit
                    obj.dept = dept_list[k]
                else:
                    Insturction(area=area, year=year, instruction_number=course_num, subject=subject_name, url=syllabus, required = required, class_time=class_time,
                    number_of_people=restrict_num, note=note, time = time2, professor=prof, credit=credit, dept='교양').save()
            except Exception as err:
                print(err)
                print(subject_name, prof, class_time)

    driver.driver.quit()
