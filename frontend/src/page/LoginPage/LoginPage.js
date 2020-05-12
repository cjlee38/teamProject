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
import Singuppage from '../Signuppage/Signuppage';
import Axios from 'axios';

// function LoginPage () {
//   return (
//     <Router>
//     <Switch>

//         <Route path="/" exact={true} component={Main} />
//         <Route path="/Main" component={Main} />

//         <Route path="/Main/:id/:password" component={Main} />
//         <Route path="/Login" component={LoginP} />
//         <Route path="/Signup" component={Signuppage} />

//         <Route path="/Check" component={Check} />
//         <Route path="/Recommend" component={Recommend} />
//     </Switch>



// </Router>

//   )
// } 



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

    const tryLogin = () => {
      console.log('sdffd')
      console.log(id, password);
      Axios.get('http://localhost:1415/web/v1/user/Login', {
        params: { studentNumber:id, password:password }
      })
      .then((response) => {
        console.log("userId : ", response.data.userId);
      })
      .catch(function (error) {
        console.log(error);
      });
      reset();
    }

    const trySignUp = () => {
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
            type={"password"}
            onChange={onChange}
            value={password}
            text={"비밀번호"}
            />
        </div>
        <div className="Button">
        <Link to='/Check'>
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




export default LoginP