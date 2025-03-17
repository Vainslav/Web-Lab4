import React from "react";
import {useDispatch, useSelector} from "react-redux";
import toast from "react-hot-toast";
import {fetchPoints} from "../store/PointSlice";

export function Graph() {
    const points = useSelector((state) => state.points.points)
    const r = useSelector(state => state.points.radius)

    const dispatch = useDispatch()

    const handleSvgButton = (e) => {
        const svg = document.getElementById("svg");
        const rect = svg.getBoundingClientRect();

        // Calculate x and y based on click position relative to SVG
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;

        fetch("http://localhost:8080/Lab4/api/shots",{
            method: "POST",
            body: JSON.stringify({x: `${(x*r/100-r*1.5)}`, y: `${(150*r - y*r)/100}`, r: `${r}`}),
            headers:{
                'Content-Type': 'application/json;charset=utf-8',
                'Authorization': `Bearer ${sessionStorage.getItem("token")}`
            }
        }).then(response =>{
            if (!response.ok){
                response.text().then(error =>{
                    toast.error(JSON.parse(error).message)
                })
            }
        })
        dispatch(fetchPoints())
        dispatch(fetchPoints())
    }
    return<svg id="svg" height="300" width="300" xmlns="http://www.w3.org/2000/svg" onClick={handleSvgButton}>
        <rect x="100" y="50" width="50" height="100" fill="#3399ff"/>

        <polygon fill="#3399ff" points="150,50 200,150 150,150"/>

        <path d="M 200 150 A 50 50, 0, 0, 1, 150 200 L 150 150  Z" fill="#3399ff"/>

        <line stroke="gray" x1="0" x2="300" y1="150" y2="150"/>
        <line stroke="gray" x1="150" x2="150" y1="0" y2="300"/>
        <polygon fill="black" points="150,0 144,15 156,15" stroke="black"/>
        <polygon fill="black" points="300,150 285,156 285,144" stroke="black"/>


        <line stroke="gray" x1="200" x2="200" y1="155" y2="145"/>
        <line stroke="gray" x1="250" x2="250" y1="155" y2="145"/>

        <line stroke="gray" x1="50" x2="50" y1="155" y2="145"/>
        <line stroke="gray" x1="100" x2="100" y1="155" y2="145"/>

        <line stroke="gray" x1="145" x2="155" y1="100" y2="100"/>
        <line stroke="gray" x1="145" x2="155" y1="50" y2="50"/>

        <line stroke="gray" x1="145" x2="155" y1="200" y2="200"/>
        <line stroke="gray" x1="145" x2="155" y1="250" y2="250"/>


        <text fill="black" x="195" y="140">R/2</text>
        <text fill="black" x="248" y="140">R</text>

        <text fill="black" x="40" y="140">-R</text>
        <text fill="black" x="90" y="140">-R/2</text>

        <text fill="black" x="160" y="105">R/2</text>
        <text fill="black" x="160" y="55">R</text>

        <text fill="black" x="160" y="205">-R/2</text>
        <text fill="black" x="160" y="255">-R</text>

        <text fill="black" x="160" y="10">Y</text>
        <text fill="black" x="290" y="140">X</text>


        <circle cx="150" cy="150" id="target-dot" r="0" stroke="black" fill="black"></circle>
        {points.map((point, index) => {
            const { x, y, hit } = point
            const cx = 150 + (x * 100) / r
            const cy = 150 - (y * 100) / r

            return (
                <circle
                    key={index}
                    cx={cx}
                    cy={cy}
                    className="dot"
                    id="dot"
                    r="4"
                    stroke={hit ? "green" : "red"}
                    fill={hit ? "green" : "red"}
                />
            )
        })}
    </svg>
}