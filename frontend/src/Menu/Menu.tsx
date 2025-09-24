import { NavLink } from "react-router-dom";

const links = [
  {
    title: "users",
    link: "/users",
  },
  {
    title: "books",
    link: "/books",
  },
  {
    title: "borrow",
    link: "/borrow",
  },
];

const Menu = () => {
  return (
    <header className="bg-emerald-600">
      <nav>
        <ul className="flex justify-center items-center gap-100 py-4">
          {links.map((l, index) => (
            <li
              key={index}
              className="capitalize underline text-stone-800 text-xl"
            >
              <NavLink to={l.link}>{l.title}</NavLink>
            </li>
          ))}
        </ul>
      </nav>
    </header>
  );
};

export default Menu;
