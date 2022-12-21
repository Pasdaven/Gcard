import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Sidebar from '../components/sidebar/Sidebar'

function SidebarRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Sidebar current="探索" />} />
      <Route path="/allBoard" element={<Sidebar current="所有看板" />} />
      <Route path="/following" element={<Sidebar current="追蹤中" />} />
      <Route path="/*" element={<Sidebar />} />
    </Routes>
  )
}

export default SidebarRoutes
