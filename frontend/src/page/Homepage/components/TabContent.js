import React, { Component } from 'react';
import TabList from './TabList';
import Table from './Table';


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
         
        ];}

    render() {
        return (
            <div className="container up">
                <TabList>
                    <div label="학점" className="tab-content">
                    <Table data={this.data} />
                    </div>
                    <div label="졸업요건" className="tab-content">
                    <Table data={this.data} />
                    </div>
                    <div label="기타" className="tab-content">
                    <Table data={this.data} />
                    </div>
                </TabList>
            </div>
        );
    }
}