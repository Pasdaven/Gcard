import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios'
import AllBoardItem from '../components/board/AllBoardItem'

function AllBoard() {
  const [data, setData] = useState([])

  const fetchData = async () => {
    event.preventDefault()
    try {
      const response = await axios.get('http://localhost:8080/api/board/')
      setData(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(() => {
    fetchData()
  }, [])

  return (
    <>
      <div
        className="grid grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4
    overflow-y-auto max-h-screen scrollbar-hide gap-12 p-12 justify-items-center"
      >
        {data.map((d) => (
          <AllBoardItem
            key={d.boardId}
            boardId={d.boardId}
            boardName={d.boardName}
            description={d.description}
            iconUrl={d.iconUrl}
          />
        ))}
      </div>
    </>
  )
}

export default AllBoard
