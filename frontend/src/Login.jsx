import React from 'react'
import { EyeIcon, EyeSlashIcon } from '@heroicons/react/24/solid'
import { useState } from 'react'

function Login() {
  const [showPassword, setShowPassword] = useState(false)

  return (
    <>
      <div className="bg-bg h-screen w-screen">
        <div className="flex justify-center items-center">
          <img src="/image/icon.png" className="h-8 w-8 mt-28"></img>
          <p className="ml-2 mt-28 text-[28px] text-white">G c a r d</p>
        </div>

        <div className="flex justify-center mt-8">
          <div className="bg-card h-96 w-96 rounded-lg">
            <div className="flex justify-center p-4">
              <h1 className="text-[28px] text-white p-4 tracking-widest">
                登 入
              </h1>
            </div>

            <div className="flex flex-col ml-8 mr-8">
              <label className="flex justify-start text-tGray tracking-widest">
                帳 號
              </label>
              <input
                type="text"
                className="
         bg-box pt-3 pb-3 px-4 rounded-lg text-white focus:outline-main"
              ></input>
              <label className="flex justify-start text-tGray mt-8 tracking-widest">
                密 碼
              </label>
              <div className="relative flex items-center justify-end">
                {showPassword ? (
                  <EyeIcon
                    className="h-5 w-5 absolute mr-2 text-white hover:cursor-pointer"
                    onClick={() => setShowPassword(!showPassword)}
                  />
                ) : (
                  <EyeSlashIcon
                    className="h-5 w-5 absolute mr-2 text-white hover:cursor-pointer"
                    onClick={() => setShowPassword(!showPassword)}
                  />
                )}
                <input
                  type={showPassword ? 'text' : 'password'}
                  className="
         bg-box pt-3 pb-3 px-4 rounded-lg text-white focus:outline-main w-full"
                  id="password"
                ></input>
              </div>
            </div>

            <div className="flex justify-between mt-8">
              <button className="bg-gray-700 py-2 px-10 rounded-full text-white ml-8 mr-8 tracking-widest hover:bg-gray-500 duration-200 cursor-pointer">
                註 冊
              </button>
              <button className="bg-main py-2 px-10 rounded-full text-white ml-8 mr-8 tracking-widest hover:bg-[#6c76d0]">
                登 入
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Login
