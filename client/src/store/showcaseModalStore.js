import axios from 'axios';
import create from 'zustand';

const useShowcaseModal = create((set, get) => ({
  isModalOpen: false,
  isModalLoading: false,
  modalItem: {},

  toggleModalOpen: () => {
    set(state => ({ isModalOpen: !state.isModalOpen }));
  },
  getModalItem: async id => {
    try {
      // Modal 로딩중
      set({ isModalLoading: true });

      // Showcase Modal Page 요청
      const response = await axios.get(`/showcases/${id}`);
      set(() => ({ modalItem: response.data, isModalOpen: true }));
    } catch (err) {
      set({ error: err.response });
    }

    set({ isModalLoading: false });
  },

  // 쇼케이스 삭제요청
  deleteShowcase: async () => {
    const { modalItem } = get();
    try {
      await axios.delete(`/showcases/${modalItem.id}`);
    } catch (err) {
      set({ error: err.response });
      console.log(err);
    }
  },
}));

export default useShowcaseModal;
