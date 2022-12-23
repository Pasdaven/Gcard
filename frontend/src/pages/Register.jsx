import React from 'react'
import { useState } from 'react'
import axios from 'axios'
import { EyeIcon } from '@heroicons/react/24/solid'
import { EyeSlashIcon } from '@heroicons/react/24/solid'

function Register() {
  const [userName, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [password2, setPassword2] = useState('')
  const [imgUrl, setImgUrl] = useState('')
  const role = 'user'
  const [showPassword, setShowPassword] = useState(false)
  const [showPassword2, setShowPassword2] = useState(false)

  const handleSubmit = async () => {
    event.preventDefault()
    try {
      if (password === password2) {
        const response = await axios.post('http://localhost:8080/api/users/', {
          role: role,
          userName: userName,
          imgUrl: imgUrl,
          userAccount: {
            email: email,
            password: password,
          },
        })
        console.log(response)
        if (response.status === 201) {
          location.href = '/login'
        }
      } else {
        alert('密碼是不會好好打？')
      }
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <>
      <div className="bg-bg h-screen w-screen text-white">
        <div className="flex justify-center items-center">
          <img src="/image/banner.png" className="h-11 mt-24"></img>
        </div>
        <form onSubmit={() => handleSubmit()}>
          <div className="flex justify-center items-center mt-8">
            <div className="h-fit w-fit bg-card px-16 pb-8">
              <h1 className="text-center p-8 mt-2 text-2xl tracking-[.6rem]">
                註冊
              </h1>
              <div>
                <div className="flex">
                  <div className="flex flex-col mr-8 mb-8">
                    <label className="flex justify-start text-tGray tracking-[.6rem] text-sm mb-2">
                      使用者名稱
                    </label>
                    <input
                      type="text"
                      value={userName}
                      onChange={(e) => setUsername(e.target.value)}
                      className="bg-box pt-3 pb-3 pr-6 pl-2 rounded-lg focus:outline-main"
                    ></input>
                  </div>
                  <div className="flex flex-col">
                    <label className="flex justify-start text-tGray tracking-[.6rem] text-sm mb-2">
                      電子信箱
                    </label>
                    <input
                      type="text"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      className="bg-box pt-3 pb-3 pr-6 pl-2 rounded-lg focus:outline-main"
                    ></input>
                  </div>
                </div>
                <div className="flex">
                  <div className="flex flex-col mr-8 mb-8">
                    <label className="flex justify-start text-tGray tracking-[.6rem] text-sm mb-2">
                      密碼
                    </label>
                    <div className="flex relative justify-end items-center">
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
                        className="bg-box pt-3 pb-3 pr-6 pl-2 rounded-lg focus:outline-main"
                      ></input>
                    </div>
                  </div>
                  <div className="flex flex-col">
                    <label className="flex justify-start text-tGray tracking-[.6rem] text-sm mb-2">
                      再次輸入密碼
                    </label>
                    <div className="flex relative justify-end items-center">
                      {showPassword2 ? (
                        <EyeIcon
                          className="h-5 w-5 absolute mr-2 text-white hover:cursor-pointer"
                          onClick={() => setShowPassword2(!showPassword2)}
                        />
                      ) : (
                        <EyeSlashIcon
                          className="h-5 w-5 absolute mr-2 text-white hover:cursor-pointer"
                          onClick={() => setShowPassword2(!showPassword2)}
                        />
                      )}
                      <input
                        type={showPassword2 ? 'text' : 'password'}
                        value={password2}
                        onChange={(e) => setPassword2(e.target.value)}
                        className="bg-box pt-3 pb-3 pr-6 pl-2 rounded-lg focus:outline-main"
                      ></input>
                    </div>
                  </div>
                </div>
                <div className="flex">
                  <div className="flex flex-col mr-8">
                    <label className="flex justify-start text-tGray tracking-[.6rem] text-sm mb-2">
                      頭貼網址
                    </label>
                    <input
                      type="text"
                      value={imgUrl}
                      onChange={(e) => setImgUrl(e.target.value)}
                      className="bg-box pt-3 pb-3 pr-6 pl-2 rounded-lg focus:outline-main"
                    ></input>
                  </div>
                </div>
              </div>
              <div className="flex justify-end">
                <button className="bg-main py-2 pr-8 pl-10 rounded-full text-white mt-2 tracking-[.6rem] hover:bg-[#6c76d0] duration-200 cursor-pointer">
                  送出
                </button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </>
  )
}

export default Register
