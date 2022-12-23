import React from 'react'
import { useState } from 'react'
import {
  ChevronDownIcon,
  ChevronUpIcon,
  HeartIcon,
  ArrowRightOnRectangleIcon,
  RectangleStackIcon,
} from '@heroicons/react/24/outline'

function UserNavCard() {
  const [openList, setOpenList] = useState(false)

  return (
    <div
      className="flex flex-col bg-box rounded-lg px-5 pt-3 pb-3 cursor-pointer select-none space-y-6 w-fit items-center hover:shadow-2xl duration-300 group"
      onClick={() => setOpenList(!openList)}
    >
      <div className="flex space-x-3 items-center">
        <span
          className="h-10 w-10 rounded-full bg-cover"
          style={{
            backgroundImage: `url(https://avatars.githubusercontent.com/u/75478661?v=4)`,
          }}
        ></span>
        <p className="text-white text-lg tracking-[.3rem]">David Ho</p>
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
}

export default UserNavCard
