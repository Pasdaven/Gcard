import React from "react"
import { Link } from "react-router-dom"

function SidebarRaw({ Icon, optionName, current, url }) {
  return current === optionName ? (
    <div className="text-white flex icon-center h-12 space-x-6 bg-main items-center rounded-3xl px-5 py-3 w-fit">
      <Icon className="h-8 w-8" />
      <p className="tracking-[.4rem] text-xl">{optionName}</p>
    </div>
  ) : (
    <Link
      to={url}
      className="text-white flex icon-center h-12 space-x-6 items-center rounded-3xl px-5 py-3 w-fit hover:bg-box duration-200 cursor-pointer"
    >
      <Icon className="h-8 w-8" />
      <p className="tracking-[.4rem] text-xl">{optionName}</p>
    </Link>
  )
}

export default SidebarRaw
