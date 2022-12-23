import { Routes, Route } from 'react-router-dom'
import AllBoard from './pages/AllBoard'
import Explore from './pages/Explore'
import Following from './pages/Following'
import Price from './components/price/Price'
import Login from './pages/Login'
import UserRoutes from './routes/UserRoutes'
import BoardRoutes from './routes/BoardRoutes'
import PostRoutes from './routes/PostRoutes'
import SidebarRoutes from './routes/SidebarRoutes'
import SearchResult from './pages/SearchResult'

function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
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
                <Route path="/search/:keyword" element={<SearchResult />} />
                <Route path="/user/*" element={<UserRoutes />} />
                <Route path="/board/*" element={<BoardRoutes />} />
                <Route path="/post/*" element={<PostRoutes />} />
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
