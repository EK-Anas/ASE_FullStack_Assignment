import axios, { AxiosError, type AxiosResponse } from "axios";
import { toast } from "react-toastify";

const instance = axios.create();

instance.interceptors.response.use(
  (response: AxiosResponse) => {
    if (response.statusText === "OK") {
      toast.success(response.data?.message || "Operation successful");
    }
    return response;
  },
  (error: AxiosError) => {
    const response = error?.response;
    if (response) {
      if (response.status !== 200) {
        // toast.error(response.data?. || `Request failed (${response.status})`);
      }
    } else {
      toast.error(error.message || "Network error");
    }
    return Promise.reject(error);
  }
);

export default instance;
