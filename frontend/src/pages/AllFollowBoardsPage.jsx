import React from 'react'
import axios from 'axios'
import { useEffect } from 'react'
import { useState } from 'react'
import BoardInfo from '../components/board/BoardInfo'

function AllFollowBoardsPage() {
  const [data, setData] = useState([])
  const [loading, setLoading] = useState(true)
  const token = localStorage.getItem('jwt_token')

  const fetchData = async () => {
    event.preventDefault()
    try {
      const response = await axios.get(
        `http://localhost:8080/api/followBoard/`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      setData(response.data)
    } catch (error) {
      console.error(error)
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
      <div className="m-12 space-y-8">
        {data.map((d) => (
          <BoardInfo
            key={d.board.boardId}
            boardId={d.board.boardId}
            boardIcon={d.board.iconUrl}
            boardDescription={d.board.description}
            boardName={d.board.boardName}
          />
        ))}
      </div>
    </>
  )
}

export default AllFollowBoardsPage
