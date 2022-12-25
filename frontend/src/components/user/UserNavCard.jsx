import React from 'react'
import { useState } from 'react'
import axios from 'axios'
import {
  ChevronDownIcon,
  ChevronUpIcon,
  HeartIcon,
  ArrowRightOnRectangleIcon,
  ArrowLeftOnRectangleIcon,
  RectangleStackIcon,
  PlusCircleIcon,
  ShieldCheckIcon,
} from '@heroicons/react/24/outline'
import { useEffect } from 'react'

function UserNavCard() {
  const [openList, setOpenList] = useState(false)
  const [userData, setUserData] = useState()
  const [loading, setLoading] = useState(true)
  const [isAdmin, setIsAdmin] = useState(false)
  const isLogin = localStorage.getItem('jwt_token') ? true : false

  const fetchUserData = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/users/tokenId/', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
        },
      })
      setUserData(res.data)
    } catch (error) {
      console.log(error)
    }
    try {
      const res = await axios.get(
        'http://localhost:8080/api/users/checkIsAdmin',
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      setIsAdmin(res.data)
    } catch (error) {
      console.log(error)
    }
    setLoading(false)
  }

  useEffect(() => {
    fetchUserData()
  }, [])

  return isLogin ? (
    !loading && (
      <div
        className="flex flex-col bg-box rounded-lg px-5 py-3 cursor-pointer select-none space-y-6 w-fit items-center hover:shadow-2xl duration-300 group"
        onClick={() => setOpenList(!openList)}
      >
        <div className="flex space-x-3 items-center">
          <span
            className="h-10 w-10 rounded-full bg-cover"
            style={{
              backgroundImage: `url(${userData.imgUrl})`,
            }}
          ></span>
          <p className="text-white text-lg tracking-[.3rem]">
            {userData.userName}
          </p>
          {openList ? (
            <ChevronUpIcon className="h-4 w-4 text-white group-hover:-mt-1 duration-300" />
          ) : (
            <ChevronDownIcon className="h-4 w-4 text-white group-hover:-mb-1 duration-300" />
          )}
        </div>
        {openList && (
          <div className="space-y-5 pb-2">
            <a
              className="flex text-white tracking-[.3rem] space-x-2 hover:bg-card px-3 py-2 duration-100 w-fit rounded-lg"
              href="/board/follow"
            >
              <RectangleStackIcon className="h-6 w-6" />
              <span>收藏看板</span>
            </a>
            <a
              className="flex text-white tracking-[.3rem] space-x-2 hover:bg-card px-3 py-2 duration-100 w-fit rounded-lg"
              href="/post/like"
            >
              <HeartIcon className="h-6 w-6" />
              <span>按過的讚</span>
            </a>
            {isAdmin ? (
              <a
                className="flex text-white tracking-[.3rem] space-x-2 hover:bg-card px-3 py-2 duration-100 w-fit rounded-lg"
                href="/reviewBoard"
              >
                <ShieldCheckIcon className="h-6 w-6" />
                <span>審核看板申請</span>
              </a>
            ) : (
              <a
                className="flex text-white tracking-[.3rem] space-x-2 hover:bg-card px-3 py-2 duration-100 w-fit rounded-lg"
                href="/requestBoard"
              >
                <PlusCircleIcon className="h-6 w-6" />
                <span>申請看板</span>
              </a>
            )}

            <a
              className="flex text-white tracking-[.3rem] space-x-2 hover:bg-card px-3 py-2 duration-100 w-fit rounded-lg"
              href="/logout"
            >
              <ArrowRightOnRectangleIcon className="h-6 w-6" />
              <span>登出</span>
            </a>
          </div>
        )}
      </div>
    )
  ) : (
    <div
      className="flex bg-box rounded-lg px-8 py-3 cursor-pointer select-none justify-center items-center hover:shadow-2xl duration-300 space-x-3"
      onClick={() => {
        location.href = '/login'
      }}
    >
      <ArrowLeftOnRectangleIcon className="h-6 w-6 text-white" />
      <p className="text-white text-lg tracking-[.3rem]">登入</p>
    </div>
  )
}

export default UserNavCard
