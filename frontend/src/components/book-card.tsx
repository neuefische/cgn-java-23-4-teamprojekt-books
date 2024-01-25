import {Book} from "../types/Book.ts";
import React from "react";

import {Link} from "react-router-dom";

type BookCardProps = {
    book: Book
}

export const BookCard: React.FC<BookCardProps> = ({book}) => {

    return (
        <Link to={`/books/${book.id}`}>

        <div className="book">
            <div>{book.title}</div>
        </div>
        </Link>
    );

}