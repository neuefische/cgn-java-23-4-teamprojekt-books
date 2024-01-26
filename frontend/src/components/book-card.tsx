import { Book } from "../types/Book.ts";
import React from "react";

import { Link } from "react-router-dom";

type BookCardProps = {
  book: Book;
};

export const BookCard: React.FC<BookCardProps> = ({ book }) => {
  return (
    <Link to={`/books/${book.id}`}>
      <div className="shadow-card m-1 flex flex-col justify-between border-border h-72 w-60 bg-white p-5 rounded-md">
        <img src={book.imageUrl} alt={book.title} className="h-3/5 object-contain" />
        <div className="flex flex-col gap-0 items-center">
          <div className="font-black text-center text-lg">{book.title}</div>
          <div>{book.author}</div>
          <div className="font-thin text-sm">{book.publicationDate}</div>
        </div>
      </div>
    </Link>
  );
};
