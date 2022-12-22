import React from 'react'
import { useState } from 'react'
import axios from 'axios'
import { HeartIcon, ChatBubbleLeftIcon } from '@heroicons/react/24/solid'
import { HeartIcon as HeartIconO } from '@heroicons/react/24/outline'
import PropTypes from 'prop-types'

function PostContent({ title, content, postId }) {
  const [likePost, setLikePost] = useState(false)

  const updateLikePost = async () => {
    try {
      await axios.post(
        `http://localhost:8080/api/likePosts/${postId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      setLikePost(!likePost)
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <>
      <h1 className="text-white text-2xl tracking-[.35rem] mb-4">{title}</h1>
      <p className="text-tGray tracking-[.25rem]">{content}</p>
      <div className="flex mt-8 space-x-6">
        <button
          className="flex items-center space-x-2 hover:bg-card px-3 py-2 rounded-lg duration-150 hover:cursor-pointer"
          onClick={() => updateLikePost()}
        >
          {likePost ? (
            <>
              <HeartIcon className="h-6 w-6 text-rose-500" />
              <span className="text-white tracking-[.4rem]">取消喜歡</span>
            </>
          ) : (
            <>
              <HeartIconO className="h-6 w-6 text-rose-500" />
              <span className="text-white tracking-[.4rem]">喜歡</span>
            </>
          )}
        </button>
        <button className="flex items-center space-x-2 hover:bg-card px-3 py-2 rounded-lg duration-150 hover:cursor-pointer">
          <ChatBubbleLeftIcon className="h-6 w-6 text-sky-500" />
          <span className="text-white tracking-[.4rem]">留言</span>
        </button>
      </div>
    </>
  )
}

PostContent.propTypes = {
  title: PropTypes.string.isRequired,
  content: PropTypes.string.isRequired,
  postId: PropTypes.number.isRequired,
}

export default PostContent
