import { defineStore } from "pinia";
import { getJwtToken, getUserInfo } from "/httputils.js";

export const useTokenStore = defineStore("token", {
  state: () => ({
    jwtToken: null,
    loggedInUser: null,
  }),
  persist: {
    storage: sessionStorage, // note that data in sessionStorage is cleared when the page session ends
  },

  actions: {
    async getTokenAndSaveInStore(username, password) {
      try {
        console.log("getTokenAndSaveInStore called");
        let response = await getJwtToken(username, password);
        let data = response.data;
        console.log("data:");
        console.log(data);
        if (data != null && data != "" && data != undefined) {
          console.log("this.jwtToken = data");
          this.jwtToken = data;
          let response = await getUserInfo(username, password, this.jwtToken);
          console.log("this.loggedInUser = response.data");
          this.loggedInUser = response.data;
        }
      } catch (err) {
        console.log(err);
      }
    },
  },
});
