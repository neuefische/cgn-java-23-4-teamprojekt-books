import {Book} from "../types/Book.ts";
import {Link, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";



export default function ViewBook(){
const [book, setBooks] = useState<Book>();
    const {id} = useParams();
   useEffect(() => {
        axios.get(`/api/books/${id}`).then(response => setBooks(response.data))

    }, [])


    return (
        <div className="book-detail">
            <div className="book">
                <div>{book?.title}</div>
                <div>{book?.author}</div>
                <Link to={`/books/${book?.id}/edit`}>
                    <button>Edit</button>
                </Link>
            </div>
        </div>
    );

}