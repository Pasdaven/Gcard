import React from 'react'
import { HeartIcon } from '@heroicons/react/24/solid'
import { useState, useEffect } from 'react'
import PropTypes from 'prop-types'
import axios from 'axios'

function BoardInfo({ priceId, priceIcon, priceName, priceDescription }) {
  const [likePost, setLikePost] = useState(false)
  const token = localStorage.getItem('jwt_token')

  const fetchLikePost = async () => {
    event.preventDefault()
    try {
      const response = await axios.get(
        `http://localhost:8080/api/post/like/${priceId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      setLikePost(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  const updateLikePost = async () => {
    try {
      if (likePost) {
        await axios.delete(`http://localhost:8080/api/likePosts/${priceId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
      } else {
        await axios.post(
          `http://localhost:8080/api/likePosts/${priceId}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        )
      }
      fetchLikePost()
    } catch (error) {
      console.log(error)
    }
  }

  const addBoard = async () => {
    event.preventDefault()
    try {
      const response = await axios.post(
        `http://localhost:8080/api/followBoard/${priceId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      console.log(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  const deleteBoard = async () => {
    event.preventDefault()
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/followBoard/${priceId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      console.log(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(() => {
    fetchLikePost()
  }, [])

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
            {likePost ? (
              <HeartIcon
                className="h-6 w-6 text-red-500 hover:cursor-pointer active:scale-75"
                onClick={function () {
                  updateLikePost()
                  deleteBoard()
                }}
              />
            ) : (
              <HeartIcon
                className="h-6 w-6 text-white hover:cursor-pointer active:scale-75"
                onClick={function () {
                  updateLikePost()
                  addBoard()
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
  priceId: PropTypes.number.isRequired,
  priceIcon: PropTypes.string.isRequired,
  priceDescription: PropTypes.string.isRequired,
  priceName: PropTypes.string.isRequired,
}

export default BoardInfo
