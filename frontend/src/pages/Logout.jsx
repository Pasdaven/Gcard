import React from 'react'
import { useEffect } from 'react'

function Logout() {
  useEffect(() => {
    localStorage.removeItem('jwt_token')
    location.href = '/'
  }, [])

  return <></>
}

export default Logout
