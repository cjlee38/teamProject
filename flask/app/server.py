from flask import Flask, jsonify
from flask_restful import Resource, Api
from flask_restful import reqparse
import database
from course_table import *

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

class ReigstInst(Resource):
    def insert(self):
        db_class = database.Database()
        sql = "INSERT INTO instruction VALUES ()"

    def get(self):
        db_class = database.Database()
        # sql = "SELECT * FROM instruction WHERE instruction_number={inst}".format(inst=inst_num)
        # sql = "SELECT * FROM instruction WHERE instruction_number='D03310101'"
        # row = db_class.execute_all(sql)
        # row = "" if not row else row
        # print(row)    
        crawl_Table('14', '1', db_class)
        pass



api.add_resource(RegistUser, '/user')
api.add_resource(ReigstInst, '/inst_update')


if __name__ == '__main__':
    app.run(debug=True)

