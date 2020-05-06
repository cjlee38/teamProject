import React, {useState, Component} from 'react';
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';

import Input from '../LoginPage/components/Input';
import Button from '../LoginPage/components/Button';
import Title from '../LoginPage/components/Title';
import Logo from '../../image/logo.png';
import Table from './components/Table';
import api from './crawl'
import Recommend from "../Homepage/Recommend";
import LoginPage from '../LoginPage/LoginPage';
import Main from '../MainPage/MainPage';
import Check1 from '../Homepage/Check';


class Check extends React.Component {

  constructor(props){
    super(props)
    this.state = {
        std : '',
        password : '',
    }
    this.data = [
      {trow:'졸업학점'},
      {trow: '현재학점'},
      {trow:'남은 학점'}
     
    ];

}
handlingChange = (event) => {
    this.setState({[event.target.name] : event.target.value})
    console.log(event.target.name, "촘  ")
}

handlingSubmit = async (event) =>{
  // event.preventDefault()
  console.log("크롤링 시작")
  let result = await api.crawlUser({std_num : this.state.std, password : this.state.password})
  console.log(result.data)
}
  

render () {
  return(
    <>
            
    <div className="CheckPage">
      <div calssName="head" style={{display: 'flex', flexDirection:'row'}}>
        <div>
          <img src={Logo} classname="logo" alt="logo" width='15%'/>
        </div>
      </div>
      <div className="body">
        <h1>1.학점</h1>
        <Table data={this.data}/>
       
      </div>
      <div className="Crwaler">
            <form onSubmit={this.handlingSubmit}>
            <input name="std" value={this.state.std} onChange={this.handlingChange} />
            <input name="password" value={this.state.password} type="password" onChange={this.handlingChange} />
            <button type="submit">유저 크롤링</button>
            </form>
        </div>
      <div className="foot">
      </div>
    </div>
    </>
  );
}
}
export default Check;