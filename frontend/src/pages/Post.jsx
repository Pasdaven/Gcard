import React from 'react'
import { useEffect, useState } from 'react'
import axios from 'axios'
import PostContent from '../components/post/PostContent'
import SearchInput from '../components/search/SearchInput'
import UserInfoCard from '../components/user/UserInfoCard'
import { useParams } from 'react-router-dom'
import Comment from '../components/comment/Comment'
import BoardTag from '../components/board/BoardTag'

function Post() {
  const { postId } = useParams()
  const [postData, setPostData] = useState()
  const [loading, setLoading] = useState(true)

  const fetchPostData = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/post/${postId}`
      )
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
      <div className="mx-12">
        <UserInfoCard
          userName={postData.user.userName}
          userIcon={postData.user.imgUrl}
          fansCount={10}
          followingCount={10}
        />
      </div>
      <div className="mx-12">
        <BoardTag
          boardId={postData.board.boardId}
          boardIcon={postData.board.iconUrl}
          boardName={postData.board.boardName}
        />
      </div>
      <div className="mx-12 mt-12">
        <PostContent title={postData.title} content={postData.content} />
        <hr className="border-gray-700 my-8" />
        <Comment />
      </div>
    </>
  )
}

export default Post
