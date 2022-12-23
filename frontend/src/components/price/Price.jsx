import React from 'react'
import PriceItem from './PriceItem'

function Price() {
  return (
    <div className="space-y-4 w-full">
      <PriceItem itemImg={'/image/BTC.png'} itemName="BTC" itemPrice="20000" />
      <PriceItem
        itemImg={'/image/BTC.png'}
        itemName="WAVES"
        itemPrice="300000"
      />
      <PriceItem itemImg={'/image/BTC.png'} itemName="AAA" itemPrice="300" />
      <PriceItem itemImg={'/image/BTC.png'} itemName="A" itemPrice="300000" />
      <PriceItem itemImg={'/image/BTC.png'} itemName="AAAA" itemPrice="3" />
    </div>
  )
}

export default Price
