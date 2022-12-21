import React from 'react'
import { HeartIcon } from '@heroicons/react/24/solid'
import { useState } from 'react'
import PropTypes from 'prop-types'

function BoardInfo({ priceIcon, priceName, priceDescription }) {
  const [showHeart, setShowHeart] = useState(false)
  return (
    <>
      <div className="bg-box h-auto rounded-lg flex">
        <div className="flex flex-col p-8 justify-center items-center">
          <div
            className="h-16 w-16 bg-cover bg-center rounded-full"
            style={{
              backgroundImage: `url(${priceIcon})`,
            }}
          ></div>
          <div className="text-white tracking-[.4rem] ml-2 mt-2 text-2xl">
            {priceName}
          </div>
        </div>
        <div className="flex flex-col w-full justify-between space-y-4 pt-8 pb-4">
          <p className="text-white tracking-[.4rem] pr-12">
            {priceDescription}
          </p>
          <div className="flex justify-end mr-6">
            {showHeart ? (
              <HeartIcon
                className="h-6 w-6 text-red-500 hover:cursor-pointer active:scale-75"
                onClick={() => setShowHeart(!showHeart)}
              />
            ) : (
              <HeartIcon
                className="h-6 w-6 text-white hover:cursor-pointer active:scale-75"
                onClick={() => setShowHeart(!showHeart)}
              />
            )}
          </div>
        </div>
      </div>
    </>
  )
}

BoardInfo.propTypes = {
  priceIcon: PropTypes.string.isRequired,
  priceDescription: PropTypes.string.isRequired,
  priceName: PropTypes.string.isRequired,
}

export default BoardInfo
