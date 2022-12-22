import React from 'react'
import { Route, Routes } from 'react-router-dom'
import NewPost from '../pages/NewPost'
import Post from '../pages/Post'

function PostRoutes() {
  return (
    <Routes>
      <Route path="/create" element={<NewPost />} />
      <Route path=":postId" element={<Post />} />
    </Routes>
  )
}

export default PostRoutes
