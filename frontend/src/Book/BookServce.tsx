import axios from "axios";
import type { Book } from "./IBook";

const baseUrl: string = "http://localhost:8080";

export const getBooks = async (): Promise<Book[]> => {
  const response = await axios.get(`${baseUrl}/books`);
  return response.data;
};

export const deleteBook = async (id: number): Promise<void> => {
  await axios.delete(`${baseUrl}/book/${id}`);
};

export const getBook = async (id: number): Promise<Book> => {
  const response = await axios.get(`${baseUrl}/book/${id}`);
  return response.data;
};
