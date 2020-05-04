/*import React, { Component } from 'react';
import {BootstrapTable, 
       TableHeaderColumn} from 'react-bootstrap-table';
// in ECMAScript 6
var ReactBSTable = require('react-bootstrap-table');  
// or in ECMAScript 5

class Table2 extends Component {
  render() {
    return (
      <div>
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn isKey dataField='id'>
            ID
          </TableHeaderColumn>
          <TableHeaderColumn dataField='name'>
            Name
          </TableHeaderColumn>
          <TableHeaderColumn dataField='value'>
            Value
          </TableHeaderColumn>
        </BootstrapTable>
      </div>
    );
  }
}*/

import React, {Component} from 'react';
import {BootstrapTable, TableHeaderColumn} from 
       'react-bootstrap-table';
import axios from 'axios';
//import '../css/Table.css'
//import '../dist/react-bootstrap-table-all.min.css'
 
 
class Table extends Component {

  componentDidMount() {
    this._dbTest();
  }
  
  _dbTest = async() => {
    const res = await axios.get('/api/test');
    console.log(res.data)
  }




  render() {
    return (
      <div>
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn isKey
                             dataField='trow'
                             dataAlign='center'
                             headerAlign="left"
                             width="40"
                             tdStyle={
                                 {backgroundColor: ''}}
                            thStyle={
                              {fontWeight:'heavy'},
                              {backgroundColor:'#CCCCCC'}
                            }>
            
          </TableHeaderColumn>
          <TableHeaderColumn dataField='first_major'
                             dataAlign='center'
                             headerAlign="center"
                             width="13%"
                             thStyle={
                                 {fontWeight: 'heavy', 
                                 backgroundColor: '#CCCCCC'}}>

            1전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='second_major'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            이중전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='sub_major'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
          2전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='out_door'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            실외
          </TableHeaderColumn>
          <TableHeaderColumn dataField='liberal_arts'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            교양
          </TableHeaderColumn>
          <TableHeaderColumn dataField='minor'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            부전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='teaching'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            교직
          </TableHeaderColumn>
          <TableHeaderColumn dataField='optional'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            자선
          </TableHeaderColumn>
          <TableHeaderColumn dataField='total_credit'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            총취득
          </TableHeaderColumn>
          <TableHeaderColumn dataField='average_score'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            총평점
          </TableHeaderColumn>
          
        </BootstrapTable>
      </div>
    )
  }
}

 
export default Table;