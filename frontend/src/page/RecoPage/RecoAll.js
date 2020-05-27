import React, { Component, useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import TabList from './components/TabList';
import Table from './Reco1';
import Table4 from './components/Table';
import cralwer from '../Homepage/crawl'
import crawl from '../Homepage/crawl';
import Spinner from 'react-bootstrap/Spinner';

function RecoAll() {

  var [full_data, setFullData] = useState()
  var [originData, setoriginData] = useState()
  var [myCourse, setmyCourse] = useState([])


  var [data3, setData3] = useState({ isdata: false })


  var areaType = {

    "1전공": "1전공",
    "이중": "이중",
    "부전공": "부전공",
    "교직": "교직",

  };
  useEffect(() => {
    crawl.get_inst().then(response => {
      console.log("res", response)
      response.data.data.sort(function (a, b) { // 오름차순
        return a.dept < b.dept ? -1 : a.dept > b.dept ? 2 : a.area > b.area ? 1 : a.area < b.area ? 0 : -2;
      });
      response.data.data.map(function (obj) {

        if (obj.required) { obj.required = "O" }
        else { obj.required = "" }

      })

      response.data.lib.map(function (obj) {
        areaType[obj.area] = obj.area + "(교양)"

      })
      setData3({ data: response.data, isdata: true, lib: areaType })
      setFullData(response.data.data)
      setoriginData(response.data.data)



    })
  }, []
  )

  // this.handlerButton = this.handlerButton.bind(this)
  // this.RemoveButton = this.RemoveButton.bind(this)



  const handlerButton = async (row) => {
    let flag = false
    myCourse.forEach(function (element) {
      if (element.subject == row.subject) {
        flag = true
        return alert("동일 교과목 존재!")
      }
    })
    if (flag) { return }
    // const { myCourse } = myCourse
    // const { full_data } = full_data

    // console.log(myCourse, this.state.myCourse)
    await setmyCourse(myCourse.concat(row))
    await setFullData(full_data.filter(inst => inst.instruction_id !== row.instruction_id))

    // await this.setState({
    //   myCourse: this.state.myCourse.concat(row),
    //   full_data: this.state.full_data.filter(inst => inst.instruction_id !== row.instruction_id)

    // })
  }


  const RemoveButton = async (row) => {
    // const { full_data } = full_data
    // console.log(myCourse, this.state.myCourse)
    // const { myCourse } = this.state.myCourse

    await setmyCourse(myCourse.filter(inst => inst.instruction_id !== row.instruction_id))
    // const { myCourse } = myCourse
    // console.log(this.state.myCourse)
    await setFullData(originData.filter(inst => !myCourse.includes(inst.instruction_id)).sort(function (a, b) { // 오름차순
        return a.dept < b.dept ? -1 : a.dept > b.dept ? 1 : 0;
      })
    )

  }

  console.log(myCourse)

  return (
    <>


      <div className="CheckPage">

        <div className="container up">
          <TabList>
            <div label="강의 선택" className="tab-content">
            {data3.isdata ? <Table4 data={data3.data} myCourse={myCourse} lib={data3.lib} handlerButton={handlerButton} RemoveButton={RemoveButton} /> : <><Spinner animation="grow" variant="info" /><div className="spinner">강의 시간표 로딩중...</div></>}
            </div>
            <div label="공강 선택" className="tab-content">
              <Table />
            </div>

          </TabList>
        </div>

      </div>
    </>
  );
}


export default RecoAll;



