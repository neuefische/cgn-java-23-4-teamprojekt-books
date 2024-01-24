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

    return (
        <>
            <Routes>
                <Route path="/" element={<ViewAllBooks books={books}/>}/>
                <Route path="/books/:id/edit" element={<EditBook books={books}/>}/>
            </Routes>
        </>
    )
}

export default App
