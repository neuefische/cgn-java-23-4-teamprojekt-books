import {Book} from "./types/Book.ts";

import {ChangeEvent, FormEvent, useState} from "react";

  type AddNewBookProps = {
    saveBook: (bookToSave: Book) => void
}


export default function AddNewBook(props : AddNewBookProps){

    const [title, setTitle] = useState<string>("")
    const [author, setAuthor] = useState<string>("")

    function onTitleChange(event:ChangeEvent<HTMLInputElement>) {
            setTitle(event.target.value);
    }

    function onAuthorChange(event: ChangeEvent<HTMLInputElement>) {
            setAuthor(event.target.value)
    }

    const onBookSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        const bookToSave: Book = {
            id : "1",
            title: title,
            author: author,
        }

        props.saveBook(bookToSave)

        setTitle("")
        setAuthor("")
    }

          return(
              <div>

                  <p>Add Book</p>
                  <form onSubmit = {onBookSubmit}>
                      <input value={title} onChange={onTitleChange} placeholder="Title"/>
                      <input value={author} onChange={onAuthorChange} placeholder={"Author"}/>
                      <button type="submit">Save</button>
                  </form>
              </div>
          )


}