from flask import Flask, jsonify, session, redirect, url_for
from flask_restful import Resource, Api
from flask_restful import reqparse
import database
from course_table import *
from user_table import *
from flask_cors import CORS
import json

app = Flask(__name__)
CORS(app)
api = Api(app)
app.secret_key = "super secret key"

class RegistUser(Resource):
    def get(self):
        #parser = reqparse.RequestParser()
        #parser.add_argument('name', type=str)
        #parser.add_argument('email', type=str)
        #args = parser.parse_args()

        #name = args['name']
        #email = args['email']

        db_class = database.Database()
        sql = "SELECT * FROM user"
        row = db_class.execute_all(sql)
        row = "" if not row else row
        print(row)

    def post(self):
        db_class = database.Database()
        try:
            parser = reqparse.RequestParser()
            parser.add_argument('std_num', type=str)
            parser.add_argument('password', type=str)
            args = parser.parse_args()

            _std_num = args['std_num']
            _Password = args['password']
            sql_user_search = """SELECT user_id FROM user WHERE student_number=\"{std_num}\";""".format(std_num=_std_num)
            row = db_class.execute_all(sql_user_search)
            row = "" if not row else row
            driver = None
            if len(row):
                print(row)
                user_id = row[0]['user_id']
                sql_liberal_delete= """DELETE FROM liberal_art WHERE user={user_id};""".format(user_id=user_id)
                db_class.execute(sql_liberal_delete)

                sql_course_delete = """DELETE FROM course WHERE user_course={user_id};""".format(user_id=user_id)
                db_class.execute(sql_course_delete)
                # while True:
                # try:
                driver = user_Table(_std_num,_Password, user_id, db_class)
                if driver:
                    # driver[1].close()
                    raise driver[0]
                db_class.commit()
                return {'status': 'success'}


                # except Exception as e:
                #     raise e

            else:
                raise Exception('Check your ID')
           
        except Exception as e:
            print(e)
            # driver.driver.close()
            db_class.rollback()
            return {'error': str(e)}

class ReigstInst(Resource):
    def post(self):
        db_class = database.Database()
        parser = reqparse.RequestParser()
        # parser.add_argument('rq_year', type=str)
        # parser.add_argument('rq_sem', type=str)
        # args = parser.parse_args()

        # rq_year = args['rq_year']
        # rq_sem = args['rq_sem']
        # crawl_Table('14', '1', db_class)
        # crawl_Table('14', '2', db_class)

        # crawl_Table('15', '1', db_class)
        # crawl_Table('15', '2', db_class)

        # crawl_Table('16', '1', db_class)
        # crawl_Table('16', '2', db_class)

        # crawl_Table('17', '1', db_class)
        # crawl_Table('17', '2', db_class)

        # crawl_Table('18', '1', db_class)
        # crawl_Table('18', '2', db_class)

        # crawl_Table('19', '1', db_class)
        # crawl_Table('19', '2', db_class)

        crawl_Table('20', '1', db_class)

        return {'status': 'success'}

    def get(self):
        print(session)
        if session.get('session_key'):
            key = session['session_key']
            
        
        else:
            session['session_key'] = key
            
            print(session)

            db_class = database.Database()

            sql_inst_search = """SELECT instruction_id, dept, area, year, subject, url, required, professor, time,
            credit, class_time, number_of_people, note FROM instruction WHERE rq_year={rq_year} and rq_semester={rq_semester};"""
            sql_inst_search = sql_inst_search.format(rq_year=20, rq_semester=1)
            row = db_class.execute_all(sql_inst_search)
            sql_lib_area = """SELECT distinct area FROM instruction where rq_year={rq_year} and rq_semester={rq_semester} and not (area like \"%전공\" or area like \"인문학공통\" or area like \"%이중%\" or area like \"교직\");
    """.format(rq_year=20, rq_semester=1)
            area =  db_class.execute_all(sql_lib_area)
            if len(row):
                return {'data' : row, 'lib' : area}
            else:
                return {'error' : '강의가 없습니다.'}



api.add_resource(RegistUser, '/user_profile')
api.add_resource(ReigstInst, '/inst_update')


if __name__ == '__main__':
    app.run(debug=True)

