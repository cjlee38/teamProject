import React, { Component, useState, useEffect } from 'react';
//import TabList from './Tablist';
import Table from './ResultTable';


export default function TabContent1(props) {
    //const [data, setData] = useState([{ trow: 'MON'}, { trow: 'TUE'}, { trow: 'WED'}, { trow: 'THU'}, { trow: 'FRI'} ])
    
    const [data, setData]=useState()
    
   let result = [
    [1, 0, 0, 0, ['자료구조', '신'], 0],
    [2, 0, 0, 0,  ['알고리즘', '고'], 0],
    [3, 0, 0, 0,  ['네트워크', '이'], 0],
    [4, 0, 0, 0,  ['웹프', '고'], 0],
    [5,  0, 0, 0, ['컴프', '고'], 0],
    [6, 0, 0,['자료구조', '신'], 0 , 0],
    [7,  0, 0, 0, ['보안', '이'], 0],
    [8, ['자료구조', 'sdf찬수'],0, 0, 0, 0],
    [9, 0, 0, 0, ['자료구조', '신ggdf'], 0],
    [10, 0, 0, 0, ['자료구조', 'dfg'], 0],
    [11, 0, 0, 0, ['자료구조', '찬dfg'], 0],
    [12, 0, 0, 0, ['자료구조', 'qqq'], 0],
    [13, 0, 0, 0, ['자료구조', 'ggg'], 0]
]




for(var i=0; i<13; i++){
    for(var j=1; j<6; j++){
        if(result[i][j]===0)
            result[i][j]='';
        else
            result[i][j]=result[i][j][0]+'('+result[i][j][1]+')'
    }
};
    
    
    
  

//setData([{trow:"1교시", "1":result[0][0], "2": result[0][1]},
// {trow:"2교시"},
// ])

    return (
        <>
           
            <div className="container up">
             
                    <div label="학점" className="tab-content">
                        <Table data={result} />
                    </div>
             
            </div>
        </>
    );

}


