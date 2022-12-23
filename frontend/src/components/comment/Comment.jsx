import React from 'react'
import UserAvatarName from '../user/UserAvatarName'
import PropTypes from 'prop-types'

function Comment({ userId, userIcon, userName, commentText }) {
  return (
    <div className="space-y-5">
      <UserAvatarName userId={userId} userName={userName} userIcon={userIcon} />
      <p className="text-tGray tracking-[.25rem]">{commentText}</p>
    </div>
  )
}

Comment.propTypes = {
  userId: PropTypes.number.isRequired,
  userIcon: PropTypes.string.isRequired,
  userName: PropTypes.string.isRequired,
  commentText: PropTypes.string.isRequired,
}

export default Comment
