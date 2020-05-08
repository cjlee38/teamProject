import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';
//import '../css/Table.css'
//import '../dist/react-bootstrap-table-all.min.css'


class Table extends Component {
  render() {
    return (
      <div className="whole-table">
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn isKey
            dataField='trow'
            dataAlign='center'
            headerAlign="left"
            width="40"
            tdStyle={
              { backgroundColor: '' }}
            thStyle={
              { fontWeight: 'heavy' },
              { backgroundColor: '#CCCCCC' }
            }>

          </TableHeaderColumn>
          <TableHeaderColumn dataField='first_major'
            dataAlign='center'
            headerAlign="center"
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}>

            1전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='second_major'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            이중전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='sub_major'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            2전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='out_door'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            실외
          </TableHeaderColumn>
          <TableHeaderColumn dataField='liberal_arts'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            교양
          </TableHeaderColumn>
          <TableHeaderColumn dataField='minor'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            부전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='teaching'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            교직
          </TableHeaderColumn>
          <TableHeaderColumn dataField='optional'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            자선
          </TableHeaderColumn>
          <TableHeaderColumn dataField='total_credit'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            총취득
          </TableHeaderColumn>
          <TableHeaderColumn dataField='average_score'
            dataAlign='center'
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center"> 
            총평점
          </TableHeaderColumn>

        </BootstrapTable>
      </div>
    )
  }
}


export default Table;