import React from 'react'
import { Route, Routes } from 'react-router-dom'
import AllLikePostPage from '../pages/AllLikePostPage'
import NewPost from '../pages/NewPost'
import Post from '../pages/Post'

function PostRoutes() {
  return (
    <Routes>
      <Route path="/create" element={<NewPost />} />
      <Route path=":postId" element={<Post />} />
      <Route path="/like" element={<AllLikePostPage />} />
    </Routes>
  )
}

export default PostRoutes
