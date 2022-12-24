import React from 'react'
import BoardInfo from '../components/board/BoardInfo'
import SearchInput from '../components/search/SearchInput'
import PostPreview from '../components/post/PostPreview'
import { useEffect } from 'react'
import { useState } from 'react'
import axios from 'axios'
import { useParams } from 'react-router-dom'

function Board() {
  const { id } = useParams()
  const [pId, setPId] = useState('')
  const [pIcon, setPIcon] = useState('')
  const [pDescription, setPDescription] = useState('')
  const [pName, setPName] = useState('')
  const [loading, setLoading] = useState(true)
  const [data, setData] = useState([])

  async function fetchData() {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/post/board/${id}`
      )
      setData(response.data)
    } catch (error) {
      console.log(error)
    }
    try {
      const response = await axios.get(`http://localhost:8080/api/board/${id}`)
      setPId(response.data.boardId)
      setPIcon(response.data.iconUrl)
      setPDescription(response.data.description)
      setPName(response.data.boardName)
    } catch (error) {
      console.log(error)
    }
    setLoading(false)
  }

  useEffect(() => {
    fetchData()
  }, [])

  return loading ? (
    <></>
  ) : (
    <>
      <div className="m-12">
        <SearchInput />
      </div>
      <div className="m-12">
        <BoardInfo
          boardId={pId}
          boardIcon={pIcon}
          boardName={pName}
          boardDescription={pDescription}
        />
      </div>
      <div className="m-12">
        {data.map((d) => (
          <PostPreview
            key={d.postId}
            title={d.title}
            contentPreview={d.content}
            userIcon={d.user.imgUrl}
            userName={d.user.userName}
            boardName={d.board.boardName}
            boardIcon={d.board.iconUrl}
            userId={d.user.userId}
            boardId={d.board.boardId}
            postId={d.postId}
          />
        ))}
      </div>
    </>
  )
}

export default Board
