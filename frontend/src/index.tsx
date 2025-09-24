import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { AllCommunityModule, ModuleRegistry } from "ag-grid-community";

const rootEl = document.getElementById("root");
ModuleRegistry.registerModules([AllCommunityModule]);
if (rootEl) {
  const root = ReactDOM.createRoot(rootEl);
  root.render(
    <React.StrictMode>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </React.StrictMode>
  );
}
