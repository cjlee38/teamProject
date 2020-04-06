import React from 'react'

function Button(props) {
    const onClick = () => {
        props.onClick();
    }

    return (
        <div>
            <button onClick={onClick}>{props.name}</button>
        </div>
    )
}

export default Button
