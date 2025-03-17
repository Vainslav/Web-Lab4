import React from "react";
import {useSelector} from "react-redux";

import "./mainPage.css"

export function ResultTable() {
    const points = useSelector(store => store.points.points)

    return (
        <div className={"table-div"}>
            <table className={"table"}>
                <thead>
                <tr>
                    <th>hit</th>
                    <th>x</th>
                    <th>y</th>
                    <th>r</th>
                    <th>Start Time</th>
                </tr>
                </thead>
                <tbody>
                {points.map((point, index) => (
                    <tr key={index}  >
                        <td>{point.hit ? 'Yes' : 'No'}</td>
                        <td>{point.x}</td>
                        <td>{point.y}</td>
                        <td>{point.r}</td>
                        <td>{new Date(point.startTime).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}