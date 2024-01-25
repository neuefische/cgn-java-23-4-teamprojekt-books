import {Book} from "../types/Book.ts";
import {BookCard} from "./book-card.tsx";
import React from "react";

type ViewAllBooksProps = {
    books: Book[]
}

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({books}) => {

    return (
        <div className="books">
            {books.map(book => (<BookCard key={book.id} book={book}/>))}
        </div>
    );

}