import { Book } from "../types/Book.ts";
import React from "react";

import { FavouriteIcon } from "./FavouriteIcon.tsx";

import { Link } from "react-router-dom";

type BookCardProps = {
  book: Book;
  isFavourite: boolean;
  removeFavourite: (id: string) => void;
  setFavourite: (id: string) => void;
};

export const BookCard: React.FC<BookCardProps> = ({ book, isFavourite, removeFavourite, setFavourite }) => {
  const toggleIsFavourite = (): void => {
    isFavourite ? removeFavourite(book.id) : setFavourite(book.id);
  };

  return (
    <div className="relative">
      <Link to={`/books/${book.id}`}>
        <div className="m-1 flex h-72 w-72 flex-col justify-between items-center rounded-md bg-border/10 shadow-card p-5">
          <img src={book.imageUrl} alt={book.title} className="h-3/5 select-none object-contain" />
          <div className="flex flex-col items-center gap-0">
            <div className="text-center text-lg font-black">{book.title}</div>
            <div>{book.author}</div>
            <div className="text-sm font-thin">{book.publicationDate}</div>
          </div>
        </div>
      </Link>
      <div className="absolute right-3 top-3 cursor-pointer" onClick={toggleIsFavourite}>
        <FavouriteIcon isActive={isFavourite} />
      </div>
    </div>
  );
};
