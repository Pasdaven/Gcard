import React from 'react'
import FollowingItem from '../components/follow/FollowingItem'
import { useEffect } from 'react'
import { useState } from 'react'
import axios from 'axios'

function Following() {
  const [userData, setUserData] = useState()
  const [loading, setLoading] = useState(true)
  const token = localStorage.getItem('jwt_token')

  const auth = () => {
    if (!localStorage.getItem('jwt_token')) location.href = '/login'
    else return true
  }

  async function fetchUserData() {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/followUsers/token`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      console.log(response.data)
      setUserData(response.data)

      setLoading(false)
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    fetchUserData()
  }, [])

  return auth() && loading ? (
    <></>
  ) : (
    <>
      <div className="pt-16 pr-8 space-y-4 ml-8 mr-8">
        {userData.map((d) => (
          <FollowingItem
            key={d.followUserEntity.followed.userId}
            userId={d.followUserEntity.followed.userId}
            name={d.followUserEntity.followed.userName}
            userIcon={d.followUserEntity.followed.imgUrl}
            followingCount={d.followedCount}
            fansCount={d.followerCount}
          />
        ))}
      </div>
    </>
  )
}

export default Following
