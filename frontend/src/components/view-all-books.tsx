import { Book } from "../types/Book.ts";
import { BookCard } from "./book-card.tsx";
import React from "react";
import AddNewBookModal from "./add-new-book-modal.tsx";

type ViewAllBooksProps = {
  books: Book[];
    saveBook: (bookToSave: Book) => void
};

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({ books }) => {
  return (
    <div className="flex flex-wrap justify-center gap-16">
      {books.map((book) => (
        <BookCard key={book.id} book={book} />
      ))}
    </div>
  );
};
export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({books, saveBook}) => {

    const saveBooks = (bookToSave: Book) => {
        return saveBook(bookToSave);
    }

    return (
        <div className="books">
            {books.map(book => (<BookCard key={book.id} book={book}/>))}
            <AddNewBookModal saveBook={saveBooks}/>
        </div>
    );
