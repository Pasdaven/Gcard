import React from 'react'

function NewPost() {
  return (
    <div className="p-12 space-y-8">
      <div>
        <label className="text-white text-base tracking-[.4rem] block mb-2">
          選擇看板
        </label>
        <div className="flex relative items-center">
          <select className="appearance-none rounded-xl pl-14 py-4 w-64 bg-box text-white tracking-widest cursor-pointer outline-main">
            <option>BTC</option>
            <option>ETH</option>
          </select>
          <span
            className="h-8 w-8 rounded-full bg-cover absolute left-3"
            style={{
              backgroundImage: `url(https://avatars.githubusercontent.com/u/75478661?v=4)`,
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
        ></input>
      </div>
      <div>
        <label className="text-white text-base tracking-[.4rem] block mb-2">
          文章內容
        </label>
        <textarea className="appearance-none rounded-xl pl-6 py-4 w-full h-64 bg-box text-white tracking-widest outline-main"></textarea>
      </div>
    </div>
  )
}

export default NewPost
