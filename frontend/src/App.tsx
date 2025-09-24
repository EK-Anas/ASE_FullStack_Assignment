import { Route, Routes } from "react-router-dom";
import "./App.css";
import Menu from "./Menu/Menu";
import UsersList from "./User/UsersList";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import UserForm from "./User/UserForm";
import BookList from "./Book/BookList";
import BookForm from "./Book/BookForm";
import BorrowList from "./Borrow/BorrowList";

const queryClient = new QueryClient();
const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <Menu />
      <Routes>
        <Route path="/users" element={<UsersList />}></Route>
        <Route path="/users/add-user/:id" element={<UserForm />}></Route>
        <Route path="/books" element={<BookList />}></Route>
        <Route path="books/add-book/:id" element={<BookForm />}></Route>
        <Route path="/borrow" element={<BorrowList />}></Route>
      </Routes>
    </QueryClientProvider>
  );
};

export default App;
