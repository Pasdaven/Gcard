import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios'
import { ShieldCheckIcon } from '@heroicons/react/24/outline'
import ReviewBoardItem from '../components/board/ReviewBoardItem'

function ReviewBoard() {
  const [isAdmin, setIsAdmin] = useState(false)
  const [boardData, setBoardData] = useState()
  const [loading, setLoading] = useState(true)

  const handleReview = async (applicationId, status) => {
    try {
      const res = await axios.put(
        `http://localhost:8080/api/applicationBoard/review/${applicationId}/${status}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      console.log(res.data)
      location.reload()
    } catch (error) {
      console.log(error)
    }
  }

  const auth = () => {
    if (!localStorage.getItem('jwt_token')) location.href = '/login'
    else return true
  }
  const fetchIsAdmin = async () => {
    try {
      const res = await axios.get(
        'http://localhost:8080/api/users/checkIsAdmin',
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      setIsAdmin(res.data)
      if (res.data === false) location.href = '/'
    } catch (error) {
      console.log(error)
    }
  }
  const fetchData = async () => {
    try {
      const res = await axios.get(
        'http://localhost:8080/api/applicationBoard/pending',
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      setBoardData(res.data)
      console.log(res.data)
      setLoading(false)
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    auth()
    fetchIsAdmin()
    fetchData()
  }, [])

  return (
    isAdmin &&
    (!loading ? (
      <div className="p-12">
        <div className="flex items-center text-white mb-12 space-x-2">
          <ShieldCheckIcon className="h-8 w-8" />
          <h1 className="text-2xl text-white tracking-[.4rem]">審核看板</h1>
        </div>
        <div className="m-12 space-y-12">
          {boardData.length ? (
            boardData.map((data) => (
              <ReviewBoardItem
                key={data.applicationId}
                applicationId={data.applicationId}
                boardName={data.boardName}
                boardDescription={data.description}
                boardIcon={data.iconUrl}
                handleReviewFunc={handleReview}
              />
            ))
          ) : (
            <p className="text-tGray tracking-[.3rem] text-lg">
              尚無待審查的看板
            </p>
          )}
        </div>
      </div>
    ) : (
      <></>
    ))
  )
}

export default ReviewBoard
