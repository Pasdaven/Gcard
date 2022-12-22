import React from 'react'
import { Route, Routes } from 'react-router-dom'
import User from '../pages/User'

function UserRoutes() {
  return (
    <Routes>
      <Route path=":userId" element={<User />} />
    </Routes>
  )
}

export default UserRoutes
