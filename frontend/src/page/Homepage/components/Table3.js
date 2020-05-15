import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';


class Table extends Component {
  render() {
    return (
      <div className="whole-table">
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn isKey
            dataField='trow'
            dataAlign='center'
            headerAlign="left"
            width="45"
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

            조건
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
            충족여부
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
            비고
          </TableHeaderColumn>

        </BootstrapTable>
      </div>
    )
  }
}


export default Table;