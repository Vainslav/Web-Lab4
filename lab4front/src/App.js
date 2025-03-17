import React, {useState} from "react";
import {
    BrowserRouter as Router,
    Routes,
    Route,
} from "react-router-dom";
import {Toaster} from 'react-hot-toast'
import {MainPage} from "./mainPage/MainPage"
import {Login} from "./auth/Login"
import {Register} from "./auth/Register"


function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false)
    if (sessionStorage.getItem("token") != null && isAuthenticated === false){
        console.log(isAuthenticated)
        setIsAuthenticated(true)
    }
    return (
        <div>
            <Toaster/>
            <Router>
                <Routes>
                    <Route path="/Lab4/" element={<Login/>}/>
                    <Route path="/Lab4/auth/">
                        <Route index element={<Login/>} />
                        <Route path="login" element={<Login />} />
                        <Route path="register" element={<Register />} />
                    </Route>
                    <Route path="/Lab4/mainPage" element={<MainPage />}/>
                </Routes>
            </Router>
        </div>
    )
}

export default App;
