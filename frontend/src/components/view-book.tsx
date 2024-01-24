import {Book} from "../types/Book.ts";
import React from "react";

type ViewBookProps = {
    book: Book
}

export const ViewBook: React.FC<ViewBookProps> = ({book}) => {

    return (
        <div className="book">
            <div>{book.title}</div>
            <button>Edit</button>
        </div>
    );

}