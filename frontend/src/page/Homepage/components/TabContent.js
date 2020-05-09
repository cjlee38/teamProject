import React, { Component } from 'react';
import TabList from './TabList';
import Table from './Table';
import Table2 from './Table2';
import Table3 from './Table3';

export default class TabContent extends Component {
    constructor(props){
        super(props)
        this.state = {
            std : '',
            password : '',
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
    
    }

    render() {
        return (
            <div className="container up">
                <TabList>
                    <div label="학점" className="tab-content">
                    <Table data={this.data} />
                    </div>
                    <div label="졸업요건" className="tab-content">
                    <Table2 data={this.data2} />
                    </div>
                    <div label="기타" className="tab-content">
                    <Table3 data={this.data3} />
                    </div>
                </TabList>
            </div>
        );
    }
}