import React, { Component } from 'react'
import ReactSearchBox from 'react-search-box'

export default class Search extends Component {

  data = [
    {
      key: '학부',
      value: '학부(Undergraduate)',
    },
    {
      key: '대학원',
      value: '대학원(Graduate School)',
    },
    {
      key: '경영',
      value: '경영대학원(Gradurate School of Business)',
    },
    {
      key: '통번역',
      value: '통번역대학원(Graduate School of Interpretation and Translation)',
    },
    {
      key: '교육',
      value: '교육대학원(Graduate School of Education)',
    },
    {
      key: '정치행정',
      value: '정치행정언론대학원(Graduate School of Politics, Government, and Communication)',
    },
    {
      key: '국제지역',
      value: '국제지역대학원(Graduate School of International and Area Studies)',
    },
    {
      key: '한국어문학',
      value: '한국어문화교육원(Center for Korean Language and Culture)',
    },
    {
      key: '법학',
      value: '법학전문대학원(Law School)',
    },

  ]

  data2 = [
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

