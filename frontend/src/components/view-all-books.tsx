import { Book } from "../types/Book.ts";
import { BookCard } from "./book-card.tsx";
import React from "react";
import AddNewBookModal from "./add-new-book-modal.tsx";

type ViewAllBooksProps = {
  books: Book[];
  saveBook: (bookToSave: Book) => void;
};

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({
  books,
  saveBook,
}) => {
  const saveBooks = (bookToSave: Book) => {
    return saveBook(bookToSave);
  };
  return (
    <div className="flex flex-col items-center p-10">
      <div className="flex flex-wrap justify-center gap-16">
        {books.map((book) => (
          <BookCard key={book.id} book={book} />
        ))}
      </div>
      <div className="mt-10">
        <AddNewBookModal saveBook={saveBooks} />
      </div>
    </div>
  );
};
