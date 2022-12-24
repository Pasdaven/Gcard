import React from 'react'
import PropTypes from 'prop-types'
import BoardTag from '../board/BoardTag'

function PostPreview({
  postId,
  title,
  contentPreview,
  userIcon,
  userName,
  boardName,
  boardIcon,
  boardId,
}) {
  return (
    <a href={`/post/${postId}`} className="block">
      <div className="flex flex-col space-y-6 hover:ml-1 duration-500 delay-100 cursor-pointer mb-8">
        {/* User Info */}
        <div className="flex items-center space-x-5">
          <span
            className="h-12 w-12 rounded-full bg-cover"
            style={{ backgroundImage: `url(${userIcon})` }}
          ></span>
          <p className="text-white tracking-[.4rem] text-2xl font-semibold">
            {userName}
          </p>
        </div>
        {/* Content */}
        <div className="space-y-2">
          <h1 className="text-white tracking-[.4rem] text-xl">{title}</h1>
          <p className="text-tGray tracking-[.3rem] text-sm">
            {contentPreview}
          </p>
        </div>
        {/* Board Tag */}
        <BoardTag
          boardIcon={boardIcon}
          boardId={boardId}
          boardName={boardName}
        />
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
  boardName: PropTypes.string.isRequired,
  boardIcon: PropTypes.string.isRequired,
  boardId: PropTypes.number.isRequired,
}

export default PostPreview
