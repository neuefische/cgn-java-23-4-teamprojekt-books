import { Book } from "../types/Book.ts";
import { Link, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

type ViewBookProps = {
  handleBookDelete: (id: string) => void;
};

export default function ViewBook(props: ViewBookProps) {
  const [book, setBooks] = useState<Book>();
  const { id } = useParams();
  useEffect(() => {
    axios.get(`/api/books/${id}`).then((response) => setBooks(response.data));
  }, [id]);

  const handleBookDelete = (id: string | undefined) => {
    if (id) {
      props.handleBookDelete(id);
    }
  };

  return (
    <div className="book-detail">
      <div className="book">
        <div>{book?.title}</div>
        <div>{book?.author}</div>
        <Link to={`/books/${book?.id}/edit`}>
          <button>Edit</button>
          <button
            className="book-delete-button"
            onClick={() => handleBookDelete(book?.id)}
          >
            {" "}
            Delete
          </button>
        </Link>
      </div>
    </div>
  );
}
