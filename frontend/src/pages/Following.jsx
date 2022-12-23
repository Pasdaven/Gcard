import React from 'react'
import FollowingItem from '../components/follow/FollowingItem'

function Following() {
  const auth = () => {
    if (!localStorage.getItem('jwt_token')) location.href = '/login'
    else return true
  }

  return (
    auth() && (
      <>
        <div className="pt-16 pr-8 space-y-4 ml-8 mr-8">
          <FollowingItem />
          <FollowingItem />
          <FollowingItem />
        </div>
      </>
    )
  )
}

export default Following
