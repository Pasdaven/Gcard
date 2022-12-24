import React from 'react'
import { useState } from 'react'
import { PlusCircleIcon } from '@heroicons/react/24/outline'
import axios from 'axios'

function RequestBoard() {
  const [boardName, setBoardName] = useState('')
  const [boardIconUrl, setBoardIconurl] = useState('')
  const [boardDescription, setBoardDescription] = useState('')

  const handleSubmit = async () => {
    event.preventDefault()
    try {
      const res = await axios.post(
        'http://localhost:8080/api/applicationBoard/',
        {
          boardName: boardName,
          description: boardDescription,
          iconUrl: boardIconUrl,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      if (res.status === 200) {
        alert('提交成功')
        location.reload()
      }
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <div className="p-12">
      <div className="flex items-center text-white mb-12 space-x-2">
        <PlusCircleIcon className="h-8 w-8" />
        <h1 className="text-2xl text-white tracking-[.4rem]">申請看板</h1>
      </div>
      <form onSubmit={() => handleSubmit()}>
        <div className="space-y-8">
          <div>
            <label className="text-white text-base tracking-[.4rem] block mb-2">
              看板名稱（貨幣名稱）
            </label>
            <input
              type="text"
              className="appearance-none rounded-xl pl-6 py-4 w-64 bg-box text-white tracking-widest outline-main"
              placeholder="請輸入看板名稱..."
              onChange={(e) => setBoardName(e.target.value)}
              value={boardName}
            ></input>
          </div>
          <div>
            <label className="text-white text-base tracking-[.4rem] block mb-2">
              看板圖像連結
            </label>
            <div className="flex items-center space-x-4">
              <input
                type="text"
                className="appearance-none rounded-xl px-6 py-4 w-3/5 bg-box text-white tracking-widest outline-main"
                placeholder="請輸入看板圖像連結..."
                onChange={(e) => setBoardIconurl(e.target.value)}
                value={boardIconUrl}
              ></input>
              {boardIconUrl && (
                <span
                  className="h-12 w-12 rounded-full bg-cover"
                  style={{
                    backgroundImage: `url(${boardIconUrl})`,
                  }}
                ></span>
              )}
            </div>
          </div>
          <div>
            <label className="text-white text-base tracking-[.4rem] block mb-2">
              看板說明
            </label>
            <textarea
              className="appearance-none rounded-xl pl-6 py-4 w-full h-80 bg-box text-white tracking-widest outline-main"
              placeholder="請輸入看板說明..."
              onChange={(e) => setBoardDescription(e.target.value)}
              value={boardDescription}
            ></textarea>
          </div>
          <button
            type="submit"
            className="bg-main pl-7 pr-5 py-3 rounded-lg hover:bg-indigo-400 duration-150 text-white tracking-[.4rem]"
          >
            送出
          </button>
        </div>
      </form>
    </div>
  )
}

export default RequestBoard
