from selenium_class import *
from selenium.webdriver.support.ui import Select
from bs4 import BeautifulSoup as bs
# from .models import Instruction
import time


def crawl_Table(rq_year, rq_semester, db):
    db_class = db


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
        if k == 0:
            cli_btn = driver.find_by_name("btnSearch")
            driver.click(cli_btn)

        dept_eles = driver.find_by_name('ag_crs_strct_cd')
        dept_eles = Select(dept_eles)

        html = bs(driver().page_source, 'html.parser')

        tbody = html.findAll('table')
        
        trs = tbody[-1].findAll('tr')[1:]

        for i in trs:
            sql_insert = """INSERT INTO instruction 
    (area, class_time, credit, dept, instruction_number, note, number_of_people,
    professor, required, rq_semester, rq_year, subject, time, url, year) 
    VALUES (\"{area}\", \"{class_time}\", {credit}, \"{dept}\", \"{instruction_number}\", \"{note}\", \"{number_of_people}\",
    \"{professor}\", {required}, {rq_semester}, {rq_year}, \"{subject}\", {time}, \"{url}\", {year});"""

            sql_search = """SELECT * FROM instruction WHERE instruction_number=\"{inst_num}\";"""

            sql_update = """UPDATE instruction SET area=\"{area}\", class_time=\"{class_time}\", credit={credit}, dept=\"{dept}\", note=\"{note}\", number_of_people=\"{number_of_people}\",
            professor=\"{professor}\", required={required}, rq_semester={rq_semester}, rq_year={rq_year}, subject=\"{subject}\", time={time}, url=\"{url}\", year={year} 
            WHERE instruction_number=\"{inst}\";"""

            tds = i.findAll('td')
            area = tds[1].get_text().strip()
            if area == "전공":
                area = "1" + area
            year = int(tds[2].get_text().strip())
            course_num = tds[3].get_text().strip()

            subject_name = tds[4].get_text().strip().splitlines()[0].split(' (')
            subject_name = subject_name[0]    
            print(subject_name)
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
                prof=""

            credit = int(tds[12].get_text().strip())
            time2 = int(tds[13].get_text().strip())

            class_time = tds[14].get_text().split('(')[0].split()
            class_time = "".join(class_time)
            restrict_num = tds[15].get_text().split('/')[1].strip()
            note = tds[16].get_text().strip().replace("\"", "\'")
            try:
                sql_search = sql_search.format(inst_num=course_num)
                # print(sql_search)

                searched_row = db_class.execute_all(sql_search)
                searched_row = "" if not searched_row else searched_row

                # obj = Instruction.objects.filter(instruction_number = course_num)
                if len(searched_row):
                    sql_update = sql_update.format(inst=course_num, area=area, year=year, subject=subject_name, url=syllabus, required = required, class_time=class_time,
                    number_of_people=restrict_num, note=note, time = time2, professor=prof, credit=credit, dept=dept_list[k], rq_year = int(rq_year), rq_semester = int(rq_semester))
                    update = db_class.execute_all(sql_update)
                    # print(update)
                else:
                    sql_insert = sql_insert.format(area=area, year=year, instruction_number=course_num, subject=subject_name, url=syllabus, required = required, class_time=class_time,
                    number_of_people=restrict_num, note=note, time = time2, professor=prof, credit=credit, dept=dept_list[k], rq_year = int(rq_year), rq_semester = int(rq_semester))
                    insert_row = db_class.execute_all(sql_insert)
                    # print(sql_insert)
                    # db.commit()
                    # Instruction(area=area, year=year, instruction_number=course_num, subject=subject_name, url=syllabus, required = required, class_time=class_time,
                    # number_of_people=restrict_num, note=note, time = time2, professor=prof, credit=credit, dept=dept_list[k], rq_year = rq_year, rq_semester = rq_semester).save()

            except Exception as err:
                print(err)
                print(subject_name, prof, class_time)
                pass

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
        if k == 0:
            cli_btn = driver.find_by_name("btnSearch")
            driver.click(cli_btn)

        lib_eles = driver.find_by_name('ag_compt_fld_cd')
        lib_eles = Select(lib_eles)

        html = bs(driver.driver.page_source, 'html.parser')

        tbody = html.findAll('tbody')
        trs = tbody[-1].findAll('tr')[1:]

        for i in trs:

            sql_insert = """INSERT INTO instruction 
    (area, class_time, credit, dept, instruction_number, note, number_of_people,
    professor, required, rq_semester, rq_year, subject, time, url, year) 
    VALUES (\"{area}\", \"{class_time}\", {credit}, \"{dept}\", \"{instruction_number}\", \"{note}\", \"{number_of_people}\",
    \"{professor}\", {required}, {rq_semester}, {rq_year}, \"{subject}\", {time}, \"{url}\", {year});"""


            sql_search = """SELECT * FROM instruction WHERE instruction_number=\"{inst_num}\";"""

            sql_update = """UPDATE instruction SET area=\"{area}\", class_time=\"{class_time}\", credit={credit}, dept=\"{dept}\", note=\"{note}\", number_of_people=\"{number_of_people}\",
            professor=\"{professor}\", required={required}, rq_semester={rq_semester}, rq_year={rq_year}, subject=\"{subject}\", time={time}, url=\"{url}\", year={year} 
            WHERE instruction_number=\"{inst}\";"""


            tds = i.findAll('td')
            
            area = lib_list[k]
            year = None
            try:
                year = int(tds[2].get_text().strip())
            except:
                year = "NULL"
            course_num = tds[3].get_text().strip()

            subject_name = tds[4].get_text().strip().splitlines()[0].split(' (')
            subject_name = subject_name[0] 

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
                sql_search = sql_search.format(inst_num=course_num)
                searched_row = db_class.execute_all(sql_search)
                searched_row = "" if not searched_row else searched_row

                # obj = Instruction.objects.filter(instruction_number = course_num)
                if len(searched_row):
                    sql_update = sql_update.format(inst=course_num, area=area, year=year, subject=subject_name, url=syllabus, required = required, class_time=class_time,
                    number_of_people=restrict_num, note=note, time = time2, professor=prof, credit=credit, dept='교양', rq_year = int(rq_year), rq_semester = int(rq_semester))
                    update = db_class.execute_all(sql_update)
                    # print(sql_update)
                else:

                    sql_insert = sql_insert.format(area=area, year=year, instruction_number=course_num, subject=subject_name, url=syllabus, required = required, class_time=class_time,
                    number_of_people=restrict_num, note=note, time = time2, professor=prof, credit=credit, dept='교양', rq_year = int(rq_year), rq_semester = int(rq_semester))

                    insert_row = db_class.execute_all(sql_insert)
                    
            except Exception as err:
                print(err)
                print(subject_name, prof, class_time)
                pass

    db.commit()
    driver.driver.quit()
    
