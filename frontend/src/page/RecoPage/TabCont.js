import React, { Component, useState, useEffect } from 'react';
import TabList from './components/TabList';
import Table from './Reco1';



export default function TabContent1() {
    const [userCourse, setUserCourse] = useState()

    useEffect(() => {

}, []
    )

    return (
        <>
            <div className="container up">
                <TabList>
                    <div label="강의 선택" className="tab-content">
                        <Table />
                    </div>
                    <div label="공강 선택" className="tab-content">
                        <Table />
                    </div>
                    
                </TabList>
            </div>
        </>
    );

}


