import {Book} from "../types/Book.ts";
import React, {ChangeEvent, FormEvent, useState} from "react";
import {useParams} from "react-router-dom";

type EditBookProps = {
    books: Book[],
    editBook: (book: Book) => void
}

export const EditBook: React.FC<EditBookProps> = ({books, editBook}) => {

    const {id} = useParams();

    const book: Book | undefined = books.find(book => book.id === id);

    const [title, setTitle] = useState<string>(book?.title || "")
    const [author, setAuthor] = useState<string>(book?.author || "")

    const onTitleChange = (event: ChangeEvent<HTMLInputElement>): void => {
        setTitle(event.target.value)
    }

    const onAuthorChange = (event: ChangeEvent<HTMLInputElement>): void => {
        setAuthor(event.target.value)
    }

    const onSubmitEdit = (event: FormEvent<HTMLFormElement>): void => {
        event.preventDefault()
        editBook({
            id: book?.id || "",
            title, author
        })
    }

    return (
        <div className="book-detail">
            <div className="book">
                <form onSubmit={onSubmitEdit}>
                    <div>

                        <div>Title</div>
                        <input name="title" value={title} type="text" onChange={onTitleChange} placeholder="Title..."/>
                    </div>
                    <div>
                        <div>Author</div>
                        <input name="author" value={author} type="text" onChange={onAuthorChange}
                               placeholder="Author..."/>
                    </div>
                    <button type="submit">Edit book</button>
                </form>
            </div>
        </div>
    );

}