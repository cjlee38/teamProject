import React, { Component, useState, useEffect } from 'react';
import TabList from './TabList';
import Table from './Table';
import Table2 from './Table2';
import Axios from 'axios';
import Table4 from '../components/Table4'



export default function TabContent1() {
    const [data, setData] = useState([{ data: "" }])
    const [data2, setData2] = useState([{ data: "" }])
    const [data3, setData3] = useState([{ data: "" }])

    useEffect(() => {
        Axios.get('http://localhost:1415/web/v1/checkCondition/try', {
            params: { userId: 1 }
        }).then(response => {
            console.log("res", response.data)
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
                trow: '남은학점', first_major: response.data.data.remainCredit[0], second_major: response.data.data.remainCredit[1],
                sub_major: response.data.data.remainCredit[2], minor: response.data.data.remainCredit[3], out_door: response.data.data.remainCredit[4],
                liberal_arts: response.data.data.remainCredit[5], teaching: response.data.data.remainCredit[6], optional: response.data.data.remainCredit[7],
                total_credit: response.data.data.remainCredit[8], average_score: "-"
            }])
            setData2([{
                trow: "필수 과목", first_major: response.data.data.grdFirstMajorCourses.join('\n'), second_major: response.data.data.grdSecondMajorCourses.join('\n')
                , minor: "-", liberal_arts: response.data.data.grdLiberalArtsCourses.join('\n'), teaching: response.data.data.grdTeachingCourses
                , optional: "-"
            },

            {
                trow: "이수 과목", first_major: response.data.data.takenFirstMajorCourses.join('\n'), second_major: response.data.data.takenSecondMajorCourses.join('\n')
                , minor: "-", liberal_arts: response.data.data.takenLiberArtsCourses.join('\n'), teaching: response.data.data.takenTeachingCourses.join('\n')
                , optional: response.data.data.takenFreeCourses.join('\n')
            },
            {
                trow: "미이수 과목", first_major: response.data.data.remainFirstMajorCourses.join('\n'), second_major: response.data.data.remainSecondMajorCourses.join('\n')
                , minor: "-", liberal_arts: response.data.data.remainLiberalArtsCourses.join('\n'), teaching: response.data.data.remainTeachingCourses
                , optional: "-"
            },
            ])
            console.log(response.data.data.remainLiberalArtsCourses)
            setData3([{ trow: response.data.data.userInfo.join(' / ') }])



        })
    }, []
    )
    console.log(data)

    return (
        <>
            <Table4 data={data3} />
            <div className="container up">
                <TabList>
                    <div label="학점" className="tab-content">
                        <Table data={data} />
                    </div>
                    <div label="이수 과목" className="tab-content">
                        <Table2 data={data2} />
                    </div>
                </TabList>
            </div>
        </>
    );

}


