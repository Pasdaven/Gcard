import { Routes, Route } from 'react-router-dom'
import AllBoard from './AllBoard'
import Sidebar from './components/sidebar/Sidebar'
import Explore from './Explore'
import Following from './Following'
import Price from './components/price/Price'

function App() {
  return (
    <div className="h-screen grid grid-cols-5 bg-bg">
      <div className="col-span-1">
        <Routes>
          <Route path="/" element={<Sidebar current="探索" />} />
          <Route path="/allBoard" element={<Sidebar current="所有看板" />} />
          <Route path="/following" element={<Sidebar current="追蹤中" />} />
        </Routes>
      </div>
      <div className="col-span-3">
        <Routes>
          <Route path="/" element={<Explore />} />
          <Route path="/allBoard" element={<AllBoard />} />
          <Route path="/following" element={<Following />} />
        </Routes>
      </div>
      <div className="col-span-1">
        <Price />
      </div>
    </div>
  )
}

export default App
