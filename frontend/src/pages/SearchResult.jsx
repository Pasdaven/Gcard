import React from 'react'
import { useState } from 'react'
import { useParams } from 'react-router-dom'
import SearchInput from '../components/search/SearchInput'
import SearchFilter from '../components/search/SearchFilter'
import AllSearchResult from '../components/search/AllSearchResult'

function SearchResult() {
  const { keyword } = useParams()
  const [searchTarget, setSearchTarget] = useState('post')
  const [loading, setLoading] = useState(true)

  const updateSearchTarget = (target) => {
    setLoading(true)
    setSearchTarget(target)
  }

  const updateLoading = (loading) => {
    setLoading(loading)
  }

  return (
    <div className="mx-12">
      <div className="my-12">
        <SearchInput />
      </div>
      <SearchFilter
        searchTarget={searchTarget}
        setSearchTarget={updateSearchTarget}
      />
      <AllSearchResult
        keyword={keyword}
        searchTarget={searchTarget}
        loading={loading}
        updateLoading={updateLoading}
      />
    </div>
  )
}

export default SearchResult
