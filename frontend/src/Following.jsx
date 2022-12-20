import React from 'react'
import FollowingItem from './components/Following/FollowingItem'

function Following() {
  return (
    <>
      <div className="pt-16 pr-8 space-y-8 w-full">
        <FollowingItem />
        <FollowingItem />
        <FollowingItem />
      </div>
    </>
  )
}

export default Following
