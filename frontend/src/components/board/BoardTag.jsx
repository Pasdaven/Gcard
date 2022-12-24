import React from 'react'
import PropTypes from 'prop-types'

function BoardTag({ boardId, boardIcon, boardName }) {
  return (
    <button
      className="flex items-center space-x-4 bg-box rounded-2xl w-fit py-3 pl-4 pr-3 hover:bg-card duration-200"
      onClick={() => (location.href = `/board/${boardId}`)}
    >
      <span
        className="h-8 w-8 rounded-full bg-cover"
        style={{ backgroundImage: `url(${boardIcon})` }}
      ></span>
      <span className="text-white tracking-[.5rem] text-lg font-semibold">
        {boardName}
      </span>
    </button>
  )
}

BoardTag.propTypes = {
  boardId: PropTypes.number.isRequired,
  boardIcon: PropTypes.string.isRequired,
  boardName: PropTypes.string.isRequired,
}

export default BoardTag
