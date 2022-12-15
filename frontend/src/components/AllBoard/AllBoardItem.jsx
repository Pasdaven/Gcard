import React from 'react'
import { Link, resolvePath } from 'react-router-dom'
import axios from 'axios';
import { useState, useEffect } from 'react'
import { data } from 'autoprefixer';

async function getBoard() {
    axios.get('http://localhost:8080/api/board/')
    .then(function (response){
        console.log(response.data);
    })
    .catch(function (error) {
        console.log(error);
    });

}

function AllBoardItem({boardId, boardName, description, iconUrl}) {

  return (
    <>
        
        <Link
        onClick={getBoard}
        to={`/board/${boardId}`}
        className='text-white h-52 w-52'>
            <div className='bg-box w-full h-full rounded-lg flex flex-col items-center justify-center hover:bg-gray-600'>
                <img src={`${iconUrl}`} className='h-16 w-16 m-5'></img>
                <p className='text-xl'>{boardName}</p>
            </div>
        </Link>

    </>
  )
}

export default AllBoardItem