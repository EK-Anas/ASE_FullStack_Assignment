import axios from "axios";
import type { Book } from "./IBook";

const baseUrl: string = "http://localhost:8080/books";

export const getBooks = async (): Promise<Book[]> => {
  const response = await axios.get(`${baseUrl}`);
  return response.data;
};

export const deleteBook = async (id: number): Promise<void> => {
  await axios.delete(`${baseUrl}/${id}`);
};

export const getBook = async (id: number): Promise<Book> => {
  const response = await axios.get(`${baseUrl}/${id}`);
  return response.data;
};

export const addBook = async (book: Book): Promise<void> => {
  await axios.post(`${baseUrl}`, book);
};

export const updateBook = async (book: Book): Promise<void> => {
  await axios.put(`${baseUrl}/${book.id}`, book);
};
