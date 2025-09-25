import axios from "../api/AxiosInstance";
import type { User } from "./IUser";

const baseUrl: string = "http://localhost:8080/users";

export const updateUser = async (payload: User): Promise<void> => {
  await axios.put(`${baseUrl}/${payload.id}`, payload);
};

export const getUsers = async (): Promise<User[]> => {
  const response = await axios.get<User[]>(`${baseUrl}`);
  return response.data;
};

export const getUser = async (id: number): Promise<User> => {
  const response = await axios.get<User>(`${baseUrl}/${id}`);
  return response.data;
};

export const deleteUser = async (id: number): Promise<void> => {
  await axios.delete(`${baseUrl}/${id}`);
};

export const addUser = async (payload: User): Promise<void> => {
  await axios.post(`${baseUrl}`, payload);
};
