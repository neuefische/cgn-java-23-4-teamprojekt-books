import {Book} from "../types/Book.ts";
import {BookCard} from "./book-card.tsx";
import React from "react";
import BasicModal from "./add-new-book-modal.tsx";

type ViewAllBooksProps = {
    books: Book[]
    saveBook: (bookToSave: Book) => void
}

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({books, saveBook}) => {

    const saveBooks = (bookToSave: Book) => {
        return saveBook(bookToSave);
    }

    return (
        <div className="books">
            {books.map(book => (<BookCard key={book.id} book={book}/>))}
            <BasicModal saveBook={saveBooks}/>
        </div>
    );

}