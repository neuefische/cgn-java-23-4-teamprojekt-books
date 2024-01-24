import {Book} from "../types/Book.ts";
import {BookElement} from "./book-element.tsx";
import React from "react";

type ViewAllBooksProps = {
    books: Book[]
    handleBookDelete: (id: string) => void
}

export const ViewAllBooks: React.FC<ViewAllBooksProps> = (props: ViewAllBooksProps) => {

    const handleBookDelete = (id: string) => {
        props.handleBookDelete(id)
    }

    return (
        <div className="books">
            {books.map(book => (<BookElement key={book.id} book={book}/>))}
            <button className="book-delete-button" onClick={() => handleBookDelete(book.id)}> Delete</button>
        </div>
    );

}