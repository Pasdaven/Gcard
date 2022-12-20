import { Routes, Route } from 'react-router-dom'
import AllBoard from './AllBoard'
import Explore from './Explore'
import Following from './Following'
import Price from './components/price/Price'
import UserRoutes from './UserRoutes'
import BoardRoutes from './BoardRoutes'
import PostRoutes from './PostRoutes'
import SidebarRoutes from './SidebarRoutes'

function App() {
  return (
    <Routes>
      <Route path="/login" element={<h1>Login Page</h1>} />
      <Route
        path="/*"
        element={
          <div className="h-screen grid grid-cols-5 bg-bg">
            <div className="col-span-1">
              <SidebarRoutes />
            </div>
            <div className="col-span-3">
              <Routes>
                <Route path="/" element={<Explore />} />
                <Route path="/allBoard" element={<AllBoard />} />
                <Route path="/following" element={<Following />} />
                <Route path="/user" element={<UserRoutes />} />
                <Route path="/board/*" element={<BoardRoutes />} />
                <Route path="/post" element={<PostRoutes />} />
              </Routes>
            </div>
            <div className="col-span-1">
              <Price />
            </div>
          </div>
        }
      ></Route>
    </Routes>
  )
}

export default App
