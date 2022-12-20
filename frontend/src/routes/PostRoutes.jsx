import React from 'react'
import { Route, Routes } from 'react-router-dom'

function PostRoutes() {
  return (
    <Routes>
      <Route path="/create" element={<></>} />
      <Route path=":id" element={<></>} />
    </Routes>
  )
}

export default PostRoutes
