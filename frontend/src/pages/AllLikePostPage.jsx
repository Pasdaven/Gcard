import React from 'react'
import axios from 'axios'
import { useEffect } from 'react'
import { useState } from 'react'
import SearchInput from '../components/search/SearchInput'
import { RectangleStackIcon } from '@heroicons/react/24/outline'
import PostPreview from '../components/post/PostPreview'

function AllLikePostPage() {
  const [data, setData] = useState([])
  const [loading, setLoading] = useState(true)
  const token = localStorage.getItem('jwt_token')

  const fetchData = async () => {
    event.preventDefault()
    try {
      const response = await axios.get(`http://localhost:8080/api/likePosts/`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      console.log(response.data)
      setData(response.data)
    } catch (error) {
      console.error(error)
    }
    setLoading(false)
  }

  useEffect(() => {
    fetchData()
  }, [])

  return loading ? (
    <></>
  ) : (
    <>
      <div className="m-12">
        <SearchInput />
      </div>
      <div className="text-white m-12 text-2xl tracking-[.4rem] flex space-x-2 items-center">
        <RectangleStackIcon className="h-7 w-7" />
        <h1>按過的讚</h1>
      </div>
      <div className="m-12 space-y-8">
        {data.map((d) => (
          <PostPreview
            key={d.post.postId}
            postId={d.post.postId}
            title={d.post.title}
            contentPreview={d.post.content}
            userIcon={d.post.user.imgUrl}
            userName={d.post.user.userName}
            boardName={d.post.board.boardName}
            boardIcon={d.post.board.iconUrl}
            boardId={d.post.board.boardId}
          />
        ))}
      </div>
    </>
  )
}

export default AllLikePostPage
