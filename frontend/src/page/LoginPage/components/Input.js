import React from 'react'
import "./Input.scss"

function Input(props) {

    const onChange = e => {
        props.onChange(e.target);
    }

    return (
        <div className="Input">
            <b className="InputName">{props.name}</b>
            <input 
            className="InputBox"
            placeholder={props.placeholder}
            name={props.name}
            onChange={onChange} 
            value={props.value}/>
        </div>
    )
}

export default Input
