import axios from 'axios'
import React from 'react'
import { useState, useEffect } from 'react'
import AllBoardItem from '../board/AllBoardItem'
import FollowingItem from '../follow/FollowingItem'
import PostPreview from '../post/PostPreview'
import PropTypes from 'prop-types'

function AllSearchResult({ keyword, searchTarget, loading, updateLoading }) {
  const [searchData, setSearchData] = useState()

  const fetchSearchData = async () => {
    let url
    if (searchTarget == 'post') {
      url = `http://localhost:8080/api/post/?keyword=${keyword}`
    } else if (searchTarget == 'user') {
      url = `http://localhost:8080/api/users/search/${keyword}`
    } else if (searchTarget == 'board') {
      url = `http://localhost:8080/api/board/search/${keyword}`
    }

    try {
      const response = await axios.get(url)
      setSearchData(response.data)
      updateLoading(false)
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    fetchSearchData()
  }, [searchTarget])

  return (
    !loading && (
      <>
        <div className="my-10">
          <p className="tracking-[.4rem] text-tGray text-sm">
            {`'${keyword}' ${
              searchData.length ? '的搜尋結果如下' : '查無任何結果'
            }`}
          </p>
        </div>
        {searchTarget == 'post' && (
          <div className="space-y-12">
            {searchData.map((data) => (
              <PostPreview
                key={data.postId}
                title={data.title}
                contentPreview={data.content}
                userIcon={data.user.imgUrl}
                userName={data.user.userName}
                boardName={data.board.boardName}
                boardIcon={data.board.iconUrl}
                userId={data.user.userId}
                boardId={data.board.boardId}
                postId={data.postId}
              />
            ))}
          </div>
        )}
        {searchTarget == 'user' &&
          searchData.map((data) => (
            <FollowingItem
              key={data.userId}
              userId={data.userId}
              name={data.userName}
              userIcon={data.imgUrl}
              followingCount={10}
              fansCount={10}
            />
          ))}
        {searchTarget == 'board' && (
          <div
            className="grid grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4
    overflow-y-auto max-h-screen scrollbar-hide gap-12 p-12 justify-items-center"
          >
            {searchData.map((data) => (
              <AllBoardItem
                key={data.boardId}
                boardId={data.boardId}
                boardName={data.boardName}
                description={data.description}
                iconUrl={data.iconUrl}
              />
            ))}
          </div>
        )}
      </>
    )
  )
}

AllSearchResult.propTypes = {
  keyword: PropTypes.string.isRequired,
  searchTarget: PropTypes.string.isRequired,
  loading: PropTypes.bool.isRequired,
  updateLoading: PropTypes.func.isRequired,
}

export default AllSearchResult
