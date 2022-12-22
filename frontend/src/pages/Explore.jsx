import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios'
import SearchInput from '../components/search/SearchInput'
import PostPreview from '../components/post/PostPreview'

function Explore() {
  const [postData, setPostData] = useState()
  const [loading, setLoading] = useState(true)

  const fetchPostData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/post/latest')
      setPostData(response.data)
      setLoading(false)
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    fetchPostData()
  }, [])

  return loading ? (
    <></>
  ) : (
    <>
      <div className="m-12">
        <SearchInput />
      </div>
      <div className="mx-12 space-y-12">
        {postData.map((data) => (
          <PostPreview
            key={data.postId}
            title={data.title}
            contentPreview={data.content}
            userIcon={data.user.imgUrl}
            userName={data.user.userName}
            boardName={data.board.boardName}
            boardIcon={data.board.iconUrl}
            userId={data.user.userId}
            boardId={data.board.boardId}
            postId={data.postId}
          />
        ))}
      </div>
    </>
  )
}

export default Explore
