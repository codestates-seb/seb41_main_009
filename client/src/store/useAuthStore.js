import create from 'zustand';
import { createJSONStorage, persist } from 'zustand/middleware';

const useAuthStore = create(
  persist(
    set => ({
      currentUserId: 0,
      authorization: '',
      setUserId: userId => set(state => ({ ...state, currentUserId: userId })),
      setAuthorization: authCode => set(state => ({ ...state, authorization: authCode })),
    }),
    {
      name: 'authorization-storage',
      storage: createJSONStorage(() => sessionStorage),
    },
  ),
);

export default useAuthStore;
