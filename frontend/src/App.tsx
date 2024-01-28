import { useEffect, useState } from "react";
import "./App.css";
import { ViewAllBooks } from "./components/view-all-books.tsx";
import { EditBook } from "./components/edit-book.tsx";
import ViewBook from "./components/view-book.tsx";
import { Book } from "./types/Book.ts";
import axios from "axios";
import { Route, Routes } from "react-router-dom";
import { Header } from "./components/header.tsx";
import Home from "./components/home.tsx";
import NotFound from "./components/not-found.tsx";
import Login from "./components/login.tsx";
import { User } from "./types/User.ts";

function App() {
  const [books, setBooks] = useState<Book[]>([]);
  const [user, setUser] = useState<User>(null);

  useEffect(() => {
    axios.get("/api/books").then((response) => setBooks(response.data));
    axios.get("/api/user").then((response) => {
      setUser(response.data);
    });
  }, []);

  const addBook = (bookToSave: Book) => {
    setBooks([...books, bookToSave]);
    axios.post("/api/books", bookToSave).then((response) => response.data);
  };

  const deleteBook = (id: string) => {
    axios.delete(`/api/books/${id}`).then((response) => {
      setBooks([...books.filter((book) => id !== book.id)]);
      return response.data;
    });
  };

  const editBook = (book: Book): void => {
    axios
      .put(`/api/books`, book)
      .then((response) => setBooks(books.map((item) => (item.id === book.id ? response.data : book))));
  };

  const logout = () => axios.get("/api/user/logout").then(() => setUser(null));

  return (
    <>
      <Header isLoggedIn={!!user} logout={logout}/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/books" element={<ViewAllBooks books={books} saveBook={addBook} />} />
        <Route path="/books/:id" element={<ViewBook handleBookDelete={deleteBook} />} />
        <Route path="/books/:id/edit" element={<EditBook books={books} editBook={editBook} />} />
        <Route path="/login" element={<Login />} />
        <Route path={"/*"} element={<NotFound />} />
      </Routes>
    </>
  );
}

export default App;
