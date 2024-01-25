import {Book} from "./types/Book.ts";

import {ChangeEvent, FormEvent, useState} from "react";

type AddNewBookProps = {
    saveBook: (bookToSave: Book) => void
}


export default function AddNewBook(props: Readonly<AddNewBookProps>) {

    const [title, setTitle] = useState<string>("")
    const [author, setAuthor] = useState<string>("")
    const [isbn, setIsbn] = useState<string>("")
    const [genre, setGenre] = useState<string>("")
    const [publicationDate, setPublicationDate] = useState<string>("")
    const [imageUrl, setImageUrl] = useState<string>("")

    function onTitleChange(event: ChangeEvent<HTMLInputElement>) {
        setTitle(event.target.value);
    }

    function onAuthorChange(event: ChangeEvent<HTMLInputElement>) {
        setAuthor(event.target.value)
    }

    function onIsbnChange(event: ChangeEvent<HTMLInputElement>) {
        setIsbn(event.target.value)
    }

    function onGenreChange(event: ChangeEvent<HTMLInputElement>) {
        setGenre(event.target.value)
    }

    function onPublicationDateChange(event: ChangeEvent<HTMLInputElement>) {
        setPublicationDate(event.target.value)
    }

    function onImageUrlChange(event: ChangeEvent<HTMLInputElement>) {
        setImageUrl(event.target.value)
    }

    const onBookSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        const bookToSave: Book = {
            id: "1",
            title: title,
            author: author,
            isbn: isbn,
            genre: genre,
            publicationDate: publicationDate,
            imageURL: imageUrl
        }

        props.saveBook(bookToSave)
        setTitle("")
        setAuthor("")
        setIsbn("")
        setGenre("")
        setPublicationDate("")
        setImageUrl("")
    }

    return (
        <div className="add-new-book">
            <h2>Add new book</h2>
            <form onSubmit={onBookSubmit}>
                <div>
                    Title <br/> <input value={title} onChange={onTitleChange} placeholder=""/>
                </div>
                <div>
                    Author <br/> <input value={author} onChange={onAuthorChange} placeholder={""}/>
                </div>
                <div>
                    ISBN <br/> <input value={isbn} onChange={onIsbnChange} placeholder={""}/>
                </div>
                <div>
                    Genre <br/> <input value={genre} onChange={onGenreChange} placeholder={""}/>
                </div>
                <div>
                    Publication Date <br/> <input value={publicationDate} onChange={onPublicationDateChange}
                                                  placeholder={""}/>
                </div>
                <div>
                    Image URL <br/> <input value={imageUrl} onChange={onImageUrlChange} placeholder={""}/>
                </div>
                <button type="submit">Save</button>
            </form>
        </div>
    )


}