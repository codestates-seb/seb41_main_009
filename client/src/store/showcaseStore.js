import axios from 'axios';
import create from 'zustand';
import { TEST_SHOWCASE_LIST_RES } from '../tests/showcaseDummy';

const useShowcaseStore = create((set, get) => ({
  offset: 0,
  isLoading: false,
  error: null,
  itemList: [],
  getItemList: async count => {
    try {
      set({ isLoading: true });
      const response = await axios.get('/showcases', {
        params: {
          offset: get().offset,
          size: count,
        },
      });
      set(state => ({ itemList: state.itemList.concat(...response.data), offset: state.offset + count }));
    } catch (err) {
      set({ error: err.response });
      console.log(err.response);
    }
    set({ isLoading: false });
  },
  // TODO: 2차 구현시 지우기
  // 무한스크롤 테스트용 함수
  getItemListForTest: async () => {
    try {
      set({ isLoading: true });
      set(state => ({
        offset: state.offset + 6,
        itemList: state.itemList.concat(
          new Array(6).fill({}).map((_, idx) => {
            return {
              ...TEST_SHOWCASE_LIST_RES,
              id: state.itemList.length + idx,
            };
          }),
        ),
      }));
    } catch (err) {
      set({ error: err.response });
      console.log(err.response);
    }
    set({ isLoading: false });
    console.log(get().itemList);
  },
}));

export default useShowcaseStore;
