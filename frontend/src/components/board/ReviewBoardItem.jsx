import React from 'react'
import PropTypes from 'prop-types'
import { CheckIcon, NoSymbolIcon } from '@heroicons/react/24/outline'

function ReviewBoardItem({
  applicationId,
  boardIcon,
  boardName,
  boardDescription,
  handleReviewFunc,
}) {
  return (
    <div className="space-y-4">
      <div className="bg-box h-auto rounded-lg flex">
        <div className="flex flex-col p-8 justify-center items-center">
          <div
            className="h-16 w-16 bg-cover bg-center rounded-full"
            style={{
              backgroundImage: `url(${boardIcon})`,
            }}
          ></div>
          <div className="text-white tracking-[.4rem] ml-2 mt-2 text-2xl">
            {boardName}
          </div>
        </div>
        <div className="flex flex-col w-full justify-between space-y-4 pt-8 pb-4">
          <p className="text-white tracking-[.4rem] pr-12">
            {boardDescription}
          </p>
        </div>
      </div>
      <div className="flex space-x-5 justify-end">
        <button
          className="bg-red-500 text-white px-4 py-3 rounded-lg flex space-x-3 items-center hover:bg-red-400 duration-150"
          onClick={() => handleReviewFunc(applicationId, 'rejected')}
        >
          <NoSymbolIcon className="h-6 w-6" />
          <span className="tracking-[.3rem]">拒絕</span>
        </button>
        <button
          className="bg-main text-white px-4 py-3 rounded-lg flex space-x-3 items-center hover:hover:bg-indigo-400 duration-150"
          onClick={() => handleReviewFunc(applicationId, 'approved')}
        >
          <CheckIcon className="h-6 w-6" />
          <span className="tracking-[.3rem]">通過</span>
        </button>
      </div>
    </div>
  )
}

ReviewBoardItem.propTypes = {
  boardIcon: PropTypes.string.isRequired,
  boardName: PropTypes.string.isRequired,
  boardDescription: PropTypes.string.isRequired,
}

export default ReviewBoardItem
