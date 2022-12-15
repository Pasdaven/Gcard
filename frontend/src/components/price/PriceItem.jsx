import React from 'react'
import PropTypes from 'prop-types'

const formatPrice = (itemPrice) => {
  const price = parseInt(itemPrice)
  // return (price.toLocaleString('zh-TW',{style:'currency',currency: 'TWD'}));
  return price.toLocaleString('en-US')
}

function PriceItem({ itemImg, itemName, itemPrice }) {
  return (
    <>
      <div className="flex border border-gray-700 justify-between w-full rounded-lg items-center">
        <div className="flex items-center p-3">
          <img src={itemImg} className="h-10 w-10" alt="Token Icon"></img>
          <p className="text-white ml-3">{itemName}</p>
        </div>

        <div className="">
          <p className="text-white mr-3">${formatPrice(itemPrice)}</p>
        </div>
      </div>
    </>
  )
}

PriceItem.propTypes = {
  itemImg: PropTypes.string.isRequired,
  itemName: PropTypes.string.isRequired,
  itemPrice: PropTypes.number.isRequired,
}

export default PriceItem
