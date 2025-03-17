import React from "react";
import {useState} from "react"
import {Button} from "belle"

import "./Spinner.css"

export function Spinner({ value, onChange }) {
    const MIN_VALUE = -3
    const MAX_VALUE = 5

    const increment = () => {
        const newValue = value < MAX_VALUE ? value + 1 : MIN_VALUE
        onChange(newValue)
    }

    const decrement = () => {
        const newValue = value > MIN_VALUE ? value - 1 : MAX_VALUE
        onChange(newValue)
    }

    return (
        <div className={"spinner-container"}>
            <div className={"spinner-value"}>
                {value}
            </div>
            <div>
                <Button onClick={decrement}> Decrement </Button>
                <Button onClick={increment}> Increment </Button>
            </div>
        </div>
    )
}