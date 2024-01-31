import * as React from "react";
import { ChangeEvent, FormEvent, useState } from "react";
import Modal from "@mui/joy/Modal";
import ModalClose from "@mui/joy/ModalClose";
import Sheet from "@mui/joy/Sheet";
import { Book } from "../types/Book.ts";

type EditBookProps = {
  book: Book;
  editBook: (book: Book) => void;
};

export const EditBookModal: React.FC<EditBookProps> = ({ book, editBook }) => {
  const [isModalOpen, setIsModalOpen] = React.useState<boolean>(false);

  const [localBook, setLocalBook] = useState<Book>(book);

  const keyToNameMap = {
    title: "Title",
    author: "Author",
    isbn: "ISBN",
    genre: "Genre",
    publicationDate: "Publication Date",
    imageUrl: "Cover Image (URL)",
  };

  const onBookChange = (event: ChangeEvent<HTMLInputElement>): void => {
    setLocalBook({ ...book, [event.target.name]: event.target.value });
  };

  const onSubmitEdit = (event: FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    editBook(localBook);
    setIsModalOpen(false);
  };

  return (
    <React.Fragment>
      <button className="h-8 rounded-lg px-3" onClick={() => setIsModalOpen(true)}>
        Edit
      </button>
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
          <form className="flex flex-col gap-2" onSubmit={onSubmitEdit}>
            {(Object.keys(keyToNameMap) as [keyof typeof keyToNameMap]).map((key) => (
              <div key={key} className="flex justify-between">
                <div>{keyToNameMap[key]}</div>
                <input
                  name={key}
                  value={localBook[key]}
                  type="text"
                  onChange={onBookChange}
                  placeholder={`${keyToNameMap[key]}...`}
                />
              </div>
            ))}
            <button type="submit">Edit book</button>
          </form>
        </Sheet>
      </Modal>
    </React.Fragment>
  );
};
