import React from 'react'
import BoardInfo from '../components/board/BoardInfo'
import SearchInput from '../components/search/SearchInput'
import PostPreview from '../components/post/PostPreview'
// import { useParams } from 'react-router-dom'

function Board() {
  //   const { id } = useParams()
  return (
    <>
      <div className="m-12">
        <SearchInput />
      </div>
      <div className="m-12">
        <BoardInfo
          priceIcon={
            'https://storage.googleapis.com/image.blocktempo.com/2022/11/ethereum-logo-portrait-black-gray-1.png'
          }
          priceDescription={
            '以太坊(Ethereum)是一個去中心化的於比特幣．以太坊使用最多的區塊鏈．台．以太幣(ETH)是以太坊的原生加密貨幣．截至2021年12月，以太幣台．以太幣(ETH)是以太坊的原生加密貨幣．截至2021年12月，以太幣台．以太幣有智能型合約功能的公共區塊鏈平台．以太幣(E有智能型合約功能的公共區塊鏈平台．以太幣(E有智能型合約功能的公共區塊鏈平台．以太幣(E有智能型合約功能的公共區塊鏈平台．以太幣(E密貨幣．截至2021年12月，以太幣台．以太幣(ETH)密貨幣．截至2021年12月，以太幣台．以太幣(ETH)'
          }
          priceName={'ETH'}
        />
      </div>
      <div className="m-12">
        <PostPreview
          title="ETH 2.0 the merge is coming"
          contentPreview="ETH 2.0 the merge is coming, let's go to Bybit and hold eth!"
          userIcon="https://avatars.githubusercontent.com/u/75478661?v=4"
          userName="David"
          boardName="ETH"
          boardIcon="https://icons.iconarchive.com/icons/cjdowner/cryptocurrency-flat/1024/Ethereum-ETH-icon.png"
          userId={4}
          boardId={3}
          postId={5}
        />
        <PostPreview
          title="ETH 2.0 the merge is coming"
          contentPreview="ETH 2.0 the merge is coming, let's go to Bybit and hold eth!"
          userIcon="https://avatars.githubusercontent.com/u/75478661?v=4"
          userName="David"
          boardName="ETH"
          boardIcon="https://icons.iconarchive.com/icons/cjdowner/cryptocurrency-flat/1024/Ethereum-ETH-icon.png"
          userId={4}
          boardId={3}
          postId={5}
        />
      </div>
    </>
  )
}

export default Board
