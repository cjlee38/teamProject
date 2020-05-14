import React, { useState, Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';

import Input from '../LoginPage/components/Input';
// import Button from '../LoginPage/components/Button';
import Title from '../LoginPage/components/Title';
import Logo from '../../image/logo.png';
import Table from './components/Table';
import Crawler from './components/Crawler';

import api from './crawl'
import Recommend from "../Homepage/Recommend";
import LoginPage from '../LoginPage/LoginPage';
import Main from '../MainPage/MainPage';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Icon from '@material-ui/core/Icon';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab';
import './Check.scss'
import TabContent from './components/TabContent';

class Check extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      std: '',
      password: '',
      result : {}
    }

  }

  handlingChange = (event) => {
    this.setState({ [event.target.name]: event.target.value })
    console.log(event.target.name, "촘  ")
  }


  render() {
    console.log("ch")
    return (
      <>


        <div className="CheckPage">
          <div className="body">
            <div className="head">내 졸업 요건 확인</div>
      <div className="Crwaler">
      
            <Crawler />
            </div>

            <TabContent />


          </div>

          <div className="foot">
          </div>
        </div>
      </>
    );
  }
}

// function CheckPage() {
//   console.log('mainpage')
//   return (
//     <Router>
//       <Switch>

//         <Route path="/" exact={true} component={Main} />
//         <Route path="/Main" component={Main} />

//         <Route path="/Main/:id/:password" component={Main} />
//         <Route path="/Login" component={LoginPage} />

//         <Route path="/Check" component={Check} />
//         <Route path="/Recommend" component={Recommend} />
//       </Switch>



//     </Router>


//   )
// }

export default Check;