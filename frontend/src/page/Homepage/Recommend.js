import React, {useState} from 'react';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import Searchbox from './components/Search';
import Campus from './components/CampusSelect';

function Recommend_1(){//선제조건 1: 듣고싶은강의 선택
    return(
        <div className="select_1">
            <div className="head">
                <div>
                    <img/>
                </div>
            </div>

            <div className="body">
                <Search/>
                <Button/>
                <Searchbox/> //소속
                <Campus/> //캠퍼스
                <Searchbox/> //전공/부전공
                <Select/> //실용외국어/교양
                <Chart/>
            </div>

            <div className="footer">

            </div>
        </div>
    )
}


function Recommend_2(){ //2:공강시간 설정
    return(
        <div className="select_2">
            <div className="head">
                <div>
                    <img/>
                </div>
            </div>

            <div className="body">
                <Chart/>
                <Button/>
            </div>

            <div className="footer">

            </div>
        </div>
    )
}

function Recommend_3(){ //3: 학점 범위설정
    return(
        <div className="select_3">
            <div className="head">
                <div>
                    <img/>
                </div>
            </div>

            <div className="body">
                <Counter/>
                <Counter/>
                <Button/> 
            </div>

            <div className="footer">
            </div>
        </div>
    )
}


export default Recommend_1;
