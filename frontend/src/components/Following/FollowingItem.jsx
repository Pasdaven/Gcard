import React from 'react'

function FollowingItem() {
  return (
    <>
      <div className="flex justify-between rounded-lg bg-box text-white py-8 px-12 text-xl tracking-widest">
        <div className="flex items-center">
          <img
            src="https://t3.ftcdn.net/jpg/02/17/04/38/360_F_217043893_C16NUEZ1x9xL6W3znWZNVn15TrIp4lc7.jpg"
            className="h-10 w-10 mr-4 rounded-full"
          ></img>
          <p>Dale Mitchelle</p>
        </div>

        <div className="flex">
          <div className="flex flex-col items-center justify-center px-12">
            <p>135</p>
            <p className="text-tGray text-base">追蹤中</p>
          </div>

          <div className="flex flex-col items-center justify-center px-2">
            <p>23</p>
            <p className="text-tGray text-base">粉絲</p>
          </div>
        </div>
      </div>
    </>
  )
}

export default FollowingItem
