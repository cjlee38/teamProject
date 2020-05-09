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
    }
    this.data = [
      { trow: '졸업학점' },
      { trow: '현재학점' },
      { trow: '남은 학점' }

    ];

  }
  handlingChange = (event) => {
    this.setState({ [event.target.name]: event.target.value })
    console.log(event.target.name, "촘  ")
  }

  handlingSubmit = async (event) => {
    // event.preventDefault()
    console.log("크롤링 시작")
    let result = await api.crawlUser({ std_num: this.state.std, password: this.state.password })
    console.log(result.data)
    this.setState({ std: "", password: "" })
  }


  render() {
    console.log("ch")
    return (
      <>


        <div className="CheckPage">
          <div className="body">
            <div className="head">내 졸업 요건 확인</div>
            <div className="Crwaler">
              <form onSubmit={this.handlingSubmit}>


                <TextField
                  className="input" margin="normal"
                  id="standard-textarea"
                  label="학번"
                  name="std" variant="outlined"
                  placeholder="학번" value={this.state.std} onChange={this.handlingChange}
                />
                <TextField
                  className="input" margin="normal"
                  id="standard-textarea"
                  label="종합정보시스템 비밀번호"
                  name="password" variant="outlined"
                  placeholder="종정시 비밀번호" value={this.state.password} type="password" onChange={this.handlingChange}
                />
                <Button startIcon={<CloudUploadIcon />} variant="outlined" size="large" color="primary" type="submit" onSubmit={this.handlingSubmit} className="crawlingbtn">
                  업데이트      </Button>
              </form>
            </div>
            {/* <Crawler /> */}

            <TabContent />


          </div>

          <div className="foot">
          </div>
        </div>
      </>
    );
  }
}

function CheckPage() {
  console.log('mainpage')
  return (
    <Router>
      <Switch>

        <Route path="/" exact={true} component={Main} />
        <Route path="/Main" component={Main} />

        <Route path="/Main/:id/:password" component={Main} />
        <Route path="/Login" component={LoginPage} />

        <Route path="/Check" component={Check} />
        <Route path="/Recommend" component={Recommend} />
      </Switch>



    </Router>


  )
}

export default CheckPage;