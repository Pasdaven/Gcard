import React from 'react'
import PropTypes from 'prop-types'

function PostPreview({
  postId,
  title,
  contentPreview,
  userIcon,
  userName,
  userId,
  boardName,
  boardIcon,
  boardId,
}) {
  return (
    <a href={`/post/${postId}`} className="block">
      <div className="flex flex-col space-y-6 hover:ml-2 duration-500 delay-100 cursor-pointer mb-8">
        {/* User Info */}
        <a
          className="flex items-center space-x-5 group"
          href={`/user/${userId}`}
        >
          <span
            className="h-12 w-12 rounded-full bg-cover group-hover:scale-105 duration-200 delay-100"
            style={{ backgroundImage: `url(${userIcon})` }}
          ></span>
          <p className="text-white tracking-[.7rem] text-2xl font-bold">
            {userName}
          </p>
        </a>
        {/* Content */}
        <div className="space-y-2">
          <h1 className="text-white tracking-[.4rem] text-xl">{title}</h1>
          <p className="text-tGray tracking-[.3rem] text-sm">
            {contentPreview}
          </p>
        </div>
        {/* Board Tag */}
        <a
          className="flex items-center space-x-2 bg-box rounded-2xl w-fit py-2 px-4 hover:bg-card duration-200"
          href={`/board/${boardId}`}
        >
          <span
            className="h-8 w-8 rounded-full bg-cover"
            style={{ backgroundImage: `url(${boardIcon})` }}
          ></span>
          <span className="text-white tracking-[.5rem] text-base font-bold">
            {boardName}
          </span>
        </a>
      </div>
      <hr className="border-gray-700" />
    </a>
  )
}

PostPreview.propTypes = {
  postId: PropTypes.number.isRequired,
  title: PropTypes.string.isRequired,
  contentPreview: PropTypes.string.isRequired,
  userIcon: PropTypes.string.isRequired,
  userName: PropTypes.string.isRequired,
  userId: PropTypes.number.isRequired,
  boardName: PropTypes.string.isRequired,
  boardIcon: PropTypes.string.isRequired,
  boardId: PropTypes.number.isRequired,
}

export default PostPreview
