import axios from 'axios'
import React from 'react'
import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import PostPreview from '../components/post/PostPreview'
import SearchInput from '../components/search/SearchInput'

function SearchResult() {
  const { keyword } = useParams()
  const [postData, setPostData] = useState()
  const [loading, setLoading] = useState(true)

  const fetchSearchData = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/post/?keyword=${keyword}`
      )
      setPostData(response.data)
      setLoading(false)
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    fetchSearchData()
  }, [])
  return loading ? (
    <></>
  ) : (
    <>
      <div className="m-12">
        <SearchInput />
      </div>
      <div className="mx-12 my-10">
        <p className="tracking-[.4rem] text-tGray text-sm">
          {keyword} {postData.length ? '的搜尋結果如下' : '查無任何結果'}
        </p>
      </div>
      <div className="mx-12 space-y-12">
        {postData.map((data) => (
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
    </>
  )
}

export default SearchResult
