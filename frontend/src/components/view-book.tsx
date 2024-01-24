import {Book} from "../types/Book.ts";

type ViewBookProps = {
    book: Book
}

export const ViewBook: React.FC<ViewBookProps> = ({book}) => {

    return (
        <div className="book">
            {book.title}
        </div>
    );

}