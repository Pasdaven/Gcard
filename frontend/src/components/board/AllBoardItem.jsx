import React from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import PropTypes from 'prop-types'

async function getBoard() {
  axios
    .get('http://localhost:8080/api/board/')
    .then(function (response) {
      console.log(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

function AllBoardItem({ boardId, boardName, iconUrl }) {
  return (
    <>
      <Link
        onClick={getBoard}
        to={`/board/${boardId}`}
        className="text-white h-52 w-52"
      >
        <div className="bg-box w-full h-full rounded-lg flex flex-col items-center justify-center hover:bg-gray-600">
          <img src={`${iconUrl}`} className="h-16 w-16 m-5"></img>
          <p className="text-xl">{boardName}</p>
        </div>
      </Link>
    </>
  )
}

AllBoardItem.propTypes = {
  boardId: PropTypes.number.isRequired,
  boardName: PropTypes.string.isRequired,
  iconUrl: PropTypes.string.isRequired,
}

export default AllBoardItem
