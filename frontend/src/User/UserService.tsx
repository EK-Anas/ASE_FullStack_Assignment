import axios from "axios";
import type { User } from "./IUser";

const baseUrl: string = "http://localhost:8080";

export const getUsers = async (): Promise<User[]> => {
  const response = await axios.get<User[]>(`${baseUrl}/users`);
  return response.data;
};

export const getUser = async (id: number): Promise<User> => {
  const response = await axios.get<User>(`${baseUrl}/user/${id}`);
  return response.data;
};

export const deleteUser = async (id: number): Promise<void> => {
  await axios.delete(`${baseUrl}/user/${id}`);
};
