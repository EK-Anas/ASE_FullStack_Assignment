import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { useEffect, useState, type ChangeEvent, type FormEvent } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getUser } from "./UserService";
import type { User } from "./IUser";

const UserForm = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const parsedId = id ? Number(id) : NaN;
  const isValidId = Number.isInteger(parsedId) && parsedId > 0;

  const { data, isLoading, error } = useQuery<User, Error>({
    queryKey: ["user", parsedId],
    queryFn: () => getUser(parsedId),
    enabled: isValidId,
  });

  const [userFormData, setUserFormData] = useState<User>({
    id: 0,
    name: "",
    course: "",
    department: "",
    email: "",
    role: "",
    phone: "",
  });

  useEffect(() => {
    if (!data) return;

    setUserFormData({
      id: data.id || 0,
      name: data.name || "",
      email: data.email || "",
      phone: data.phone || "",
      role: data.role || "",
      course: data.course || "",
      department: data.department || "",
    });
  }, [data]);

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    let payload: any = { ...userFormData };
    payload.otherInfo =
      userFormData.role === "STUDENT"
        ? userFormData.course
        : userFormData.department;

    if (isValidId) {
      axios
        .put("http://localhost:8080/user", payload)
        .then(() => navigate("/users"));
    } else {
      axios
        .post("http://localhost:8080/user", payload)
        .then(() => navigate("/users"));
    }
  };

  const handleSelection = (event: ChangeEvent<HTMLSelectElement>) => {
    const { name, value } = event.target;
    setUserFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setUserFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div className="h-screen flex flex-col justify-center items-center">
      <h1 className="text-3xl font-bold">User Form</h1>
      <form className="p-8 flex flex-col gap-6" onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="name"
          id="name"
          name="name"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={userFormData.name}
          onChange={handleChange}
        />
        <input
          type="text"
          placeholder="email"
          id="email"
          name="email"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={userFormData.email}
          onChange={handleChange}
        />
        <input
          type="text"
          placeholder="phone"
          id="phone"
          name="phone"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={userFormData.phone}
          onChange={handleChange}
        />
        <select
          name="role"
          id="role"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={userFormData.role}
          onChange={handleSelection}
          disabled={isValidId}
        >
          <option value="" disabled>
            Select Role
          </option>
          <option value="STUDENT">Student</option>
          <option value="TEACHER">Teacher</option>
        </select>
        {userFormData.role !== "" && (
          <input
            type="text"
            placeholder={
              userFormData.role === "STUDENT" ? "course" : "department"
            }
            id="otherInfo"
            name={userFormData.role === "STUDENT" ? "course" : "department"}
            className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
            value={
              userFormData.role === "STUDENT"
                ? userFormData.course
                : userFormData.department
            }
            onChange={handleChange}
          />
        )}

        <button
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          type="submit"
        >
          {isValidId ? "Update User" : "Add User"}
        </button>
      </form>
    </div>
  );
};

export default UserForm;
