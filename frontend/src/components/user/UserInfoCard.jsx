import React from 'react'
import { HeartIcon } from '@heroicons/react/24/solid'
import PropTypes from 'prop-types'

function userInfoCard({ userName, userIcon, fansCount, followingCount }) {
  return (
    <>
      <div className="flex w-full justify-between">
        {/* User Name and icon */}
        <div className="flex items-center space-x-6">
          <span
            className="h-14 w-14 rounded-full bg-cover group-hover:scale-105 duration-200 delay-100"
            style={{
              backgroundImage: `url(${userIcon})`,
            }}
          ></span>
          <p className="text-white tracking-[.7rem] text-3xl font-bold">
            {userName}
          </p>
        </div>
        {/* Follow info */}
        <div className="bg-box flex items-center w-fit py-5 px-5 space-x-12 rounded-xl">
          <div className="flex space-x-2 items-end">
            <p className="text-white text-3xl font-semibold tracking-[.5rem]">
              {followingCount}
            </p>
            <label className="text-tGray text-sm">追蹤中</label>
          </div>
          <div className="flex space-x-2 items-end">
            <p className="text-white text-3xl font-semibold tracking-[.5rem]">
              {fansCount}
            </p>
            <label className="text-tGray text-sm">粉絲</label>
          </div>
        </div>
      </div>
      <div className="flex w-full justify-end mt-4">
        <button className="flex text-white items-center space-x-2 px-5 py-2 border-gray-700 border-[1px] rounded-lg hover:bg-box duration-150">
          <HeartIcon className="h-6 w-6" />
          <span className="tracking-[.5rem]">追蹤</span>
        </button>
      </div>
    </>
  )
}

userInfoCard.propTypes = {
  userName: PropTypes.string.isRequired,
  userIcon: PropTypes.string.isRequired,
  fansCount: PropTypes.number.isRequired,
  followingCount: PropTypes.number.isRequired,
}

export default userInfoCard
