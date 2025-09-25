import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router-dom";
import { addBook, getBook, updateBook } from "./BookServce";
import type { Book } from "./IBook";
import { useEffect, useState, type ChangeEvent, type FormEvent } from "react";

const BookForm = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

  const parsedId = id ? Number(id) : NaN;
  const isValidId = Number.isInteger(parsedId) && parsedId > 0;

  const { data, isLoading, error } = useQuery<Book, Error>({
    queryKey: ["book", parsedId],
    queryFn: () => getBook(parsedId),
    enabled: isValidId,
  });

  const addMutation = useMutation({
    mutationFn: (book: Book) => addBook(book),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["books", "book", parsedId] });
      navigate("/books");
    },
  });
  const updateMutation = useMutation({
    mutationFn: (book: Book) => updateBook(book),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["books", "book", parsedId] });
      navigate("/books");
    },
  });

  const [bookFormData, setBookFormData] = useState<Book>({
    id: 0,
    title: "",
    author: "",
    isbn: "",
    category: "",
    shelfLocation: "",
    copiesAvailable: 0,
    fileUrl: "",
    fileSizeMb: 0,
    ebook: false,
  });

  useEffect(() => {
    if (!data) return;
    setBookFormData(data);
    setBookFormData((prev) => ({
      ...prev,
      ebook: data.fileUrl ? true : false,
    }));
  }, [data]);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setBookFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (isValidId) updateMutation.mutate(bookFormData);
    else addMutation.mutate(bookFormData);
  };

  const handleSelection = (e: ChangeEvent<HTMLSelectElement>) => {
    const { value } = e.target;

    setBookFormData((prev) => ({
      ...prev,
      category: value,
    }));
  };

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  return (
    <div className="h-screen flex flex-col justify-center items-center">
      <h1 className="text-3xl font-bold">Book Form</h1>
      <form className="p-8 flex flex-col gap-6" onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="title"
          id="title"
          name="title"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={bookFormData.title}
          onChange={handleChange}
        />
        <input
          type="text"
          placeholder="author"
          id="author"
          name="author"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={bookFormData.author}
          onChange={handleChange}
        />
        <input
          type="text"
          placeholder="isbn"
          id="isbn"
          name="isbn"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={bookFormData.isbn}
          onChange={handleChange}
        />
        <select
          name="category"
          id="category"
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          value={bookFormData.category}
          onChange={handleSelection}
          disabled={isValidId}
        >
          <option value="" disabled>
            Select Category
          </option>
          <option value="FICTION">Fiction</option>
          <option value="NON_FICTION">Non Fiction</option>
          <option value="RESEARCH">Research</option>
        </select>
        <div className="flex flex-row gap-4 items-center pl-5">
          <label>
            <input
              type="radio"
              name="bookType"
              value="isEbook"
              checked={bookFormData.ebook === true}
              onChange={() =>
                setBookFormData((prev) => ({ ...prev, ebook: true }))
              }
            />
            Ebook
          </label>
          <label>
            <input
              type="radio"
              name="bookType"
              value="phys"
              checked={bookFormData.ebook === false}
              onChange={() =>
                setBookFormData((prev) => ({ ...prev, ebook: false }))
              }
            />
            Physical book
          </label>
        </div>
        {bookFormData.ebook != null && (
          <>
            <input
              type="text"
              placeholder={bookFormData.ebook ? "fileUrl" : "shelfLocation"}
              id={bookFormData.ebook ? "fileUrl" : "shelfLocation"}
              name={bookFormData.ebook ? "fileUrl" : "shelfLocation"}
              className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
              value={
                bookFormData.ebook
                  ? bookFormData.fileUrl
                  : bookFormData.shelfLocation
              }
              onChange={handleChange}
            />
            <input
              type="text"
              placeholder={
                bookFormData.ebook ? "fileSizeMb" : "copiesAvailable"
              }
              id={bookFormData.ebook ? "fileSizeMb" : "copiesAvailable"}
              name={bookFormData.ebook ? "fileSizeMb" : "copiesAvailable"}
              className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
              value={
                bookFormData.ebook
                  ? bookFormData.fileSizeMb
                  : bookFormData.copiesAvailable
              }
              onChange={handleChange}
            />
          </>
        )}

        <button
          className="border border-gray-300 rounded px-4 py-2 w-full bg-black"
          type="submit"
        >
          {isValidId ? "Update Book" : "Add Book"}
        </button>
      </form>
    </div>
  );
};

export default BookForm;
