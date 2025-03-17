import React from "react";
import {useState} from "react"
import {Link, useNavigate} from "react-router-dom"
import toast from "react-hot-toast"

import "./Auth.css"

export function Register() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const navigate = useNavigate()

    const handleSubmit = (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            toast.error('Passwords do not match!')
            return
        }
        if (!/^[a-zA-Z0-9а-яА-Я]/.test(username)){
            toast.error("Username can contain only Cirilic or English letters and numbers")
            return
        }
        if (!/^[a-zA-Z0-9а-яА-Я!@$*#]/.test(password)){
            toast.error("Password can contain only Cirilic or English letters, numbers and !@$*#")
            return
        }
        fetch("http://localhost:8080/Lab4/api/auth/register",{
            method: "POST",
            body: JSON.stringify({username: `${username}`, password: `${password}`}),
            headers:{
                'Content-Type': 'application/json;charset=utf-8'
            }
        })
            .then(response => {
                if (!response.ok){
                    response.text().then(response => {
                        toast.error(JSON.parse(response).message)
                    })
                    return;
                }
                toast.success(`Registered user: ${username}`)
                navigate('/Lab4/auth/login')
            })
    };

    return (
        <div className="auth">
            <div className={"login-form"}>
                <h2>Регаться тут</h2>
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
                    <div>
                        <label>Подтверждение пароля:</label>
                        <input
                            type="password"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit">Зарегаться</button>
                </form>
                <Link to='/Lab4/auth/login'>Перехотелось регаться</Link>
            </div>
        </div>
    );
}