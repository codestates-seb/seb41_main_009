import create from 'zustand';

const useSidebarStore = create(set => ({
  currentTab: 'Home',
  setCurrentTab: tab => set(() => ({ currentTab: tab })),
}));

export default useSidebarStore;
