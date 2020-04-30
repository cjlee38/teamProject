import React, {useState} from 'react';
import Counter from './components/CreditRange';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';

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


export default Recommend_3;
