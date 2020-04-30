import React, {useState, Component} from 'react';
import Input from '../LoginPage/components/Input';
import Button from '../LoginPage/components/Button';
import Title from '../LoginPage/components/Title';
import Logo from '../../image/logo.png';
import Table from './components/Table';

function Check() {
  const data = [
    {trow:'졸업학점'},
    {trow: '현재학점'},
    {trow:'남은 학점'}
   
  ];

  return (
    <div className="CheckPage">
      <div calssName="head" style={{display: 'flex', flexDirection:'row'}}>
        <div>
          <img src={Logo} classname="logo" alt="logo" width='15%'/>
        </div>
      </div>
      <div className="body">
        <h1>1.학점</h1>
        <Table data={data}/>
       
      </div>

      <div className="foot">
      </div>
    </div>
  );
}

export default Check;