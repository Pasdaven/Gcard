import React from 'react'

function AllBoard() {
  const [data, setData] = useState([])
  useEffect(() => {
    axios
      .get('http://localhost:8080/api/board/')
      .then(function (response) {
        setData(response.data)
      })
      .catch((error) => console.log(error))
  }, [data])

  return (
    <>
      <div
        className="grid grid-cols-2 xl:grid-cols-3 
    overflow-y-auto max-h-screen scrollbar-hide gap-12 p-12 justify-items-center"
      >
        {data.map((d) => (
          <AllBoardItem
            boardId={d.boardId}
            boardName={d.boardName}
            description={d.description}
            iconUrl={d.iconUrl}
          />
        ))}
      </div>
    </>
  )
}

export default AllBoard
