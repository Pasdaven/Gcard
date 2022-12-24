import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios'
import { HeartIcon, ChatBubbleLeftIcon } from '@heroicons/react/24/solid'
import {
  PaperAirplaneIcon,
  ChatBubbleLeftIcon as ChatBubbleLeftIconO,
  HeartIcon as HeartIconO,
} from '@heroicons/react/24/outline'
import PropTypes from 'prop-types'

function PostContent({ title, content, postId }) {
  const [likePost, setLikePost] = useState(false)
  const [showCommentBox, setShowCommentBox] = useState(false)
  const [commentContent, setCommentContent] = useState('')
  const [ownAvatar, setOwnAvatar] = useState('')

  const auth = () => {
    if (!localStorage.getItem('jwt_token')) location.href = '/login'
    else return true
  }

  const fetchData = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/post/like/${postId}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      setLikePost(res.data)
    } catch (error) {
      console.log(error)
    }
    const res = await axios.get('http://localhost:8080/api/users/tokenId/', {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
      },
    })
    setOwnAvatar(res.data.imgUrl)
  }

  const updateLikePost = async () => {
    auth()
    try {
      if (likePost) {
        await axios.delete(`http://localhost:8080/api/likePosts/${postId}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        })
      } else {
        await axios.post(
          `http://localhost:8080/api/likePosts/${postId}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
            },
          }
        )
      }
      fetchData()
    } catch (error) {
      console.log(error)
    }
  }

  const createComment = async () => {
    try {
      const res = await axios.post(
        'http://localhost:8080/api/comments/',
        {
          content: commentContent,
          post: {
            postId: postId,
          },
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      if (res.status === 200) {
        location.reload()
      } else {
        console.log('error')
      }
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    fetchData()
  }, [])

  return (
    <>
      <h1 className="text-white text-2xl tracking-[.35rem] mb-4">{title}</h1>
      <p className="text-tGray tracking-[.25rem]">{content}</p>
      <div className="flex mt-8 space-x-6 -mx-4">
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
        <button
          className="flex items-center space-x-2 hover:bg-card px-3 py-2 rounded-lg duration-150 hover:cursor-pointer"
          onClick={() => {
            auth() && setShowCommentBox(!showCommentBox)
          }}
        >
          {showCommentBox ? (
            <ChatBubbleLeftIcon className="h-6 w-6 text-sky-500" />
          ) : (
            <ChatBubbleLeftIconO className="h-6 w-6 text-sky-500" />
          )}

          <span className="text-white tracking-[.4rem]">留言</span>
        </button>
      </div>
      {showCommentBox && (
        <div className="mt-4 relative flex items-center">
          <input
            type="text"
            className="bg-box rounded-lg w-full h-20 pl-16 pr-16 text-white tracking-[.3rem] outline-main"
            placeholder="輸入留言..."
            value={commentContent}
            onChange={(e) => setCommentContent(e.target.value)}
            onKeyDown={(e) => {
              e.key === 'Enter' && createComment()
            }}
          />
          <span
            className="h-10 w-10 rounded-full bg-cover absolute left-3"
            style={{
              backgroundImage: `url(${ownAvatar})`,
            }}
          ></span>
          <button
            className="rounded-full hover:bg-gray-700 duration-100 absolute right-3 p-2 outline-none"
            onClick={() => createComment()}
          >
            <PaperAirplaneIcon className="h-8 w-8 text-main" />
          </button>
        </div>
      )}
    </>
  )
}

PostContent.propTypes = {
  title: PropTypes.string.isRequired,
  content: PropTypes.string.isRequired,
  postId: PropTypes.number.isRequired,
}

export default PostContent
