import React, {Component} from './node_modules/react';


const mysql =require('./node_modules/mysql');
const db= mysql.createPool({
    host:'hufsscheduleservice.cvazzjdw6jxq.ap-northeast-2.rds.amazonaws.com',
    port:'3306',
    user:'hufs',
    password:'rhtjrgns12',
    database:'innodb',

});

module.exports=db;