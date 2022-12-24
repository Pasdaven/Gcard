import React from 'react'
import PropTypes from 'prop-types'

const formatPrice = (itemPrice) => {
  const price = parseFloat(itemPrice).toFixed(5)
  // return (price.toLocaleString('zh-TW',{style:'currency',currency: 'TWD'}));
  return price.toLocaleString('en-US')
}

function PriceItem({ itemId, itemImg, itemName, itemPrice }) {
  return (
    <>
      <a
        className="flex border border-gray-700 justify-between w-full rounded-lg items-center hover:bg-card duration-200 group"
        href={`/board/${itemId}`}
      >
        <div className="flex items-center p-3">
          <span
            className="h-10 w-10 rounded-full bg-cover group-hover:scale-95 duration-300"
            style={{
              backgroundImage: `url(${itemImg})`,
            }}
          ></span>
          <p className="text-white ml-3">{itemName}</p>
        </div>

        <div>
          <p className="text-white mr-3 tracking-widest">
            ${formatPrice(itemPrice)}
          </p>
        </div>
      </a>
    </>
  )
}

PriceItem.propTypes = {
  itemId: PropTypes.number.isRequired,
  itemImg: PropTypes.string.isRequired,
  itemName: PropTypes.string.isRequired,
  itemPrice: PropTypes.string.isRequired,
}

export default PriceItem
