import React from 'react'
import { HeartIcon } from '@heroicons/react/24/solid'
import { useState } from 'react'

function BoardInfo() {
  const [showHeart, setShowHeart] = useState(false)
  return (
    <>
      <div className="bg-box h-auto rounded-lg flex">
        <div className="flex flex-col p-8 justify-center items-center">
          <div
            className="h-16 w-16 bg-cover bg-center rounded-full"
            style={{
              backgroundImage: `url("https://storage.googleapis.com/image.blocktempo.com/2022/11/ethereum-logo-portrait-black-gray-1.png")`,
            }}
          ></div>
          <div className="text-white tracking-[.4rem] ml-2 mt-2 text-2xl">
            ETH
          </div>
        </div>
        <div className="flex flex-col w-full justify-between space-y-4 pt-8 pb-4">
          <p className="text-white tracking-[.4rem] pr-12">
            以太坊(Ethereum)是一個去中心化的於比特幣．以太坊使用最多的區塊鏈．台．以太幣(ETH)是以太坊的原生加密貨幣．截至2021年12月，以太幣台．以太幣(ETH)是以太坊的原生加密貨幣．截至2021年12月，以太幣台．以太幣有智能型合約功能的公共區塊鏈平台．以太幣(E有智能型合約功能的公共區塊鏈平台．以太幣(E有智能型合約功能的公共區塊鏈平台．以太幣(E有智能型合約功能的公共區塊鏈平台．以太幣(E密貨幣．截至2021年12月，以太幣台．以太幣(ETH)密貨幣．截至2021年12月，以太幣台．以太幣(ETH)
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

export default BoardInfo
