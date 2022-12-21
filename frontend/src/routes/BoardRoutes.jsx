import React from 'react'
import { Routes, Route } from 'react-router-dom'

function BoardRoutes() {
  return (
    <Routes>
      <Route path="/create" element={<></>} />
      <Route path=":id" element={<></>} />
    </Routes>
  )
}

export default BoardRoutes
