import React from "react";
import {useState} from "react";
import {useNavigate, Link} from "react-router-dom"
import toast from "react-hot-toast"

import "./Auth.css"

export function Login() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()

    const handleSubmit = (e) => {
        e.preventDefault()
        fetch("http://localhost:8080/Lab4/api/auth/login",{
            method: "POST",
            body: JSON.stringify({username: `${username}`, password: `${password}`}),
            headers:{
                'Content-Type': 'application/json;charset=utf-8',
            }
        })
            .then(response => {
                if (!response.ok){
                    response.text().then(response => {
                        toast.error(JSON.parse(response).message)
                    })
                    return
                }
                return response.text()
            }).then(responseText => {
            if (responseText === undefined){
                return
            }
            let responseJson = JSON.parse(responseText)
            sessionStorage.setItem("token", responseJson.jwtToken)
            toast.success(`Logged as: ${username}`)
            setTimeout(() => navigate("/Lab4/mainPage"), 1000)
        })
    };

    return (
        <div className="auth">
            <div className={"login-form"}>
                <h2>Тут логиниться</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Имя пользователя:</label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label>Пароль:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">ПОЕХАЛИ</button>
                </form>
                <Link to='/Lab4/auth/register'>Зарегаться</Link>
            </div>
        </div>
    );
}