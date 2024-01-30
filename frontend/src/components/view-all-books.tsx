import { Book } from "../types/Book.ts";
import { BookCard } from "./book-card.tsx";
import React, { useEffect, useState } from "react";
import AddNewBookModal from "./add-new-book-modal.tsx";
import { User } from "../types/User.ts";

type ViewAllBooksProps = {
  books: Book[];
  saveBook: (bookToSave: Book) => void;
  updateUser: (updatedUser: User) => void;
  user: User;
};

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({ user, books, saveBook, updateUser }) => {
  const [favourites, setFavourites] = useState<string[]>([]);

  useEffect(() => {
    user && setFavourites(user.favouriteBooks);
  }, [user]);

  if (!user) {
    return <div>loading</div>;
  }

  const setFavourite = (id: string): void => {
    const updatedFavourites: string[] = [...favourites, id];
    updateUser({ ...user, favouriteBooks: updatedFavourites });
    setFavourites(updatedFavourites);
  };

  const removeFavourite = (id: string): void => {
    const updatedFavourites: string[] = favourites.filter((bookId) => bookId !== id);
    updateUser({ ...user, favouriteBooks: updatedFavourites });
    setFavourites(updatedFavourites);
  };

  return (
    <div className="flex flex-col items-center p-16">
      <div className="flex flex-wrap justify-center gap-16">
        {books.map((book) => (
          <BookCard
            key={book.id}
            removeFavourite={removeFavourite}
            setFavourite={setFavourite}
            isFavourite={favourites.includes(book.id)}
            book={book}
          />
        ))}
      </div>
      <div className="mt-10">
        <AddNewBookModal saveBook={saveBook} />
      </div>
    </div>
  );
};
