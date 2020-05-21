import React, { Component, useState, useEffect } from 'react';
import './Reco1.scss';
import Table4 from './components/Table';
import cralwer from '../Homepage/crawl'
import crawl from '../Homepage/crawl';
import Spinner from 'react-bootstrap/Spinner';

export default function Reco1() {

  const [data3, setData3] = useState({isdata:false})
  useEffect(() => {
    crawl.get_inst().then(response => {
        console.log("res", response.data)
        response.data.data.sort(function(a, b) { // 오름차순
          return a.dept < b.dept ? -1 : a.dept > b.dept ? 1 : 0;
      });
      response.data.data.map(function(obj){
          if (obj.required) {obj.required="O"}
          else{obj.required=""}
      })
        setData3({data:response.data.data, isdata:true})



    })
}, []
)
    console.log(data3)
    return (
        <div style={{ marginTop: "10%"}}>
            {data3.isdata ? <Table4 data={data3.data} />:<><Spinner animation="grow"  variant="info" /><div className="spinner">강의 시간표 로딩중...</div></>}
            
        </div>
    )
}

// export default class a extends React.Component {
//     constructor(props) {
//         super(props)
//         this.state = async() => { await cralwer.get_inst()}
    
//       }
//     componentDidMount() {
//        cralwer.get_inst().then((response) => {
//         console.log("userId : ", response.data.data);
//         this.setState({data:response.data.data})
        
//       })
//       .catch(function (error) {
//         console.log(error);
//       });
//         }

// render() {
//     console.log("data",this.state)
//     const { data } = this.state.data;
//     console.log("data",data)
//                 return(
//         <div style = {{ marginTop: "20%" }} >
//             <Table4 data={data} />
//         </div >
//     )

//     }
// }