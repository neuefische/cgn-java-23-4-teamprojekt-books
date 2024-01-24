import {Book} from "../types/Book.ts";
import {ViewBook} from "./view-book.tsx";

type ViewAllBooksProps = {
    books: Book[]
}

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({books}) => {

    return (
        <div className="books">
            {books.map(book => (<ViewBook key={book.id} book={book}/>))}
        </div>
    );

}