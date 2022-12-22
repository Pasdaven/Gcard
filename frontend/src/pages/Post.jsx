import React from 'react'
import PostContent from '../components/post/PostContent'
import SearchInput from '../components/search/SearchInput'
import UserInfoCard from '../components/user/UserInfoCard'
// import { useParams } from 'react-router-dom'
import Comment from '../components/comment/Comment'

function Post() {
  // const { postId } = useParams()

  return (
    <>
      <div className="m-12">
        <SearchInput />
      </div>
      <div className="mx-12">
        <UserInfoCard
          userName="David"
          userIcon="https://avatars.githubusercontent.com/u/75478661?v=4"
          fansCount={10}
          followingCount={10}
        />
      </div>
      <div className="mx-12 mt-12">
        <PostContent />
        <hr className="border-gray-700 my-8" />
        <Comment />
      </div>
    </>
  )
}

export default Post
