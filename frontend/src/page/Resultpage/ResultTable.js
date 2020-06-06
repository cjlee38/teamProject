import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';
//import '../css/Table.css'
//import '../dist/react-bootstrap-table-all.min.css'


class ResultTable extends Component {
  render() {
    return (
      <div className="whole-table">
        <BootstrapTable data={this.props.data}>
   

        <TableHeaderColumn dataField='0' isKey
            dataAlign='center'
            headerAlign="center"
            width="8%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}>

              시간
          </TableHeaderColumn>
          <TableHeaderColumn dataField='1'
            dataAlign='center'
            headerAlign="center"
            width="18%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}>

              월
          </TableHeaderColumn>
          <TableHeaderColumn dataField='2'
            dataAlign='center'
            width="18%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            화
          </TableHeaderColumn>
     
          <TableHeaderColumn dataField='3'
            dataAlign='center'
            width="18%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
              수
          
          
          </TableHeaderColumn>
          <TableHeaderColumn dataField='4'
            dataAlign='center'
            width="18%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
              목
          
          
          </TableHeaderColumn>
          <TableHeaderColumn dataField='5'
            dataAlign='center'
            width="18%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
              금
          
          
          </TableHeaderColumn>
  



        </BootstrapTable>
      </div>
    )
  }
}


export default ResultTable;