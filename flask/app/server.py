from flask import Flask, jsonify
from flask_restful import Resource, Api
from flask_restful import reqparse
from app import database

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


api.add_resource(RegistUser, '/user')

if __name__ == '__main__':
    app.run(debug=True)
