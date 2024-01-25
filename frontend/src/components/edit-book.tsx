import {Book} from "../types/Book.ts";
import React, {ChangeEvent, FormEvent, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

type EditBookProps = {
    books: Book[],
    editBook: (book: Book) => void
}

export const EditBook: React.FC<EditBookProps> = ({books, editBook}) => {
    const navigate = useNavigate();
    const {id} = useParams();

    const [book, setBook] = useState<Book | undefined>(books.find(book => book.id === id))

    if (!book) {
        navigate("/404")
        return;
    }

    const keyToNameMap = {
        title: "Title",
        author: "Author",
        isbn: "ISBN",
        genre: "Genre",
        publicationDate: "Publication Date",
        imageUrl: "Cover Image (URL)",
    }

    const onBookChange = (event: ChangeEvent<HTMLInputElement>): void => {
        setBook({...book, [event.target.name]: event.target.value})
    }

    const onSubmitEdit = (event: FormEvent<HTMLFormElement>): void => {
        event.preventDefault()
        editBook(book)

        navigate(`/books/${book?.id}`)
    }

    return (
        <div className="book-detail">
            <div className="book">
                <form onSubmit={onSubmitEdit}>
                    {Object.keys(book).map(key => (
                        <div key={key}>
                            <div>{keyToNameMap[key as keyof typeof keyToNameMap]}</div>
                            <input name={key} value={book[key as keyof Book]} type="text" onChange={onBookChange}
                                   placeholder={`${keyToNameMap[key as keyof typeof keyToNameMap]}...`}/>
                        </div>
                    ))}
                    <button type="submit">Edit book</button>
                </form>
            </div>
        </div>
    );

}