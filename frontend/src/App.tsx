import {useEffect, useState} from 'react'
import './App.css'
import {ViewAllBooks} from "./components/view-all-books.tsx";
import {Book} from "./Book.ts";
import axios from "axios";

function App() {
  const [books, setBooks] = useState<Book[]>([])

  useEffect(() => {
    axios.get("/api/books").then(response => setBooks(response.data))
  }, [])

  const deleteBook = (id: string) => {
    axios.delete(`/api/books/${id}`)
        .then(response => {
          setBooks([...books.filter(book => id !== book.id)]);
          return console.log(response.data)
        })
  }

  return (
    <>
      <ViewAllBooks books={books} handleBookDelete={deleteBook} />
    </>
  )
}

export default App
