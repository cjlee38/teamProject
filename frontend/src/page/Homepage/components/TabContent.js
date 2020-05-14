import React, { Component, useState, useEffect } from 'react';
import TabList from './TabList';
import Table from './Table';
import Table2 from './Table2';
import Table3 from './Table3';
import Axios from 'axios';
import Table4 from '../components/Table4'



export default function TabContent1() {
    const [data, setData] = useState([{data:""}])
    const [data2, setData2] = useState([{data:""}])
    const [data3, setData3] = useState([{data:""}])
    // const [data2, setData2] = useState([
    //     { trow: '필수 과목' },
    // { trow: '미이수과목' },
    // { trow: '이수 과목' }    ]
    // )

    // const [data3, setData3] = useState([
        
    // ])

    useEffect(() => {
        Axios.get('http://localhost:1415/web/v1/checkCondition/try', {
            params: { userId: 1 }
        }).then(response => {
            console.log("res", response.data)
            let total = response.data.data.takenCredit.reduce(
                ( accumulator, currentValue ) => accumulator + currentValue,
                0
              );
            setData([{ trow: '졸업 학점', first_major: response.data.data.grdCredit[0], second_major:response.data.data.grdCredit[1], 
        sub_major:response.data.data.grdCredit[2], minor:response.data.data.grdCredit[3], out_door:response.data.data.grdCredit[4],
    liberal_arts:response.data.data.grdCredit[5], teaching:response.data.data.grdCredit[6], optional:response.data.data.grdCredit[7],
total_credit:response.data.data.grdCredit[8], average_score:2.0},
{ trow: '현재 학점', first_major: response.data.data.takenCredit[0], second_major:response.data.data.takenCredit[1], 
sub_major:response.data.data.takenCredit[2], minor:response.data.data.takenCredit[3], out_door:response.data.data.takenCredit[4],
liberal_arts:response.data.data.takenCredit[5], teaching:response.data.data.takenCredit[6], optional:response.data.data.takenCredit[7],
total_credit:total, average_score:response.data.data.averageScore},
{ trow: '졸업학점', first_major: response.data.data.remainCredit[0], second_major:response.data.data.remainCredit[1], 
sub_major:response.data.data.remainCredit[2], minor:response.data.data.remainCredit[3], out_door:response.data.data.remainCredit[4],
liberal_arts:response.data.data.remainCredit[5], teaching:response.data.data.remainCredit[6], optional:response.data.data.remainCredit[7],
total_credit:response.data.data.remainCredit[8], average_score:"-"}])
            setData2([{trow: "필수 과목", first_major:response.data.data.grdFirstMajorCourses.join('\n'), second_major:response.data.data.grdSecondMajorCourses.join('\n')
            , minor:"-", liberal_arts:response.data.data.grdLiberalArtsCourses.join('\n'), teaching:response.data.data.grdTeachingCourses
            , optional:"-"},
            {trow: "미이수 과목", first_major:response.data.data.remainFirstMajorCourses.join('\n'), second_major:response.data.data.remainSecondMajorCourses.join('\n')
            , minor:"-", liberal_arts:response.data.data.remainLiberalArtsCourses.join('\n'), teaching:response.data.data.remainTeachingCourses
            , optional:"-"},
            {trow: "이수 과목", first_major:response.data.data.takenFirstMajorCourses.join('\n'), second_major:response.data.data.takenSecondMajorCourses.join('\n')
            , minor:"-", liberal_arts:response.data.data.takenLiberArtsCourses.join('\n'), teaching:response.data.data.takenTeachingCourses.join('\n')
            , optional:response.data.data.takenFreeCourses.join('\n')}
        ])
        console.log(response.data.data.remainLiberalArtsCourses)
        setData3([{trow:response.data.data.userInfo}])



        })
    }, []
)
    console.log(data)

    return (
        <>
            <Table4 data={data3} />
            <div className="container up">
                <TabList>
                    <div label="학점" className="tab-content">
                        <Table data={data} />
                    </div>
                    <div label="이수 과목" className="tab-content">
                        <Table2 data={data2} />
                    </div>
                </TabList>
            </div>
        </>
    );

}




















 class TabContent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            std: '',
            password: '',
            result: {},
            data_in: false,
        }

        this.data = [
            { trow: '졸업학점' },
            { trow: '현재학점' },
            { trow: '남은 학점' }

        ];

        this.data2 = [{ trow: '필수 과목' },
        { trow: '미이수과목' },
        { trow: '이수 과목' }
        ];


        this.data3 = [
            { trow: '총 평점 2.0이상' },
            { trow: '전공/이중전공 졸업시험 합격여부' },
            { trow: '외국어인증(졸업인증) 완료 여부' }
        ];
        this.data4 = [
            { trow: "영어학, 융복합소프트웨어" }
        ]

    }

    componentDidUpdate(prevProps) {
        console.log("did update", prevProps, this.props);

    }
    static getDerivedStateFromProps(nextProps, prevState) {
        console.log("state form props next", nextProps, "prev", prevState)
        if (prevState.data_in !== nextProps.data_in) {
            return { data_in: nextProps.data_in };
        }

        return null;
    }
    componentDidMount() {
        Axios.get('http://localhost:1415/web/v1/checkCondition/try', {
            params: { userId: 1 }
        })
            .then((response) => {
                this.setState({ result: response.data.data, data_in: true }, () => {
                    console.log("did mount this.state는", this.state.result);
                    this.data2[0].first_major = this.state.result.remainCourses;
                    this.data2[1].first_major = this.state.result.takenCourses;
                    let lists = Array()
                    let take = this.state.result.takenCredit;
                    let grd = this.state.result.grdCredit;
                    let remain = this.state.result.remainCredit;

                    lists.push(grd);
                    lists.push(take);
                    lists.push(remain);

                    for (let i = 0; i < this.data.length; i++) {
                        this.data[i].first_major = lists[i][0];
                        this.data[i].second_major = lists[i][1];
                        this.data[i].sub_major = lists[i][2];
                        this.data[i].minor = lists[i][3];
                        this.data[i].out_door = lists[i][4];
                        this.data[i].liberal_arts = lists[i][5];
                        this.data[i].teaching = lists[i][6];
                        this.data[i].optional = lists[i][7];
                        this.data[i].total_credit = lists[i][8];
                    }

                    this.data[1].average_score = this.state.result.averageScore;
                    this.data[0].average_score = this.state.result.grdAverageScore;
                    this.data4 = { trow: this.state.result.userInfo[0] + this.state.result.userInfo[1] };
                    console.log("check:", this.data4);


                });
                // this.setState({data_in:true});
            })
            .catch(function (error) {
                console.log(error);
            });
        // this.setState({
        //     data : this.data
        // });
        console.log("asd", this.state.data);
    }

    render() {
        console.log(this.state.result);
        return (

            <>
                <Table4 data={this.data4} />
                <div className="container up">
                    <TabList>
                        <div label="학점" className="tab-content">
                            <Table data={this.data} />
                        </div>
                        <div label="이수 과목" className="tab-content">
                            <Table2 data={this.data2} />
                        </div>
                    </TabList>
                </div>
            </>
        );
    }
}