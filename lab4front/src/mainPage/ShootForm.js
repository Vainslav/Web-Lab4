import React, {useEffect, useState} from "react"
import {Spinner} from "./components/Spinner"

import "./mainPage.css"
import toast from "react-hot-toast"
import { useDispatch, useSelector } from 'react-redux'
import {fetchPoints, setRadius} from "../store/PointSlice";

export function ShootForm() {
    const [x, setX] = useState(-2)
    const [y, setY] = useState(-3)
    const r = useSelector(store => store.points.radius)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(fetchPoints())
    }, [dispatch])

    const handleForm = (e) => {
        e.preventDefault()
        console.log({ x, y, r })
        fetch("http://localhost:8080/Lab4/api/shots",{
            method: "POST",
            body: JSON.stringify({x: `${x}`, y: `${y}`, r: `${r}`}),
            headers:{
                'Content-Type': 'application/json;charset=utf-8',
                'Authorization': `Bearer ${sessionStorage.getItem("token")}`
            }
        }).then(response =>{
            if (!response.ok){
                response.text().then(error =>{
                    toast.error(JSON.parse(error).message)
                })
                return
            }
            dispatch(setRadius(r))
        })
        dispatch(fetchPoints())
    }
    return (
        <div className={"form"}>
            <div className={"form-inputs"}>
                <div className={"input-group"}>
                    <label htmlFor={"x-select"}>X:</label>
                    <select name={"x"} id={"x-select"} value={x} onChange={(e) => setX(parseFloat(e.target.value))}>
                        <option value={-2}>-2</option>
                        <option value={-1.5}>-1.5</option>
                        <option value={-1}>-1</option>
                        <option value={-0.5}>-0.5</option>
                        <option value={0}>0</option>
                        <option value={0.5}>0.5</option>
                        <option value={1}>1</option>
                        <option value={1.5}>1.5</option>
                        <option value={2}>2</option>
                    </select>
                </div>
                <div className={"input-group"}>
                    <span>Y:</span>
                    <Spinner value={y} onChange={(newValue) => setY(newValue)}/>
                </div>
                <div className={"input-group"}>
                    <label htmlFor={"r-select"}>R:</label>
                    <select name={"r"} id={"r-select"} value={r} onChange={(e) => dispatch(setRadius(parseFloat(e.target.value)))}>
                        <option value={-2}>-2</option>
                        <option value={-1.5}>-1.5</option>
                        <option value={-1}>-1</option>
                        <option value={-0.5}>-0.5</option>
                        <option value={0}>0</option>
                        <option value={0.5}>0.5</option>
                        <option value={1}>1</option>
                        <option value={1.5}>1.5</option>
                        <option value={2}>2</option>
                    </select>
                </div>
            </div>
            <div className={"submit-container"}>
                <button type={"button"} className={"submit"} onClick={handleForm}>Отправить</button>
            </div>
        </div>
    )
}