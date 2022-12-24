import React from 'react'
import { Routes, Route } from 'react-router-dom'
import AllFollowBoardsPage from '../pages/AllFollowBoardsPage'
import Board from '../pages/Board'

function BoardRoutes() {
  return (
    <Routes>
      <Route path="/create" element={<></>} />
      <Route path=":id" element={<Board />} />
      <Route path="/board/follow" element={<AllFollowBoardsPage />} />
    </Routes>
  )
}

export default BoardRoutes
