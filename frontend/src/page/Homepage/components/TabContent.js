import React, { Component, useState, useEffect } from 'react';
import TabList from './TabList';
import Table from './Table';
import Table2 from './Table2';
import Axios from 'axios';
import Table4 from '../components/Table4'



export default function TabContent1(props) {
    const [data, setData] = useState([{ trow: '졸업 학점'}, { trow: '현재 학점'}, { trow: '남은 학점'} ])
    const [data2, setData2] = useState([{trow: "필수 과목"}, {trow: "이수 과목"}, {trow: "미이수 과목"} ])
    const [data3, setData3] = useState()
    useEffect(() => {
        Axios.get('http://ec2-13-209-184-168.ap-northeast-2.compute.amazonaws.com:1415/web/v1/checkCondition/try', {
            params: { userId: props.userID }
        }).then(response => {
            let total = response.data.data.takenCredit.reduce(
                (accumulator, currentValue) => accumulator + currentValue,
                0
            );
            setData([{
                trow: '졸업 학점', first_major: response.data.data.grdCredit[0], second_major: response.data.data.grdCredit[1],
                sub_major: response.data.data.grdCredit[2], minor: response.data.data.grdCredit[3], out_door: response.data.data.grdCredit[4],
                liberal_arts: response.data.data.grdCredit[5], teaching: response.data.data.grdCredit[6], optional: response.data.data.grdCredit[7],
                total_credit: response.data.data.grdCredit[8], average_score: 2.0
            },
            {
                trow: '현재 학점', first_major: response.data.data.takenCredit[0], second_major: response.data.data.takenCredit[1],
                sub_major: response.data.data.takenCredit[2], minor: response.data.data.takenCredit[3], out_door: response.data.data.takenCredit[4],
                liberal_arts: response.data.data.takenCredit[5], teaching: response.data.data.takenCredit[6], optional: response.data.data.takenCredit[7],
                total_credit: total, average_score: response.data.data.averageScore
            },
            {
                trow: '남은 학점', first_major: response.data.data.remainCredit[0], second_major: response.data.data.remainCredit[1],
                sub_major: response.data.data.remainCredit[2], minor: response.data.data.remainCredit[3], out_door: response.data.data.remainCredit[4],
                liberal_arts: response.data.data.remainCredit[5], teaching: response.data.data.remainCredit[6], 
                optional: response.data.data.remainCredit[7],
                total_credit: response.data.data.remainCredit[8], average_score: "-"
            }])
            setData2([{
                trow: "필수 과목", first_major: response.data.data.grdFirstMajorCourses, second_major: response.data.data.grdSecondMajorCourses
                , minor: "-", liberal_arts: response.data.data.grdLiberalArtsCourses, teaching: response.data.data.grdTeachingCourses
                , optional: "-"
            },

            {
                trow: "이수 과목", first_major: response.data.data.takenFirstMajorCourses, second_major: response.data.data.takenSecondMajorCourses
                , minor: "-", liberal_arts: response.data.data.takenLiberArtsCourses, teaching: response.data.data.takenTeachingCourses
                , optional: response.data.data.takenFreeCourses
            },
            {
                trow: "미이수 과목", first_major: response.data.data.remainFirstMajorCourses, second_major: response.data.data.remainSecondMajorCourses
                , minor: "-", liberal_arts: response.data.data.remainLiberalArtsCourses, teaching: response.data.data.remainTeachingCourses
                , optional: "-"
            },
            ])
            setData3([{ trow: response.data.data.userInfo.join(' / ') }])



        })
    }, []
    )

    return (
        <>
            <Table4 data={data3} />
            <div className="GG">
                <TabList>
                    <div label="학점" className="tab-content1">
                        <Table data={data} />
                    </div>
                    <div label="이수 과목" className="tab-content2" >
                        <Table2 data={data2} />
                    </div>
                </TabList>
            </div>
        </>
    );

}


