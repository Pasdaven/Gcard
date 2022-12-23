import React from 'react'
import { useState } from 'react'
import { MagnifyingGlassIcon } from '@heroicons/react/24/solid'

function SearchInput() {
  const [keyword, setKeyword] = useState()

  return (
    <>
      <div className="flex space-x-2 items-center">
        <MagnifyingGlassIcon className="h-5 w-5 text-tGray" />
        <form
          onSubmit={() => {
            event.preventDefault()
            location.href = `/search/${keyword}`
          }}
        >
          <input
            type="text"
            className="appearance-none bg-transparent focus:outline-none text-white tracking-[.3rem] placeholder:text-tGray placeholder:tracking-[.6rem] placeholder:text-sm"
            placeholder="點擊以搜尋"
            onChange={(e) => {
              setKeyword(e.target.value)
            }}
          />
        </form>
      </div>
    </>
  )
}

export default SearchInput
