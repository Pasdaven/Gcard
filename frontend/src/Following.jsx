import React from 'react'
import FollowingItem from './components/Following/FollowingItem'

function Following() {
  return (
    <>
      <div className="pt-8 pr-8 space-y-4 ml-8 mr-8">
        <FollowingItem />
        <FollowingItem />
        <FollowingItem />
      </div>
    </>
  )
}

export default Following
