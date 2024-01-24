import {ViewAllBooksProps} from "../types/TypeBooksList.tsx";
import {Link} from "react-router-dom";

export const ViewAllBooks: React.FC<ViewAllBooksProps> = ({books}) => {

    return (
        <div className="books">
            {books.map(book => (<Link to={`/${book.id}`} key={book.id} className="book">{book.title}</Link>))}
        </div>
    );

}