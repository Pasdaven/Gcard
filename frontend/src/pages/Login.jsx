import React from 'react'
import { EyeIcon, EyeSlashIcon } from '@heroicons/react/24/solid'
import { useState } from 'react'
import axios from 'axios'

function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [showPassword, setShowPassword] = useState(false)

  const handleSubmit = async () => {
    event.preventDefault()
    try {
      const response = await axios.post(
        'http://localhost:8080/api/users/login',
        {
          email,
          password,
        }
      )
      if (response.status === 200) {
        localStorage.setItem('jwt_token', response.data)
      }
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <>
      <div className="bg-bg h-screen w-screen">
        <div className="flex justify-center items-center">
          <img src="/image/banner.png" className="h-11 mt-28"></img>
        </div>

        <div className="flex justify-center items-center mt-8">
          <div className="bg-card h-96 w-96 rounded-lg">
            <div className="flex justify-center p-4">
              <h1 className="text-[28px] text-white p-4 tracking-[.6rem]">
                登入
              </h1>
            </div>

            <form onSubmit={() => handleSubmit()}>
              <div className="flex flex-col ml-8 mr-8">
                <label className="flex justify-start text-tGray tracking-[.6rem] mb-2">
                  帳號
                </label>
                <input
                  type="text"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="
                  bg-box pt-3 pb-3 px-4 rounded-lg text-white focus:outline-main"
                ></input>
                <label className="flex justify-start text-tGray mt-8 tracking-[.6rem] mb-2">
                  密碼
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
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="
                    bg-box pt-3 pb-3 px-4 rounded-lg text-white focus:outline-main w-full"
                    id="password"
                  ></input>
                </div>
              </div>
              <div className="flex justify-between mt-8">
                <button className="bg-gray-700 py-2 pr-8 pl-10 rounded-full text-white ml-8 mr-8 tracking-[.6rem] hover:bg-gray-500 duration-200 cursor-pointer">
                  註冊
                </button>
                <button className="bg-main py-2 pr-8 pl-10 rounded-full text-white ml-8 mr-8 tracking-[.6rem] hover:bg-[#6c76d0] duration-200 cursor-pointer">
                  登入
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  )
}

export default Login
