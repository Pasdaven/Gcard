import React from 'react'
import { Routes, Route } from 'react-router-dom'
import Board from '../pages/Board'

function BoardRoutes() {
  return (
    <Routes>
      <Route path="/create" element={<></>} />
      <Route path=":id" element={<Board />} />
    </Routes>
  )
}

export default BoardRoutes
