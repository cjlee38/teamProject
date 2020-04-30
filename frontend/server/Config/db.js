import React, {Component} from 'react';


const mysql =require('mysql');
const db= mysql.createPool({
    host:'hufsscheduleservice.cvazzjdw6jxq.ap-northeast-2.rds.amazonaws.com',
    port:'3306',
    user:'hufs',
    password:'rhtjrgns12',
    database:'innodb',

});
db.connect();

module.exports=db;