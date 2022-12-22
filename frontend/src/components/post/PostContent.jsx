import React from 'react'
import { HeartIcon, ChatBubbleLeftIcon } from '@heroicons/react/24/solid'
import PropTypes from 'prop-types'

function PostContent({ title, content }) {
  return (
    <>
      <h1 className="text-white text-2xl tracking-[.35rem] mb-4">{title}</h1>
      <p className="text-tGray tracking-[.25rem]">{content}</p>
      <div className="flex mt-8 space-x-6">
        <button className="flex items-center space-x-2 hover:bg-card px-3 py-2 rounded-lg duration-150 hover:cursor-pointer">
          <HeartIcon className="h-6 w-6 text-rose-500" />
          <span className="text-white tracking-[.4rem]">喜歡</span>
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
}

export default PostContent
