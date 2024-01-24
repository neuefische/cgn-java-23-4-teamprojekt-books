import styled from "styled-components";
import {useParams} from "react-router-dom";
import {ViewAllBooksProps} from "../types/TypeBooksList.tsx";

export default function BookDetailsPage(props:ViewAllBooksProps){
    const params = useParams()
    const id = params.id
    if(!id){
        return <p>Book with such id was not found</p>
    }
    const foundBook = props.books.find((book)=>book.id == id);
    if(!foundBook){
        return<p>Book not found</p>
    }
    return(
    <StyledSection>
        <p>Titel:</p>
<h1>{foundBook?.title}</h1>
        <p>Author:</p>
        <h2>{foundBook?.author}</h2>
    </StyledSection>
    )
}
const StyledSection = styled.section`
    width:50vw;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: auto;
    padding: 5vw;
    margin: 5vw;
    border-radius: 2vw;
    border: darkcyan solid 1px;
    `;