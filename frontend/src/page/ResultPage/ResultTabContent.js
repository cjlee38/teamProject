import React, {  useState, useEffect } from 'react';
import Table from './ResultTable';
import TabList from './Tablist';
import Spinner from 'react-bootstrap/Spinner';


export default function TabContent1() {

    const [data, setData] = useState()
    const [isData, setIsData] = useState(false)

    useEffect(async () => {
        var result1 = [
            [1, 0, 0, 0, ['자료구조', '신찬수'], 0],
            [2, 0, 0, 0, ['알고리즘', '고'], 0],
            [3, 0, 0, 0, ['네트워크', '이'], 0],
            [4, 0, 0, 0, ['웹프', '고'], 0],
            [5, 0, 0, 0, ['컴프', '고'], 0],
            [6, 0, 0, ['자료구조', '신'], 0, 0],
            [7, 0, 0, 0, ['보안', '이'], 0],
            [8, ['자료구조', 'sdf찬수'], 0, 0, 0, 0],
            [9, 0, 0, 0, ['자료구조', '신ggdf'], 0],
            [10, 0, 0, 0, ['자료구조', 'dfg'], 0],
            [11, 0, 0, 0, ['자료구조', '찬dfg'], 0],
            [12, 0, 0, 0, ['자료구조', 'qqq'], 0],
            [13, 0, 0, 0, ['자료구조', 'ggg'], 0]
        ]
        var result2 = [
            [1, 0, 0, 0, ['자료구조', '신찬수'], 0],
            [2, 0, 0, 0, ['알고리즘', '고'], 0],
            [3, 0, 0, 0, ['네트워크', '이'], 0],
            [4, 0, 0, 0, ['웹프', '고'], 0],
            [5, 0, 0, 0, ['컴프', '고'], 0],
            [6, 0, 0, ['자료구조', '신'], 0, 0],
            [7, 0, 0, 0, ['보안', '이'], 0],
            [8, ['자료구조', 'sdf찬수'], 0, 0, 0, 0],
            [9, 0, 0, 0, ['자료구조', '신ggdf'], 0],
            [10, 0, 0, 0, ['자료구조', 'dfg'], 0],
            [11, 0, 0, 0, ['자료구조', '찬dfg'], 0],
            [12, 0, 0, 0, ['자료구조', 'qqq'], 0],
            [13, 0, 0, 0, ['자료구조', 'ggg'], 0]
        ]
        let test = [result1, result2]
        for (var x = 0; x < test.length; x++) {
            for (var i = 0; i < 13; i++) {
                for (var j = 1; j < 6; j++) {
                    if (test[x][i][j] === 0) {
                        test[x][i][j] = '';
                    }
                    else {
                        test[x][i][j] = test[x][i][j][0] + '(' + test[x][i][j][1] + ')'
                    }
                }
            };
        }
        await setData(test)
        await setIsData(true)
    }, []
    )



    function mapping(data) {
        let i = 0
        data.map(function (array) {
            i++;
            return (<div label={`시간표${i}`} className="tab-content">
                <Table data={array} />
                <button onClick={()=>onClick(i-1)}/>

            </div>)
        })
    }


    function onClick(i){
        console.log(data[i]);
        let idArray=[];
        for(let j=0; j<data[i].length; j++){
            if(!data[i][j]===''){
                idArray.push(data[i][j].instruction_id);
                console.log(data[i][j].instruction_id)
            }
        }
        console.log(idArray)
    }





    var i = 0;
    return (
        <>
            {isData ?

                <div className="container up">
                    <TabList>

                        {data.map(function (array) {
                            i++;
                            let w = i
                            return (<div label={`시간표${i}`} className="tab-content">
                                <Table data={array} />
                                <button style={
                                    {float: 'right'}
                                }onClick={()=>onClick(w-1)}> 저장하기</button>
                            </div>)
                        })}
                    </TabList>
                </div>
                : <><Spinner animation="grow" variant="info" /><div className="spinner">결과 가져오는 중..</div></>}
        </>
    );

}


