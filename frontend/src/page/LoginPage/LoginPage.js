import React, {useState} from 'react';
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';
import Input from './components/Input';
import Button from './components/Button';
import Title from './components/Title';
import Logo from '../../image/logo.png';
import './LoginPage.scss';
import Signuppage from '../Signuppage/Signuppage';
import Main from '../MainPage/MainPage';
import Check from '../Homepage/Check';
import Recommend from '../Homepage/Recommend';


function LoginPage () {
  return (
    <Router>
    <Switch>

        <Route path="/" exact={true} component={Main} />
        <Route path="/Main" component={Main} />

        <Route path="/Main/:id/:password" component={Main} />
        <Route path="/Login" component={LoginP} />

        <Route path="/Check" component={Check} />
        <Route path="/Recommend" component={Recommend} />
    </Switch>



</Router>

  )
} 



const LoginP = () => {
    const [inputs, setInputs] = useState({
      id: "",
      password: ""
    });
    
    const {id, password} = inputs;

    const onChange = e => {
      const {value, name} = e;
      console.log(value, name);

      setInputs({
          ...inputs,
          [name]: value
      });
    }
    
    const reset = () => {
      setInputs({
        id : "",
        password : ""
      })
    }

    const tryLogin = async() => {
      console.log('sdffd')
      console.log(id, password);
      try {
        let response = await fetch(`http://localhost:1415/web/v1/user/Login?studentNumber=${id}&password=${password}`);
        let json = await response.json();
        console.log(json.success);
      console.log(1)
      } catch (error) {
        console.log(error);
      };
      reset();
    }

    const trySignUp = () => {
      reset();
    }
    
    return (
    <div className="LoginPage">
      <div className="body">
        <Title/>
        <div className="idPassword">
          <Input 
            placeholder={"ex)195002215"}
            name={"id"}
            onChange={onChange}
            value={id}
            text={"학번"}
            />
          <Input 
            placeholder={"ex)12345678"}
            name={"password"}
            onChange={onChange}
            value={password}
            text={"비밀번호"}
            />
        </div>
        <div className="Button">
        <Link to={`/Main/${id}/${password}`}>
            <Button onClick={tryLogin} name={"로그인"} value={"login"}/>
          </Link>
          <Link to="/Signup">
            <Button onClick={trySignUp} name={"회원가입"} value={"signUp"}/>
          </Link>  
        </div>
      </div>
      <div className="foot"/>
    </div>
    )
}




export default LoginPage