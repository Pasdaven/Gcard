import React from 'react'
import PropTypes from 'prop-types'

function UserAvatarName({ userId, userIcon, userName }) {
  return (
    <button
      className="flex items-center space-x-5"
      onClick={() => (location.href = `/user/${userId}`)}
    >
      <span
        className="h-12 w-12 rounded-full bg-cover"
        style={{
          backgroundImage: `url(${userIcon})`,
        }}
      ></span>
      <p className="text-white tracking-[.7rem] text-2xl font-bold">
        {userName}
      </p>
    </button>
  )
}

UserAvatarName.propTypes = {
  userId: PropTypes.number.isRequired,
  userIcon: PropTypes.string.isRequired,
  userName: PropTypes.string.isRequired,
}

export default UserAvatarName
