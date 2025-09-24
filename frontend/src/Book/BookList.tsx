import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { deleteBook, getBooks } from "./BookServce";
import type { Book } from "./IBook";
import type { ColDef } from "ag-grid-community";
import { NavLink } from "react-router-dom";
import Table from "../Shared/Table";
import { borrowBook } from "../Borrow/BorrowService";

const BookList = () => {
  const queryClient = useQueryClient();

  const { data, isLoading, error } = useQuery<Book[], Error>({
    queryKey: ["books"],
    queryFn: getBooks,
    refetchIntervalInBackground: true,
    refetchInterval: 5000,
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => deleteBook(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["books"] });
    },
  });

  const borrowMutation = useMutation({
    mutationFn: (id: number) => borrowBook(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["books"] });
    },
  });

  const columnDefs: ColDef<Book>[] = [
    {
      field: "id",
      filter: "agTextColumnFilter",
      width: 100,
      cellRenderer: (params: any) => {
        let id = params.value;

        return <NavLink to={"add-book/" + id}>{id}</NavLink>;
      },
    },
    {
      field: "author",
      filter: "agTextColumnFilter",
    },
    {
      field: "category",
      filter: "agTextColumnFilter",
    },
    {
      field: "category",
      filter: "agTextColumnFilter",
    },
    {
      field: "isbn",
      filter: "agTextColumnFilter",
    },
    {
      field: "title",
      filter: "agTextColumnFilter",
    },
    {
      field: "copiesAvailable",
      filter: "agTextColumnFilter",
    },
    {
      field: "shelfLocation",
      filter: "agTextColumnFilter",
    },
    {
      field: "fileSizeMb",
      filter: "agTextColumnFilter",
    },
    {
      field: "fileUrl",
      filter: "agTextColumnFilter",
    },
    {
      headerName: "Action",
      colId: "action",
      width: 170,
      cellRenderer: (params: any) => {
        const id = params.data.id;

        return (
          <div className="flex gap-5">
            <button
              className="text-red-400"
              onClick={() => {
                if (confirm("delete book")) {
                  deleteMutation.mutate(id);
                }
              }}
            >
              Delete
            </button>
            <button
              className="text-green-400"
              onClick={() => {
                if (confirm("borrow book")) {
                  borrowMutation.mutate(id);
                }
              }}
            >
              Borrow
            </button>
          </div>
        );
      },
    },
  ];

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  return (
    <div className="p-8">
      <div className="flex justify-end ">
        <NavLink
          to="/books/add-book/0"
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition"
        >
          Add Book
        </NavLink>
      </div>
      <div className="flex justify-center">
        <Table rowData={data} columnDefs={columnDefs} />
      </div>
    </div>
  );
};

export default BookList;
