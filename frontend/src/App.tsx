import {useEffect, useState} from 'react'
import './App.css'
import {ViewAllBooks} from "./components/view-all-books.tsx";
import {EditBook} from "./components/edit-book.tsx";
import {Book} from "./types/Book.ts";
import axios from "axios";

import {Routes, Route} from "react-router-dom";


function App() {
    const [books, setBooks] = useState<Book[]>([])

    useEffect(() => {
        axios.get("/api/books").then(response => setBooks(response.data))
    }, [])

    const editBook = (book: Book): void => {
        axios.put(`/api/books/${book.id}`).then(response => setBooks(books.map((item) => (item.id === book.id ? response.data : book)))
        )
    }

    return (
        <>
            <Routes>
                <Route path="/" element={<ViewAllBooks books={books}/>}/>
                <Route path="/books/:id/edit" element={<EditBook books={books} editBook={editBook}/>}/>
            </Routes>
        </>
    )
}

export default App
