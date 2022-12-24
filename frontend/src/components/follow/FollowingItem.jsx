import React from 'react'
import PropTypes from 'prop-types'

function FollowingItem({ userId, userIcon, name, followingCount, fansCount }) {
  return (
    <>
      <a href={`/user/${userId}`}>
        <div className="flex justify-between rounded-lg bg-box text-white py-8 px-12 text-xl tracking-widest mb-8 hover:drop-shadow-lg duration-300 ease-linear group hover:bg-card">
          <div className="flex items-center">
            <span
              className="h-12 w-12 mr-6 rounded-full bg-cover group-hover:scale-110 group-hover:mr-7 duration-300"
              style={{ backgroundImage: `url(${userIcon})` }}
            ></span>
            <p className="tracking-[.3rem] text-lg font-semibold">{name}</p>
          </div>

          <div className="flex">
            <div className="flex flex-col items-center justify-center px-12">
              <p>{followingCount}</p>
              <p className="text-tGray text-base">追蹤中</p>
            </div>

            <div className="flex flex-col items-center justify-center px-2">
              <p>{fansCount}</p>
              <p className="text-tGray text-base">粉絲</p>
            </div>
          </div>
        </div>
      </a>
    </>
  )
}
FollowingItem.propTypes = {
  userId: PropTypes.number.isRequired,
  userIcon: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  followingCount: PropTypes.number.isRequired,
  fansCount: PropTypes.number.isRequired,
}

export default FollowingItem
