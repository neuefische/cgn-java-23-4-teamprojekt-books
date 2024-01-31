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

  return (
    <div className="book-detail">
      <div className="book">
        <div>{book.title}</div>
        <div>{book.author}</div>
        <div className="flex w-2/3 justify-evenly">
          <EditBookModal book={book} editBook={editBook} />
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
