import {Book} from "../types/Book.ts";
import {Link, useParams} from "react-router-dom";
import React from "react";

type ViewBookProps = {
    books: Book[],
}

export const ViewBook: React.FC<ViewBookProps> = ({books}) => {

    const {id} = useParams();

    const book: Book | undefined = books.find(book => book.id === id);

    return (
        <div className="book-detail">
            <div className="book">
                <div>{book?.title}</div>
                <div>{book?.author}</div>
                <Link to={`/books/${book?.id}/edit`}>
                    <button>Edit</button>
                </Link>
            </div>
        </div>
    );

}