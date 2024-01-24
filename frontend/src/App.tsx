import {useEffect, useState} from 'react'
import './App.css'
import {ViewAllBooks} from "./components/view-all-books.tsx";
import {Book} from "./Book.ts";
import axios from "axios";
import {Route, Routes} from "react-router-dom";
import BookDetailsPage from "./components/BookDetailsPage.tsx";

function App() {
  const [books, setBooks] = useState<Book[]>([])

  useEffect(() => {
    axios.get("/api/books").then(response => setBooks(response.data))
  }, [])

  return (
    <>
        <Routes>
      <Route path={"/"} element={<ViewAllBooks books={books} />}/>
      <Route path={"/:id"} element={<BookDetailsPage books={books}/>}/>
            </Routes>
    </>
  )
}

export default App
