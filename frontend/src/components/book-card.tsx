import { Book } from "../types/Book.ts";
import React, {MouseEvent, useState} from "react";

import { FavouriteIcon } from "./FavouriteIcon.tsx";

import { Link } from "react-router-dom";

type BookCardProps = {
  book: Book;
};

export const BookCard: React.FC<BookCardProps> = ({ book }) => {
  const [isFavourite, setIsFavourite] = useState<boolean>(false);

  const toggleIsFavourite = (event: MouseEvent<HTMLDivElement>) => {
    event.stopPropagation();
    setIsFavourite(!isFavourite)
  }

  return (
    <div className="relative">
      <Link to={`/books/${book.id}`}>
        <div className="m-1 flex h-72 w-72 flex-col justify-between rounded-md border-border bg-white p-5 shadow-card">
          <img
            src={book.imageUrl}
            alt={book.title}
            className="h-3/5 object-contain select-none"
          />
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
