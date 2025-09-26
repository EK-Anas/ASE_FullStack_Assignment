import axios, { AxiosError, type AxiosResponse } from "axios";
import { toast } from "react-toastify";

const instance = axios.create();

instance.interceptors.response.use(
  (response: AxiosResponse) => {
    if (
      response.status >= 200 &&
      response.status < 300 &&
      response.config.method != "get"
    ) {
      toast.success("Success");
    }
    return response;
  },
  (error: AxiosError) => {
    const response = error?.response;
    if (response) {
      if (response.status !== 200) {
        const errorMsg: any = response.data;
        toast.error(errorMsg, { autoClose: 3000 });
      }
    } else {
      toast.error(error.message || "Network error");
    }
    return Promise.reject(error);
  }
);

export default instance;
