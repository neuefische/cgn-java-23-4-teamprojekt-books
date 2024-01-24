import {Book} from "../types/Book.ts";
import React from "react";
import {useParams} from "react-router-dom";

type EditBookProps = {
    books: Book[]
}

export const EditBook: React.FC<EditBookProps> = ({books}) => {

    const {id} = useParams();

    const book: Book | undefined = books.find(book => book.id === id);

    return (
        <div className="book-detail">
            <div className="book">{book?.title}</div>
        </div>
    );

}