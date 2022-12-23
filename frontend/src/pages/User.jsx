import React from 'react'
import axios from 'axios'
import PostPreview from '../components/post/PostPreview'
import SearchInput from '../components/search/SearchInput'
import { useParams } from 'react-router-dom'
import UserInfoCard from '../components/user/UserInfoCard'
import { useState, useEffect } from 'react'

function User() {
  const { userId } = useParams()
  const [userData, setUserData] = useState()
  const [fansCount, setFansCount] = useState()
  const [followingCount, setFollowingCount] = useState()
  const [loading, setLoading] = useState(true)
  const [post, setPost] = useState()

  async function fetchData() {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/users/${userId}`
      )
      setUserData(response.data)

      const fansResponse = await axios.get(
        `http://localhost:8080/api/followUsers/fans/${userId}`
      )
      setFansCount(fansResponse.data.length)

      const followingResponse = await axios.get(
        `http://localhost:8080/api/followUsers/${userId}`
      )
      setFollowingCount(followingResponse.data.length)
    } catch (error) {
      console.log(error)
    }
    try {
      const response = await axios.get(
        `http://localhost:8080/api/post/user/${userId}`
      )
      setPost(response.data)
    } catch (error) {
      console.log(error)
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
      <div className="mx-12">
        <UserInfoCard
          userName={userData.userName}
          userIcon={userData.imgUrl}
          fansCount={fansCount}
          followingCount={followingCount}
        />
      </div>
      <div className="mx-12 space-y-12 mt-12">
        {post.map((data) => (
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

export default User
