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
  this.setState({std:"", password:""})
}
  

render () {
  console.log("ch")
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
        <div className="Crwaler">
            <form onSubmit={this.handlingSubmit}>
            <input name="std" placeholder="학번" value={this.state.std} onChange={this.handlingChange} />
            <input name="password" placeholder="종정시 비밀번호"value={this.state.password} type="password" onChange={this.handlingChange} />
            <button type="submit" onSubmit={this.handlingSubmit}>유저 크롤링</button>
            </form>
        </div>
      </div>
      
      <div className="foot">
      </div>
    </div>
    </>
  );
}
}

function CheckPage () {
  console.log('mainpage')
  return (
      <Router> 
          <div style={{margin:20}}> 
               
              <div>   
              <hr />
              <Switch>

              <Route path="/" exact={true} component={Main} />
              <Route path="/Main" exact={true} component={Main} />
              <Route path="/Login" component={LoginPage} />

                  <Route path="/Check" component={Check} />
                  <Route path="/Recommend" component={Recommend} />
              </Switch>

              </div>
              
          </div> 
      </Router>

  )
} 

export default CheckPage;