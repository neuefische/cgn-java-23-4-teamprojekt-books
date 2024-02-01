import * as React from "react";
import { FormEvent, useState } from "react";
import Button from "@mui/joy/Button";
import Modal from "@mui/joy/Modal";
import ModalClose from "@mui/joy/ModalClose";
import Sheet from "@mui/joy/Sheet";
import { Book } from "../types/Book.ts";
import axios from "axios";

type AddBookModalProps = {
  saveBook: (bookToSave: Book) => void;
};

export default function AddBookModal(props: Readonly<AddBookModalProps>) {
  const [isModalOpen, setIsModalOpen] = React.useState<boolean>(false);
  const [title, setTitle] = useState<string>("");
  const [author, setAuthor] = useState<string>("");
  const [isbn, setIsbn] = useState<string>("");
  const [genre, setGenre] = useState<string>("");
  const [publicationDate, setPublicationDate] = useState<string>("");
  const [imageUrl, setImageUrl] = useState<string>("");
  const [blurb, setBlurb] = useState<string>("");

  const onBookSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const bookToSave: Book = {
      id: "",
      title: title,
      author: author,
      isbn: isbn,
      genre: genre,
      publicationDate: publicationDate,
      imageUrl: imageUrl,
      blurb: blurb
    };

    props.saveBook(bookToSave);
    setTitle("");
    setAuthor("");
    setIsbn("");
    setGenre("");
    setPublicationDate("");
    setImageUrl("");
    setBlurb("");

    setIsModalOpen(false);
  };

  const handleOnBlurIsbn = () => {
    if (isModalOpen) {
      axios
        .get(`/api/googleBooks/${isbn}`)
        .then((response) => {
          setTitle(response.data.title);
          setAuthor(response.data.authors[0]);
          setImageUrl(response.data.imageLinks?.thumbnail || "");
          setBlurb(response.data.description);
        })
        .catch(() => console.log("No Result for Google Books Api Request with given ISBN"));
    }
  };

  return (
    <React.Fragment>
      <Button
        variant="outlined"
        color="neutral"
        onClick={() => {
          setTitle("");
          setAuthor("");
          setIsbn("");
          setGenre("");
          setPublicationDate("");
          setImageUrl("");
          setBlurb("");
          setIsModalOpen(true);
        }}
      >
        Add new Book
      </Button>
      <Modal
        className="flex flex-col items-center justify-center"
        aria-labelledby="modal-title"
        aria-describedby="modal-desc"
        open={isModalOpen}
        onClose={() => setIsModalOpen(false)}
      >
        <Sheet className="rounded-md p-7">
          <ModalClose variant="plain" />
          <h2 className="mb-5 flex justify-center text-lg font-bold">Insert book information</h2>
          <form className="flex flex-col gap-3" onSubmit={onBookSubmit}>
            <div className="flex justify-between">
              ISBN
              <input
                value={isbn}
                onChange={(event) => setIsbn(event.target.value)}
                onBlur={handleOnBlurIsbn}
                placeholder=""
              />
            </div>
            <div className="flex justify-between">
              Title <input value={title} onChange={(event) => setTitle(event.target.value)} placeholder="" />
            </div>
            <div className="flex justify-between">
              Author <input value={author} onChange={(event) => setAuthor(event.target.value)} placeholder="" />
            </div>
            <div className="flex justify-between">
              Genre <input value={genre} onChange={(event) => setGenre(event.target.value)} placeholder="" />
            </div>
            <div className="flex justify-between gap-3">
              Publication Date
              <input
                value={publicationDate}
                onChange={(event) => setPublicationDate(event.target.value)}
                placeholder=""
              />
            </div>
            <div className="flex justify-between">
              Image URL <input value={imageUrl} onChange={(event) => setImageUrl(event.target.value)} placeholder="" />
            </div>
            <div className="flex justify-between">
              Book Blurb
              <textarea
                value={blurb}
                onChange={(event) => setBlurb(event.target.value)}
                rows={5}
                cols={20}
                placeholder=""
              />
            </div>
            <button type="submit">Save</button>
          </form>
        </Sheet>
      </Modal>
    </React.Fragment>
  );
}
