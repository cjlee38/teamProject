import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import Input from '../LoginPage/components/Input';
import Title from '../LoginPage/components/Title';
import Button from '../LoginPage/components/Button';
import Axios from 'axios';

const Signuppage = () => {
    const [inputs, setInputs] = useState({
        username: "",
        usernum: "",
        password: ""
    });


    const { username, usernum, password } = inputs;

    const onChange = e => {
        const { value, name } = e;
        setInputs({
            ...inputs,
            [name]: value
        });

        console.log(username, usernum, password);
    }

    const trySignUp = async () => {
        console.log(username, usernum, password);
        Axios.post('http://localhost:1415/web/v1/user/SignUp', {
            "studentNumber": usernum,
            "password": password,
            "name": username
        })
            .then((response) => {
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <div className="SignupPage">
            <div className="body">
                <Title />
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
                        type="password"
                        text={"비밀번호"}
                    />
                </div>
                <div className="Button">
                    <Link to="/Login">
                        <Button onClick={trySignUp} name={"회원가입"} value={"signUp"} />
                    </Link>
                </div>
            </div>
        </div>

    )


}



export default Signuppage