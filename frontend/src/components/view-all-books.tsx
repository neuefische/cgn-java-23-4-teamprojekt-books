import {Book} from "../Book.ts";

type ViewAllBooksProps = {
    books: Book[]
    handleBookDelete: (id: string) => void
}

export const ViewAllBooks: React.FC<ViewAllBooksProps> = (props: ViewAllBooksProps) => {

    const handleBookDelete = (id: string) => {
        props.handleBookDelete(id)
    }

    return (
        <div className="books">
            {props.books.map(book => (<div key={book.id} className="book">
                {book.title}
                <button className="book-delete-button" onClick={() => handleBookDelete(book.id)}> Delete</button>
            </div>))}
        </div>
    );

}