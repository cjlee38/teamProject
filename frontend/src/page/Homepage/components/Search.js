import React, { Component } from 'react'
import ReactSearchBox from 'react-search-box'

export default class Search extends Component {

  data = [
    {
      key: 'AI융합',
      value: 'AI융합전공(Artificial Intelligence Covergence)',
    },
    {
      key: 'CORE글로벌',
      value: 'CORE글로벌지역학(CORE Global Studies)',
    },
    {
      key: 'EICC',
      value: 'EICC학과(Department of English for International Conferences and Communication)',
    },
    {
      key: 'ELLT',
      value: 'ELLT학과(Department od English Linguistics & Language Technology)',
    },
    {
      key: 'EU',
      value: 'EU전공(EU Major)',
    },


  ]


  render() {
    return (
      <ReactSearchBox
      
        placeholder="Search"
        //value="Doe"
        data={this.data}
        callback={record => console.log(record)}
      />
    )
  }
}

