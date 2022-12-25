import React from 'react'
import { useEffect, useState } from 'react'
import PriceItem from './PriceItem'
import axios from 'axios'

function Price() {
  const [priceData, setPriceData] = useState()
  const [loading, setLoading] = useState(true)

  const fetchData = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/board/price')
      setPriceData(res.data)
      setLoading(false)
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    fetchData()
  }, [])

  return (
    !loading && (
      <div className="space-y-4 w-full">
        {priceData.map((data) => (
          <PriceItem
            key={data.boardId}
            itemId={data.boardId}
            itemImg={data.boardIconUrl}
            itemName={data.tokenName}
            itemPrice={data.tokenPrice}
          />
        ))}
      </div>
    )
  )
}

export default Price
