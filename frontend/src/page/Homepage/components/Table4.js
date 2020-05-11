import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';
//import '../css/Table.css'
//import '../dist/react-bootstrap-table-all.min.css'


class Table extends Component {
  render() {
    return (
      <div className="table4">
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn isKey
            dataField='trow'
            dataAlign='center'
            headerAlign="center"
            width="5%"
            tdStyle={
              { backgroundColor: '' }}
            thStyle={
              { fontWeight: 'heavy' },
              { backgroundColor: '#CCCCCC' }
            }>
            전공 정보

          </TableHeaderColumn>
          {/* <TableHeaderColumn dataField='first_major'
            dataAlign='center'
            headerAlign="center"
            width="9%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}>

          </TableHeaderColumn> */}


        </BootstrapTable>
      </div>
    )
  }
}


export default Table;