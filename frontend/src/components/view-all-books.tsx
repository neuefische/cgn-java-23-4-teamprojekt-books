import {Book} from "../Book.ts";

type ViewAllBooksProps = {
    books: Book[]
}

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({books}) => {

    return (
        <div className="books">
            {books.map(book => (<div key={book.id} className="book">{book.title}</div>))}
        </div>
    );

}