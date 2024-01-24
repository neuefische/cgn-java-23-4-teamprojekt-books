import {Book} from "../types/Book.ts";
import {BookElement} from "./book-element.tsx";
import React from "react";

type ViewAllBooksProps = {
    books: Book[]
}

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({books}) => {

    return (
        <div className="books">
            {books.map(book => (<BookElement key={book.id} book={book}/>))}
        </div>
    );

}