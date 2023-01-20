import create from 'zustand';
import { persist } from 'zustand/middleware';

const useAuthStore = create(
  persist(
    set => ({
      currentUserId: 0,
      setUserId: userId => set({ currentUserId: userId }),
    }),
    {
      name: 'userId-storage',
      getStorage: () => sessionStorage,
    },
  ),
);

export default useAuthStore;
