import React, { useState} from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import Input from '../LoginPage/components/Input';
import Title from '../LoginPage/components/Title';
import Button from '../LoginPage/components/Button';
import Logo from '../../image/logo.png';


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
            <div className="head">
                <div>
                    <img src={Logo} classname="logo" alt="logo" width='15%'/>
                </div>
            </div>
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
                    <button>회원가입</button>
                </div>
            </div>
        </div>

    )


}

export default Signuppage