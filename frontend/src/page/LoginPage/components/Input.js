import React from 'react'

function Input(props) {

    const onChange = e => {
        props.onChange(e.target);
    }

    return (
        <div className="Input">
            <b>{props.placeholder}</b>
            <input 
            placeholder={props.placeholder}
            name={props.name}
            onChange={onChange} 
            value={props.value}/>
        </div>
    )
}

export default Input
