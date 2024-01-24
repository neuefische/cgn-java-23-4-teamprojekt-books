import {useEffect, useState} from 'react'
import './App.css'
import {ViewAllBooks} from "./components/view-all-books.tsx";
import {Book} from "./Book.ts";
import axios from "axios";
import {Route, Routes} from "react-router-dom";
import AddNewBook from "./AddNewBook.tsx";


function App() {
  const [books, setBooks] = useState<Book[]>([])

  useEffect(() => {
    axios.get("/api/books").then(response => setBooks(response.data))
  }, [])
const addBook =(bookToSave:Book)=>{
    setBooks([...books, bookToSave ])
}

  return (
    <>

      <ViewAllBooks books={books} />
      <Routes>
         <Route path={"/add/book"} element={<AddNewBook saveBook={addBook}/>}/>

      </Routes>
    </>
  )
}

export default App
