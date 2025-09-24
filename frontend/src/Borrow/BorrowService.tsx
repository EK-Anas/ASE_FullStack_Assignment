import axios from "axios";
import type { IBorrow } from "./IBorrow";

const baseUrl = "http://localhost:8080";

export const borrowBook = async (id: number): Promise<void> => {
  await axios.put(`${baseUrl}/burrow/${id}`);
};

export const getBorrowList = async (): Promise<IBorrow[]> => {
  const response = await axios.get(`${baseUrl}/burrows`);
  return response.data;
};

export const returnBook = async (id: number): Promise<void> => {
  await axios.put(`${baseUrl}/burrow/return/${id}`);
};
