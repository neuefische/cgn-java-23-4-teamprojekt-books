import * as React from 'react';
import Button from '@mui/joy/Button';
import Modal from '@mui/joy/Modal';
import ModalClose from '@mui/joy/ModalClose';
import Typography from '@mui/joy/Typography';
import Sheet from '@mui/joy/Sheet';
import {FormEvent, useState} from "react";
import {Book} from "../types/Book.ts";

type AddNewBookProps = {
    saveBook: (bookToSave: Book) => void
}

export default function BasicModal(props: Readonly<AddNewBookProps>) {
    const [open, setOpen] = React.useState<boolean>(false);
    const [title, setTitle] = useState<string>("")
    const [author, setAuthor] = useState<string>("")
    const [isbn, setIsbn] = useState<string>("")
    const [genre, setGenre] = useState<string>("")
    const [publicationDate, setPublicationDate] = useState<string>("")
    const [imageUrl, setImageUrl] = useState<string>("")

    const onBookSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        const bookToSave: Book = {
            id: "1",
            title: title,
            author: author,
            isbn: isbn,
            genre: genre,
            publicationDate: publicationDate,
            imageUrl: imageUrl
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
        <React.Fragment>
            <Button variant="outlined" color="neutral" onClick={() => setOpen(true)}>
                Add new Book
            </Button>
            <Modal
                aria-labelledby="modal-title"
                aria-describedby="modal-desc"
                open={open}
                onClose={() => setOpen(false)}
                sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}
            >
                <Sheet
                    variant="outlined"
                    sx={{
                        maxWidth: 500,
                        borderRadius: 'md',
                        p: 3,
                        boxShadow: 'lg',
                    }}
                >
                    <ModalClose variant="plain" sx={{ m: 1 }} />
                    <Typography
                        component="h2"
                        id="add-new-book"
                        level="h4"
                        textColor="inherit"
                        fontWeight="lg"
                        mb={1}
                    >
                        Insert book information
                    </Typography>
                    <form onSubmit={onBookSubmit}>
                        <br/> Title <input value={title} onChange={event => setTitle(event.target.value)}
                                           placeholder=""/>
                        <br/> Author <input value={author} onChange={event => setAuthor(event.target.value)}
                                            placeholder=""/>
                        <br/> ISBN <input value={isbn} onChange={event => setIsbn(event.target.value)} placeholder=""/>
                        <br/> Genre <input value={genre} onChange={event => setGenre(event.target.value)}
                                           placeholder=""/>
                        <br/> Publication Date <input value={publicationDate}
                                                      onChange={event => setPublicationDate(event.target.value)}
                                                      placeholder=""/>
                        <br/> Image URL <input value={imageUrl} onChange={event => setImageUrl(event.target.value)}
                                               placeholder=""/>
                        <button type="submit">Save</button>
                    </form>
                </Sheet>
            </Modal>
        </React.Fragment>
    );
}