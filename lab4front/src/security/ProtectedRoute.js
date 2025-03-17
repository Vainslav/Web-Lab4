import React from "react"
import { Navigate, Outlet } from "react-router-dom"

export function ProtectedRoute ({ condition, redirectPath = "/" }) {
    return condition ? <Outlet /> : <Navigate to={redirectPath} replace />
}
