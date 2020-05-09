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
            width="30%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}>

            1전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='second_major'
            dataAlign='center'
            width="30%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            이중전공
          </TableHeaderColumn>
     
          <TableHeaderColumn dataField='liberal_arts'
            dataAlign='center'
            width="30%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            교양
          
          
          </TableHeaderColumn>

        </BootstrapTable>
      </div>
    )
  }
}


export default Table;