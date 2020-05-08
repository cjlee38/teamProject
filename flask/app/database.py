import pymysql

class Database():
    def __init__(self):
        self.db = pymysql.connect(host="hufsscheduleservice.cvazzjdw6jxq.ap-northeast-2.rds.amazonaws.com", user="hufs", password="rhtjrgns12", db="innodb", charset="utf8", port=3306)
        self.cursor = self.db.cursor(pymysql.cursors.DictCursor)

    def execute(self, query):
        self.cursor.execute(query)

    def execute_one(self, query):
        self.cursor.execute(query)
        row = self.cursor.fetchone()
        return row

    def execute_all(self, query):
        self.cursor.execute(query)
        row = self.cursor.fetchall()
        return row

    def commit(self):
        self.db.commit()

    def rollback(self):
        self.db.rollback()