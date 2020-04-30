from flask import Flask, jsonify
from flask_restful import Resource, Api
from flask_restful import reqparse
import database
from course_table import *
from user_table import *

app = Flask(__name__)
api = Api(app)


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
            # sql_user_search = """SELECT user_id FROM user WHERE student_number=\"{std_num}\";""".format(std_num='test3')
            row = db_class.execute_all(sql_user_search)
            row = "" if not row else row
            if len(row):
                print(row)
                user_id = row[0]['user_id']
                sql_liberal_delete= """DELETE FROM liberal_art WHERE user={user_id};""".format(user_id=user_id)
                db_class.execute(sql_liberal_delete)

                sql_course_delete = """DELETE FROM course WHERE user_course={user_id};""".format(user_id=user_id)
                db_class.execute(sql_course_delete)
                
                while True:
                    try:
                        user_Table(_std_num,_Password, user_id, db_class)
                        break
                    except NoSuchWindowException:
                        pass
                db_class.commit()

            else:
                raise Exception('아이디를 다시 입력')
           
        except Exception as e:
            return {'error': str(e)}

class ReigstInst(Resource):
    def post(self):
        db_class = database.Database()
        parser = reqparse.RequestParser()
        parser.add_argument('rq_year', type=str)
        parser.add_argument('rq_sem', type=str)
        args = parser.parse_args()

        rq_year = args['rq_year']
        rq_sem = args['rq_sem']
        crawl_Table(rq_year, rq_sem, db_class)

        pass



api.add_resource(RegistUser, '/user_profile')
api.add_resource(ReigstInst, '/inst_update')


if __name__ == '__main__':
    app.run(debug=True)

