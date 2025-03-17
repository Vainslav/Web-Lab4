import React from "react"
import {ShootForm} from "./ShootForm"
import {Graph} from "./Graph"
import {ResultTable} from "./ResultTable"

import "./mainPage.css"
import {useNavigate} from "react-router-dom"
import {Button} from "belle"

export function MainPage(){
    const navigator = useNavigate()
    const handleExitButton = () => {
        sessionStorage.clear()
        navigator("/Lab4/")
    }
    return(
        <div className={"mainContainer"}>
            <div className={"exit-container"}>
                <Button onClick={handleExitButton} primary>ВЫШЕЛ С КАРТЫ</Button>
            </div>
            <div className={"content"}>
                <div className={"left-block"}>
                    <div>
                        <Graph/>
                    </div>
                    <ShootForm/>
                </div>
                <div>
                    <ResultTable/>
                </div>
            </div>
        </div>
    )
}