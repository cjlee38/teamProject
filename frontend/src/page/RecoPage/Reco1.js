import React, { Component, useState, useEffect } from 'react';
import './Reco1.scss';
import Table4 from './components/Table';
import cralwer from '../Homepage/crawl'
import crawl from '../Homepage/crawl';
import Spinner from 'react-bootstrap/Spinner';
import ReactHtmlParser, { processNodes, convertNodeToElement, htmlparser2 } from 'react-html-parser';

export default function Reco1() {

  const [data3, setData3] = useState({isdata:false})

  
const areaType = {

    "1전공": "1전공",
    "이중": "이중",
    "부전공": "부전공",
    "교직": "교직",
  
  };
  useEffect(() => {
    crawl.get_inst().then(response => {
        console.log("res", response)
        response.data.data.sort(function(a, b) { // 오름차순
          return a.dept < b.dept ? -1 : a.dept > b.dept ? 2 : a.area > b.area? 1 : a.area < b.area? 0 : -2;
      });
      response.data.data.map(function(obj){

          if (obj.required) {obj.required="O"}
          else{obj.required=""}
          
      })

      response.data.lib.map(function(obj){
        areaType[obj.area] = obj.area + "(교양)"
        
    })
        setData3({data:response.data, isdata:true, lib:areaType})



    })
}, []
)
    return (
        <div>
            {data3.isdata ? <Table4 data={data3.data} lib={data3.lib} />:<><Spinner animation="grow"  variant="info" /><div className="spinner">강의 시간표 로딩중...</div></>}
            
        </div>
    )
}
