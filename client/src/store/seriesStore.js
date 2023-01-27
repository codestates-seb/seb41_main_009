import create from 'zustand';

const useSeriesStore = create(set => ({
  isToggleBlur: true,

  setBlur: () => {
    set(state => ({ isToggleBlur: !state.isToggleBlur }));
  },
}));

export default useSeriesStore;
