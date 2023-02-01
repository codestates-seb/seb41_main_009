import create from 'zustand';

const usePostStore = create(set => ({
  isOpen: false,
  setIsOpen: () => {
    set(state => ({ isOpen: !state.isOpen }));
  },

  currentSeriesId: '',
  setCurrentSeriesId: currentSeriesId => set({ currentSeriesId }),
}));

export default usePostStore;
