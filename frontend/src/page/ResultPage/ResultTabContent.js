import React, { useState, useEffect } from 'react';
import TabList from './Tablist';
import Spinner from 'react-bootstrap/Spinner';
import Axios from 'axios';
import LectureTable from './ResultT'


export default function TabContent1(props) {
    const [data, setData] = useState([])
    const [isData, setIsData] = useState(false)

    useEffect( () => {
        if (isNaN(props.data.state.myCredit)) {
            props.data.state.myCredit = 0;
        }
        Axios.post('http://ec2-13-209-184-168.ap-northeast-2.compute.amazonaws.com:1415/web/v1/makeTimeTable/try', {
            "myCourse": props.data.state.myCourse,
            "myCredit": props.data.state.myCredit,
            "myFreetime": props.data.state.mytime,
            "userId": props.userId
        })
            .then(async (response) => {
                console.log(response);
               setData(response.data.data)
               setIsData(true)

            })
            .catch(async function (error) {
                console.log(error);
               setData([props.data.state.myCourse])
               setIsData(true)

                alert(error)
            });
    }, []
    )




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
                                
                            </div>)
                        })}
                    </TabList>
                </div>
                : <><Spinner animation="grow" variant="info" /><div className="spinner">결과 가져오는 중..</div></>}
        </>
    );

}


