import axios from "../api/AxiosInstance";
import type { IBorrow } from "./IBorrow";

const baseUrl = "http://localhost:8080/borrows";

export const borrowBook = async (id: number): Promise<void> => {
  await axios.post(`${baseUrl}/${id}`);
};

export const getBorrowList = async (): Promise<IBorrow[]> => {
  const response = await axios.get(`${baseUrl}`);
  return response.data;
};

export const returnBook = async (id: number): Promise<void> => {
  await axios.put(`${baseUrl}/${id}`);
};
