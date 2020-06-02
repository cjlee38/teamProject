import React, { Component } from 'react';
//import TabList from './Tablist';
import Table from './ResultTable';


export default class TabContent extends Component {
    constructor(props){
        super(props)
        this.state = {
            std : '',
            password : '',
        }
        this.data = [
          {trow:'1', MON: '자료구조', THU: '알고리즘' },
          {trow: '2'},
          {trow:'3'},
          {trow:'4'},
          {trow: '5'},
          {trow: '6'},
          {trow: '7'},
          {trow: '8'},
          {trow: '9'},
          {trow: '10'},
          {trow: '11'},
          {trow: '12'},
          {trow: '13'}
        
         
        ];

        this.a = [
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0],
            [0, 0, 0, ['자료구조', '신찬수'], 0]
        ]
        
    
    
    }
    

    render() {
        return (
            <div className="container up">
                
                    <div label="학점" className="tab-content">
                    <Table data={this.a} />
                    </div>
                
            </div>
        );
    }
}