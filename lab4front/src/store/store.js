import { configureStore } from '@reduxjs/toolkit'
import pointsReducer from "./PointSlice"

const store = configureStore({
    reducer: {
        points: pointsReducer,
    },
})

export default store