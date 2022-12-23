import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios'

function NewPost() {
  const [boardOption, setBoardOption] = useState()
  const [loading, setLoading] = useState(true)
  const [currentOption, setCurrentOption] = useState()
  const [option, setOption] = useState()
  const [title, setTitle] = useState('')
  const [content, setContent] = useState('')

  const auth = () => {
    if (!localStorage.getItem('jwt_token')) location.href = '/login'
    else return true
  }

  const fetchBoardOption = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/board/')
      setBoardOption(res.data)
      setCurrentOption(res.data[0])
      setOption(res.data[0].boardId)
      setLoading(false)
    } catch (error) {
      console.log(error)
    }
  }

  const handleSubmit = async () => {
    event.preventDefault()
    try {
      const res = await axios.post(
        'http://localhost:8080/api/post/',
        {
          title: title,
          content: content,
          board: {
            boardId: option,
          },
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt_token')}`,
          },
        }
      )
      if (res.status === 201) {
        location.href = `/post/${res.data.postId}`
      }
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(() => {
    fetchBoardOption()
  }, [])

  return auth() && loading ? (
    <></>
  ) : (
    <form onSubmit={() => handleSubmit()}>
      <div className="p-12 space-y-8">
        <div>
          <label className="text-white text-base tracking-[.4rem] block mb-2">
            選擇看板
          </label>
          <div className="flex relative items-center">
            <select
              className="appearance-none rounded-xl pl-14 py-4 w-64 bg-box text-white tracking-widest cursor-pointer outline-main"
              onChange={(e) => {
                setCurrentOption(boardOption[e.target.selectedIndex])
                setOption(parseInt(e.target.value))
              }}
            >
              {boardOption.map((data) => (
                <option key={data.boardId} value={data.boardId}>
                  {data.boardName}
                </option>
              ))}
            </select>
            <span
              className="h-8 w-8 rounded-full bg-cover absolute left-3"
              style={{
                backgroundImage: `url(${currentOption.iconUrl})`,
              }}
            ></span>
          </div>
        </div>
        <div>
          <label className="text-white text-base tracking-[.4rem] block mb-2">
            文章標題
          </label>
          <input
            type="text"
            className="appearance-none rounded-xl pl-6 py-4 w-3/5 bg-box text-white tracking-widest outline-main"
            placeholder="請輸入文章標題..."
            onChange={(e) => {
              setTitle(e.target.value)
            }}
            value={title}
          ></input>
        </div>
        <div>
          <label className="text-white text-base tracking-[.4rem] block mb-2">
            文章內容
          </label>
          <textarea
            className="appearance-none rounded-xl pl-6 py-4 w-full h-80 bg-box text-white tracking-widest outline-main"
            placeholder="請輸入文章內容..."
            onChange={(e) => {
              setContent(e.target.value)
            }}
            value={content}
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
  )
}

export default NewPost
