import axios from 'axios';
import create from 'zustand';
import { TEST_SHOWCASE_MODAL_RES } from '../tests/showcaseDummy';

const useShowcaseModal = create((set, get) => ({
  isModalOpen: false,
  isModalLoading: false,
  modalItem: {
    ...TEST_SHOWCASE_MODAL_RES,
  },
  toggleModalOpen: () => {
    set(state => ({ isModalOpen: !state.isModalOpen }));
  },
  getModalItem: async id => {
    try {
      set({ isModalLoading: true });
      const response = await axios.get(`/showcases/${id}`);
      set(() => ({ modalItem: response.data, isModalOpen: true }));
    } catch (err) {
      set({ error: err.response });
      console.log(err.response);
    }
    set({ isModalLoading: false });
  },
  // TODO: 2차 구현시 지우기
  // 무한스크롤 테스트용 함수
  getModalItemTest: async id => {
    set({ modalItem: { ...TEST_SHOWCASE_MODAL_RES, id } });
    get().toggleModalOpen();
  },
  // TODO: 2차 페이지구현시 작성
  postComment: () => {},
  editComment: () => {},
  deleteComment: () => {},
}));

export default useShowcaseModal;
