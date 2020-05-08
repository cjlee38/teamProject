import React from 'react';


export default class Course extends React.Component {
    constructor(props) {
        super(props)
    }
    // mapping = (List) => {
    //     this.render() {
    //         return (
                
    //         )
    //     }
    // }
    render() {
        return (
            <Form>
                <Form.Group controlId="exampleForm.SelectCustom">
                    <Form.Label>{this.props.name}</Form.Label>
                    <Form.Control as="select" custom>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </Form.Control>
                </Form.Group>
            </Form>);
    }
}