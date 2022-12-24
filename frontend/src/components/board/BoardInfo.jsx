import React from 'react'
import { HeartIcon } from '@heroicons/react/24/solid'
import { useState, useEffect } from 'react'
import PropTypes from 'prop-types'
import axios from 'axios'

function BoardInfo({ boardId, boardIcon, boardName, boardDescription }) {
  const [FollowBoard, setFollowBoard] = useState(false)
  const token = localStorage.getItem('jwt_token')

  const auth = () => {
    if (!localStorage.getItem('jwt_token')) location.href = '/login'
    else return true
  }

  const fetchLikeBoard = async () => {
    event.preventDefault()
    try {
      const response = await axios.get(
        `http://localhost:8080/api/followBoard/check/${boardId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      setFollowBoard(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  const updateFollowBoard = async () => {
    auth()
    try {
      if (FollowBoard) {
        await axios.delete(`http://localhost:8080/api/followBoard/${boardId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
      } else {
        await axios.post(
          `http://localhost:8080/api/followBoard/${boardId}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        )
      }
      fetchLikeBoard()
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    fetchLikeBoard()
  }, [])

  return (
    <>
      <div className="bg-box h-auto rounded-lg flex">
        <div className="flex flex-col p-8 justify-center items-center">
          <div
            className="h-16 w-16 bg-cover bg-center rounded-full"
            style={{
              backgroundImage: `url(${boardIcon})`,
            }}
          ></div>
          <div className="text-white tracking-[.4rem] ml-2 mt-2 text-2xl">
            {boardName}
          </div>
        </div>
        <div className="flex flex-col w-full justify-between space-y-4 pt-8 pb-4">
          <p className="text-white tracking-[.4rem] pr-12"></p>
          {boardDescription}
          <div className="flex justify-end mr-6">
            {FollowBoard ? (
              <HeartIcon
                className="h-6 w-6 text-red-500 hover:cursor-pointer active:scale-75"
                onClick={function () {
                  updateFollowBoard()
                }}
              />
            ) : (
              <HeartIcon
                className="h-6 w-6 text-white hover:cursor-pointer active:scale-75"
                onClick={function () {
                  updateFollowBoard()
                }}
              />
            )}
          </div>
        </div>
      </div>
    </>
  )
}

BoardInfo.propTypes = {
  boardId: PropTypes.number.isRequired,
  boardIcon: PropTypes.string.isRequired,
  boardName: PropTypes.string.isRequired,
  boardDescription: PropTypes.string.isRequired,
}

export default BoardInfo
