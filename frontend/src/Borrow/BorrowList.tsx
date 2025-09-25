import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { getBorrowList, returnBook } from "./BorrowService";
import type { IBorrow } from "./IBorrow";
import type { ColDef } from "ag-grid-community";
import Table from "../Shared/Table";

const BorrowList = () => {
  const queryClient = useQueryClient();
  const { data, isLoading, error } = useQuery<IBorrow[], Error>({
    queryKey: ["borrows"],
    queryFn: getBorrowList,
  });

  const returnMutation = useMutation({
    mutationFn: (id: number) => returnBook(id),
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["borrows"],
      });
    },
  });

  const cols: ColDef<IBorrow>[] = [
    {
      field: "id",
    },
    {
      field: "bookId",
    },
    {
      field: "userId",
    },
    {
      field: "borrowDate",
    },
    {
      field: "returnDate",
    },
    {
      headerName: "action",
      width: 100,
      cellRenderer: (params: any) => {
        const data: IBorrow = params.data;
        if (data.returnDate) return <span className="text-red-400">N/A</span>;

        return (
          <span
            className="text-green-500"
            onClick={() => {
              if (confirm("are you sure you want to return the book?"))
                returnMutation.mutate(data.id);
            }}
          >
            Return
          </span>
        );
      },
    },
  ];

  if (isLoading) return <>Loading</>;
  if (error) return <>Error</>;
  return (
    <div className="p-8">
      <div className="flex justify-center">
        <Table rowData={data} columnDefs={cols} />
      </div>
    </div>
  );
};

export default BorrowList;
