import React from 'react'
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
      <a
        onClick={getBoard}
        href={`/board/${boardId}`}
        className="text-white h-52 w-52"
      >
        <div className="bg-box w-full h-full rounded-lg flex flex-col items-center justify-center hover:bg-card duration-200 group">
          <span
            className="h-20 w-20 rounded-full bg-cover m-4 group-hover:scale-95 duration-300"
            style={{ backgroundImage: `url(${iconUrl})` }}
          ></span>
          {/* <img src={`${iconUrl}`} className="h-16 w-16 m-5"></img> */}
          <p className="text-xl tracking-[.3rem]">{boardName}</p>
        </div>
      </a>
    </>
  )
}

AllBoardItem.propTypes = {
  boardId: PropTypes.number.isRequired,
  boardName: PropTypes.string.isRequired,
  iconUrl: PropTypes.string.isRequired,
}

export default AllBoardItem
