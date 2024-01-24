import {Book} from "../types/Book.ts";
import React from "react";

import {Link} from "react-router-dom";

type BookElementProps = {
    book: Book
}

export const BookElement: React.FC<BookElementProps> = ({book}) => {

    return (
        <div className="book">
            <div>{book.title}</div>
            <Link to={`/books/${book.id}/edit`}>
                <button>Edit</button>
            </Link>
        </div>
    );

}