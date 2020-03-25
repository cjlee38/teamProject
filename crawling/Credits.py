from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import pandas as pd


class Driver:
    def __init__(self):
        options = webdriver.ChromeOptions()
        options.add_argument('headless')
        options.add_argument(
            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")

        self.driver = webdriver.Chrome(ChromeDriverManager().install(), options=options)

    def __call__(self):
        return self.driver

    def get_url(self, url): # 새 창으로 url 열기
        self.driver.get(url)

    def get_default(self):
        while True:
            try:
                self.driver.switch_to_default_content()
                return
            except:
                print('default frame 이동')
                pass

    def get_fra(self, name):
        while True:
            try:
                self.driver.switch_to_frame(name)
                break
            except:
                self.get_default()
                print(name, 'frame 이동')
                continue

    def find_by_xpath(self, xpath): # Xpath로 단일 요소 찾기
        return WebDriverWait(self.driver, 5).until(
                        EC.presence_of_element_located(
                            (By.XPATH, xpath)))

    def find_by_class(self, class_name): # class name으로 단일 요소 찾기
        return WebDriverWait(self.driver, 5).until(
                        EC.presence_of_element_located(
                            (By.CLASS_NAME, class_name)))

    def find_by_tag(self, tag): # tag로 단일 요소 찾기
        return WebDriverWait(self.driver, 5).until(
            EC.presence_of_element_located(
                (By.TAG_NAME, tag)))


    def find_by_name(self, name): # name으로 단일 요소 찾기
        return WebDriverWait(self.driver, 5).until(
            EC.presence_of_element_located(
                (By.NAME, name)))

    def find_all_by_class(self, class_name): # class name으로 모든 요소 찾기
        return WebDriverWait(self.driver, 5).until(
            EC.presence_of_all_elements_located(
                (By.TAG_NAME, class_name)))

    def find_all_by_tag(self, tag): # tag로 모든 요소 찾기
        return WebDriverWait(self.driver, 5).until(
            EC.presence_of_all_elements_located(
                (By.TAG_NAME, tag)))

    def find_all_by_name(self, name): # name으로 모든 요소 찾기
        return WebDriverWait(self.driver, 5).until(
            EC.presence_of_all_elements_located(
                (By.NAME, name)))

    def find_all_by_tag_with_obj(self, obj, name): # name으로 모든 요소 찾기
        return WebDriverWait(obj, 5).until(
            EC.presence_of_all_elements_located(
                (By.TAG_NAME, name)))

    def find_by_tag_with_obj(self, obj, name): # name으로 모든 요소 찾기
        return WebDriverWait(obj, 5).until(
            EC.presence_of_element_located(
                (By.TAG_NAME, name)))

    def click(self, btn):
        self.driver.execute_script("arguments[0].click();", btn)



id_input = input('종합정보시스템 ID를 입력해주세요 : ')
pw_input = input('비밀번호를 입력해주세요 : ')

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




# 성적 취득 현황
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
    major_dict[text[1].strip('[]')] = ' '.join(text[2:])


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

# 유저 데이터 프레임 생성
user_df = pd.DataFrame(courses_list, columns=columns[1:])
# pd.set_option('display.max_columns', 500)
user_df.set_index(columns[1], inplace=True)

print(major_dict)

print(user_df)




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

credits_list = []   # 교양 영역 담길 리스트

for i in range(1, len(trs) - 1):    # 각 교양 행 탐색
    tds = driver.find_all_by_tag_with_obj(trs[i], 'td') # 행 요소
    temp_list = []

    # 행 요소 탐색
    for k in tds[:3]:
        if '(' in k.text:   # (소계) 제외
            continue
        temp_list.append(k.text)    # 행 요소 종합
    credits_list.append(temp_list)  # 교양 영역에 각 행 추가

# driver.driver.quit()

user_df = pd.DataFrame(credits_list, columns=columns[2:5])
print(user_df)


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

