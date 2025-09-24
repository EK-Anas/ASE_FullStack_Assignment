import { AgGridReact } from "ag-grid-react";
import type { ColDef } from "ag-grid-community";
import { colorSchemeDarkBlue, themeQuartz } from "ag-grid-community";

const tableTheme = themeQuartz.withPart(colorSchemeDarkBlue);

type Props<T> = {
  rowData: T[] | undefined;
  columnDefs: ColDef<T>[];
};

const Table = <T,>({ rowData, columnDefs }: Props<T>) => {
  return (
    <div className="h-[500px] w-[100%] pt-4">
      <AgGridReact
        rowData={rowData}
        columnDefs={columnDefs}
        theme={tableTheme}
      />
    </div>
  );
};
export default Table;
