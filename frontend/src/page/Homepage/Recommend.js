import React, {useState} from 'react';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import Searchbox from './components/Search';
import Campus from './components/CampusSelect';
import Button from '../LoginPage/components/Button';
import Counter from '../Homepage/components/CreditRange'





function Recommend_1(){//선제조건 1: 듣고싶은강의 선택
    console.log("reco")
    return(
        <div className="select_1">
            <div className="head">
                <div>
                    <img/>
                </div>
            </div>

            <div className="body">
                <h2>1.듣고 싶은 과목 설정</h2>
                <Searchbox/> //전공/부전공
                <Searchbox/> //실용외국어/교양
                <Counter/>
             
            </div>

            <div className="footer">

            </div>
        </div>
    )
}



export default Recommend_1;
