import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';


class Table extends Component {
  Formatter1(cell, row) {
    let a = row.first_major
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter2(cell, row) {
    let a = row.second_major
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter3(cell, row) {
    let a = row.sub_major
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter4(cell, row) {
    let a = row.out_door
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter5(cell, row) {
    let a = row.liberal_arts
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter6(cell, row) {
    let a = row.minor
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter7(cell, row) {
    let a = row.teaching
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter8(cell, row) {
    let a = row.optional
    
    let sum = 0
    let values = Object.values(row)
    if (values[0] === "남은 학점"){
    values.slice(1,8).map(function(val) {if (val > 0){sum += val}})
    if (values[9] - sum <= 0){
      return (<>0</>)
    }
    return(<>{values[9] - sum}</>)}

    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
  Formatter9(cell, row) {
    let a = row.total_credit
    if (a <= 0) {
    return (
      <>0</>
    );}
    else{
    return (<>{a}</>)}
  }
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
            dataFormat={this.Formatter1}
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
            dataFormat={this.Formatter2}
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
            dataFormat={this.Formatter3}
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
            dataFormat={this.Formatter4}
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
            dataFormat={this.Formatter5}
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
            dataFormat={this.Formatter6}
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
            dataFormat={this.Formatter7}
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
            dataFormat={this.Formatter8}
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
            dataFormat={this.Formatter9}
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