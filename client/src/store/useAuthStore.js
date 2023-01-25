import create from 'zustand';
import { createJSONStorage, persist } from 'zustand/middleware';

const useAuthStore = create(
  persist(
    set => ({
      currentUserId: 0,
      setUserId: userId => set({ currentUserId: userId }),
    }),
    {
      name: 'userId-storage',
      storage: createJSONStorage(() => sessionStorage),
    },
  ),
);

export default useAuthStore;
