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

import React, {Component} from 'react'
import {BootstrapTable, TableHeaderColumn} from 
       'react-bootstrap-table'
//import '../css/Table.css'
//import '../dist/react-bootstrap-table-all.min.css'
 
 
class Table extends Component {
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
          <TableHeaderColumn dataField='1전공'
                             dataAlign='center'
                             headerAlign="center"
                             width="13%"
                             thStyle={
                                 {fontWeight: 'heavy', 
                                 backgroundColor: '#CCCCCC'}}>

            1전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='이중'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            이중전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='부전공'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
          부전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='교양외국어'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            교양외국어
          </TableHeaderColumn>
          <TableHeaderColumn dataField='교양'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            교양
          </TableHeaderColumn>
          <TableHeaderColumn dataField='교직'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            교직
          </TableHeaderColumn>
          <TableHeaderColumn dataField='외국어'
                             dataAlign='center'
                             width="13.5%"
                             thStyle={
                                {fontWeight: 'heavy', 
                                backgroundColor: '#CCCCCC'}}
                             headerAlign="center">
            외국어
          </TableHeaderColumn>
        </BootstrapTable>
      </div>
    )
  }
}

 
export default Table;