import React from 'react'
import './Button.scss'

function Button(props) {
    const onClick = () => {
        props.onClick();
    }

    return (
        <div>
            <button className={props.value}
           >{props.name}</button>
        </div>
    )
}

export default Button
