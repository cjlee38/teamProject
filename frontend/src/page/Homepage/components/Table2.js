import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';
//import '../css/Table.css'
//import '../dist/react-bootstrap-table-all.min.css'


class Table extends Component {
  render() {
    console.log("table:", this.props);
    return (
      <div className="course_t">
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn 
            isKey
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
          <TableHeaderColumn 
            dataField='first_major'
            dataAlign='center'
            headerAlign="center"
            width="15%"
            maxHeight="20%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}>

            1전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='second_major'
                      maxHeight="20%"

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            이중전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='minor'
                      maxHeight="20%"

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            부전공
          </TableHeaderColumn>
     
          <TableHeaderColumn dataField='liberal_arts'
                      maxHeight="20%"

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            교양
          
          
          </TableHeaderColumn>
          <TableHeaderColumn dataField='teaching'
                      maxHeight="20%"

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            교직
          
          
          </TableHeaderColumn>
          <TableHeaderColumn dataField='optional'
                      maxHeight="20%"

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            자선
          
          
          </TableHeaderColumn>

        </BootstrapTable>
      </div>
    )
  }
}


export default Table;