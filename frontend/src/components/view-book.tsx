import { Book } from "../types/Book.ts";
import { useNavigate, useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { EditBookModal } from "./edit-book-modal.tsx";

type ViewBookProps = {
  deleteBook: (id: string) => void;
  editBook: (book: Book) => void;
};

export const ViewBook: React.FC<ViewBookProps> = ({ deleteBook, editBook }) => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [book, setBook] = useState<Book>();

  useEffect(() => {
    axios.get(`/api/books/${id}`).then((response) => setBook(response.data));
  }, [id]);

  if (!book) {
    return <div>loading</div>;
  }

  const handleBookDelete = (id: string): void => {
    deleteBook(id);
    navigate("/books");
  };

  const handleEditBook = (book: Book): void => {
    editBook(book);
    setBook(book);
  };

  return (
    <div className="flex flex-1 items-center justify-center">
      <div className="m-1 flex h-max w-96 flex-col items-center justify-between rounded-md bg-border/10 p-16 shadow-card">
        <img src={book.imageUrl} alt={book.title} className="w-3/5 select-none object-contain" />
        <div className="flex flex-col items-center gap-0 mt-5">
          <div className="text-center text-lg font-black">{book.title}</div>
          <div>{book.author}</div>
          <div className="text-sm font-thin">{book.publicationDate}</div>
        </div>
        <div className="flex w-2/3 justify-evenly mt-10">
          <EditBookModal book={book} editBook={handleEditBook} />
          <button
            className="h-8 rounded-lg border-none bg-red-500 px-3 text-white"
            onClick={() => handleBookDelete(book.id)}
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  );
};
