import React from 'react'
import { Route, Routes } from 'react-router-dom'

function UserRoutes() {
  return (
    <Routes>
      <Route path=":id" element={<></>} />
    </Routes>
  )
}

export default UserRoutes
