import React from "react"
import SidebarRaw from "./SidebarRaw"
import { GlobeAsiaAustraliaIcon, RectangleStackIcon } from "@heroicons/react/24/solid"

function Sidebar({ current }) {
  return (
    <div className="flex flex-col items-start pt-12 pl-12">
      <img src="/image/icon.png" alt="icon" className="h-16 mb-16 ml-6" />
      <div className="option-group space-y-8">
        <SidebarRaw optionName="探索" Icon={GlobeAsiaAustraliaIcon} url="/" current={current} />
        <SidebarRaw optionName="所有看板" Icon={RectangleStackIcon} url="/allBoard" current={current} />
        <SidebarRaw optionName="追蹤中" Icon={GlobeAsiaAustraliaIcon} url="/following" current={current} />
      </div>
    </div>
  )
}

export default Sidebar
