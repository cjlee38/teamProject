import React, { Component } from 'react';
import TabList from './TabList';
import Table from './Table';
import Table2 from './Table2';
import Table3 from './Table3';
import Axios from 'axios';
import Table4 from '../components/Table4'

export default class TabContent extends Component {
    constructor(props){
        super(props)
        this.state = {
            std : '',
            password : '',
            result : {},
        }

        this.data = [
          {trow:'졸업학점'},
          {trow: '현재학점'},
          {trow:'남은 학점'}
         
        ];
        
        this.data2 = [
            {trow:'미이수과목'},
            {trow: '이수 과목'}
          ];

          
          this.data3 = [
            {trow:'총 평점 2.0이상'},
            {trow: '전공/이중전공 졸업시험 합격여부'},
            {trow: '외국어인증(졸업인증) 완료 여부'}
          ];  
          this.data4 = [
            {trow: "베트남어, 융복합소프트웨어"}
          ]
    
    }

    componentDidMount() {
        Axios.get('http://localhost:1415/web/v1/checkCondition/try', {
            params: { userId: 24}
        })
        .then((response) => {
            this.setState({result : response.data.data},()=> {
                console.log("this.state는",this.state.result);
                this.data2[0].first_major = this.state.result.remainCourses;
                this.data2[1].first_major = this.state.result.takenCourses;
                let lists = Array()
                let take = this.state.result.takenCredit;
                let grd = this.state.result.grdCredit;
                let remain = this.state.result.remainCredit;
                
                lists.push(grd);
                lists.push(take);
                lists.push(remain);

                for (let i =0; i < this.data.length; i++){
                    this.data[i].first_major = lists[i][0];
                    this.data[i].second_major = lists[i][1];
                    this.data[i].sub_major = lists[i][2];
                    this.data[i].minor = lists[i][3];
                    this.data[i].out_door = lists[i][4];
                    this.data[i].liberal_arts = lists[i][5];
                    this.data[i].teaching = lists[i][6];
                    this.data[i].optional = lists[i][7];
                    this.data[i].total_credit = lists[i][8];
                }

                this.data[1].average_score = this.state.result.averageScore;
                this.data[0].average_score = this.state.result.grdAverageScore;
                this.data4={trow:this.state.result.userInfo[0] + this.state.result.userInfo[1]} ;
                console.log("check:", this.data4);
            });
        })
        .catch(function (error) {
            console.log(error);
        });
        this.setState({
            data : this.data
        });
        console.log("asd",this.state.data);
    }

    render() {
        console.log(this.state.result);
        return (
            <>
            <Table4 data={this.data4} />
            <div className="container up">
                <TabList>
                    <div label="학점" className="tab-content">
                    <Table data={this.data} />
                    </div>
                    <div label="이수 과목" className="tab-content">
                    <Table2 data={this.data2} />
                    </div>
                </TabList>
            </div>
            </>
        );
    }
}