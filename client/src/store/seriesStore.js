import create from 'zustand';

const useSeriesStore = create(set => ({
  isToggleBlur: true,
  setBlur: () => {
    set(state => ({ isToggleBlur: !state.isToggleBlur }));
  },

  currentPostId: 0,
  setCurrentPostId: currentPostId => set({ currentPostId }),
}));

export default useSeriesStore;
