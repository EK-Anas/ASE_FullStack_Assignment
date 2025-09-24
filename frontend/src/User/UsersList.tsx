import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import Table from "../Shared/Table";
import { getUsers, deleteUser } from "./UserService";
import type { User } from "./IUser";
import type { ColDef } from "ag-grid-community";
import { NavLink } from "react-router-dom";

const UsersList = () => {
  const queryClient = useQueryClient();

  const { data, error, isLoading } = useQuery<User[], Error>({
    queryKey: ["users"],
    queryFn: getUsers,
    refetchIntervalInBackground: true,
    refetchInterval: 5000,
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => deleteUser(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });

  const columnDefs: ColDef<User>[] = [
    {
      field: "id",
      filter: "agTextColumnFilter",
      width: 100,
      cellRenderer: (params: any) => {
        let id = params.value;

        return <NavLink to={"add-user/" + id}>{id}</NavLink>;
      },
    },
    {
      field: "name",
      filter: "agTextColumnFilter",
    },
    {
      field: "email",
      filter: "agTextColumnFilter",
    },
    {
      field: "phone",
      filter: "agTextColumnFilter",
    },
    {
      field: "role",
      filter: "agTextColumnFilter",
    },
    {
      field: "course",
      filter: "agTextColumnFilter",
    },
    {
      field: "department",
      filter: "agTextColumnFilter",
    },
    {
      headerName: "Action",
      colId: "action",
      width: 100,
      cellRenderer: (params: any) => {
        const id = params.data?.id;
        return (
          <button
            className="text-red-400"
            onClick={() => {
              if (!id) return;
              if (confirm("Delete user " + id + "?")) {
                deleteMutation.mutate(id);
              }
            }}
          >
            Delete
          </button>
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
          to="/users/add-user/0"
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition"
        >
          Add User
        </NavLink>
      </div>
      <div className="flex justify-center">
        <Table rowData={data} columnDefs={columnDefs} />
      </div>
    </div>
  );
};

export default UsersList;
