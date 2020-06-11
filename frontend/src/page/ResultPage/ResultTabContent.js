import React, { useState, useEffect } from 'react';
import Table from './ResultTable';
import TabList from './Tablist';
import Spinner from 'react-bootstrap/Spinner';
import Axios from 'axios';
import LectureTable from './test'


export default function TabContent1(props) {
    const [data, setData] = useState([])
    const [isData, setIsData] = useState(false)

    useEffect(async () => {
        Axios.post('http://ec2-13-209-184-168.ap-northeast-2.compute.amazonaws.com:1415/web/v1/makeTimeTable/try', {
            "myCourse": props.data.state.myCourse,
            "myCredit": props.data.state.myCredit,
            "myFreetime": props.data.state.mytime,
            "userId": props.userId
        })
            .then(async (response) => {
                console.log(response);
                await setData(response.data.data)
                await setIsData(true)

            })
            .catch(async function (error) {
                console.log(error);
                // await setData([[{ instruction_id: 1234, subject: "자료구조", class_time: "월 1 2 3", professor: "신찬수" }]])
                // await setIsData(true)

                alert(error)
            });

            // await setData([[{ instruction_id: 1234, subject: "자료구조", class_time: "월 1 2 3", professor: "신찬수" }]])

        // await setData(data)
    }, []
    )



    function mapping(data) {
        let i = 0
        data.map(function (array) {
            i++;
            return (<div label={`시간표${i}`} className="tab-content">
                <Table data={array} />
                <button onClick={() => onClick(i - 1)} />

            </div>)
        })
    }


    function onClick(i) {
        console.log(data[i]);
        let idArray = [];
        for (let j = 0; j < data[i].length; j++) {
            if (!data[i][j] === '') {
                idArray.push(data[i][j].instruction_id);
                console.log(data[i][j].instruction_id)
            }
        }
        console.log(idArray)
    }


    function makearr(a) {
        if (a.length) {

            let arr = Array(a.length);
            for (let i = 0; i < a.length; i++) {
                arr[i] = i;
            }
            return arr

        }
        return 0
    }


    var i = 0;
    console.log(data)
    return (
        <>
            {isData ?

                <div className="container up">
                    <TabList>

                        {data.map(function (array) {
                            i++;
                            let w = i
                            return (<div label={`시간표${i}`} className="tab-content">
                                <LectureTable data={array} lectureLength={makearr(array)} />
                                <button style={
                                    { float: 'right' }
                                } onClick={() => onClick(w - 1)}> 저장하기</button>
                            </div>)
                        })}
                    </TabList>
                </div>
                : <><Spinner animation="grow" variant="info" /><div className="spinner">결과 가져오는 중..</div></>}
        </>
    );

}


