import React from 'react'
import SidebarRow from './SidebarRow'
import {
  GlobeAsiaAustraliaIcon,
  RectangleStackIcon,
} from '@heroicons/react/24/solid'
import PropTypes from 'prop-types'

function Sidebar({ current }) {
  return (
    <div className="flex flex-col items-start pt-12 pl-12">
      <div className="relative animate-godog animation-delay-3000">
        <img src="/image/icon.png" alt="icon" className="h-12 ml-6" />
        <div className="h-2 w-20 rounded-full bg-main ml-2"></div>
        <div className="flex animate-wheels animation-delay-100">
          <img
            src="https://t3.ftcdn.net/jpg/02/17/04/38/360_F_217043893_C16NUEZ1x9xL6W3znWZNVn15TrIp4lc7.jpg"
            alt="icon"
            className="h-5 w-5 rounded-full mb-8 ml-4 animate-spin"
          />
          <img
            src="https://t3.ftcdn.net/jpg/02/17/04/38/360_F_217043893_C16NUEZ1x9xL6W3znWZNVn15TrIp4lc7.jpg"
            alt="icon"
            className="h-5 w-5 rounded-full mb-8 ml-6 animate-spin"
          />
        </div>
      </div>

      <div className="option-group space-y-8">
        <SidebarRow
          optionName="探索"
          Icon={GlobeAsiaAustraliaIcon}
          url="/"
          current={current}
        />
        <SidebarRow
          optionName="所有看板"
          Icon={RectangleStackIcon}
          url="/allBoard"
          current={current}
        />
        <SidebarRow
          optionName="追蹤中"
          Icon={GlobeAsiaAustraliaIcon}
          url="/following"
          current={current}
        />
      </div>
    </div>
  )
}

Sidebar.propTypes = {
  current: PropTypes.string,
}

export default Sidebar
