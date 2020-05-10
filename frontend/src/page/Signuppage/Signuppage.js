import React, { useState} from 'react';
import PropTypes from 'prop-types';
import {  BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import Input from '../LoginPage/components/Input';
import Title from '../LoginPage/components/Title';
import Button from '../LoginPage/components/Button';
import Logo from '../../image/logo.png';
import Recommend from "../Homepage/Recommend";
import LoginPage from '../LoginPage/LoginPage';
import Main from '../MainPage/MainPage';

const Signuppage = () => {
    console.log("hi")
    const [inputs, setInputs]=useState({
        username:"",
        usernum:"",
        password:""
    });
    const {username, usernum, password}=inputs;

    const onChange=e=>{
        const{value, name}=e;
        setInputs({
            ...inputs,
            [name]:value
        });
    }

    return(
        <div className="SignupPage">
            <div className="body">
                <Title/>
                <div className="useridpassword">
                    <Input
                        placeholder={"이름"}
                        onChange={onChange}
                        name={"username"}
                        value={username}
                        text={"이름"}
                    />
                    <Input
                        placeholder={"학번 ex)201912345"}
                        onChange={onChange}
                        name={"usernum"}
                        value={usernum}
                        text={"학번"}
                    />
                    <Input
                        placeholder={"ex)12345678"}
                        onChange={onChange}
                        name={"password"}
                        value={password}
                        text={"비밀번호"}
                    />
                </div>
                <div className="Button">
                <Link to="/Login">
            <Button onClick name={"회원가입"} value={"signUp"}/>
          </Link> 
                </div>
            </div>
        </div>

    )


}


  
function SignUp () {
      console.log('mainpage')
      return (
        <Router>
          <Switch>
    
            <Route path="/" exact={true} component={Main} />
            <Route path="/Main" component={Main} />
    
            <Route path="/Main/:id/:password" component={Main} />
            <Route path="/Login" component={LoginPage} />
    
            <Route path="/Signup" component={Signuppage} />
          </Switch>
    
    
    
        </Router>
    
    
      )
    };

    export default SignUp