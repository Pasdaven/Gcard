import React from 'react'
import {
  PencilSquareIcon,
  UserIcon,
  RectangleStackIcon,
} from '@heroicons/react/24/solid'
import PropTypes from 'prop-types'

function SearchFilter({ searchTarget, setSearchTarget }) {
  return (
    <div className="flex space-x-4">
      <button
        className={`px-4 py-3 duration-200 rounded-lg text-white tracking-[.3rem] flex items-center space-x-3 ${
          searchTarget == 'post' ? 'bg-main' : 'bg-box hover:bg-card'
        }`}
        onClick={() => setSearchTarget('post')}
      >
        <PencilSquareIcon className="h-5 w-5" />
        <span>貼文</span>
      </button>
      <button
        className={`px-4 py-3 duration-200 rounded-lg text-white tracking-[.3rem] flex items-center space-x-3 ${
          searchTarget == 'user' ? 'bg-main' : 'bg-box hover:bg-card'
        }`}
        onClick={() => setSearchTarget('user')}
      >
        <UserIcon className="h-5 w-5" />
        <span>使用者</span>
      </button>
      <button
        className={`px-4 py-3 duration-200 rounded-lg text-white tracking-[.3rem] flex items-center space-x-3 ${
          searchTarget == 'board' ? 'bg-main' : 'bg-box hover:bg-card'
        }`}
        onClick={() => setSearchTarget('board')}
      >
        <RectangleStackIcon className="h-5 w-5" />
        <span>看板</span>
      </button>
    </div>
  )
}

SearchFilter.propTypes = {
  searchTarget: PropTypes.string.isRequired,
  setSearchTarget: PropTypes.func.isRequired,
}

export default SearchFilter
