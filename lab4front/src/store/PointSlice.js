import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import toast from "react-hot-toast";

export const fetchPoints = createAsyncThunk('points/fetchPoints', async () => {
    const response = await fetch('http://localhost:8080/Lab4/api/shots',{
        method: "GET",
        headers:{
            'Content-Type': 'application/json;charset=utf-8',
            'Authorization': `Bearer ${sessionStorage.getItem("token")}`
        }
    })
    if (!response.ok) {
        throw new Error('Failed to fetch points')
    }
    return response.json()
})

const pointsSlice = createSlice({
    name: 'points',
    initialState: {
        radius: 1,
        points: [],
        status: 'idle', // idle | loading | succeeded | failed
        error: null,
    },
    reducers: {
        setRadius(state, action) {
            let newRadius = action.payload
            if (newRadius > 0){
                state.radius = action.payload
            }
            else{
                toast.error("Radius must be greater than zero")
            }
        },
        fetchPoints
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchPoints.pending, (state) => {
                state.status = 'loading'
            })
            .addCase(fetchPoints.fulfilled, (state, action) => {
                state.status = 'succeeded'
                state.points = action.payload
            })
            .addCase(fetchPoints.rejected, (state, action) => {
                state.status = 'failed'
                state.error = action.error.message
            })
    },
})

export const { setRadius } = pointsSlice.actions
export default pointsSlice.reducer