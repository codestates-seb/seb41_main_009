import axios from 'axios';
import create from 'zustand';
import { TEST_SHOWCASE_LIST_RES } from '../tests/showcaseDummy';

const useShowcaseStore = create((set, get) => ({
  disabledFlag: false,
  setDisabledFlag: boolean => set({ disabledFlag: boolean }),

  offset: -1,
  setOffset: offset => set({ offset }),

  isLoading: false,
  error: null,
  itemList: [],
  getItemList: async (count, callback) => {
    const { disabledFlag, offset, isLoading } = get();
    const body = {
      params: {
        offset,
        size: count,
      },
    };
    // 중복 실행 방지
    if (isLoading) return;

    // 더이상 불러올게 없으면 callback 실행 후 리턴
    if (disabledFlag === true) {
      console.log('disabled flag is on');
      return;
    }

    try {
      set({ isLoading: true });
      const response = await axios.get('/showcases', body);
      console.log(response);

      const { data, pageInfo } = response.data;
      console.log(data);
      console.log(pageInfo);

      // for Offset-based Pagination
      // response.data 의 맨마지막 요소의 id 를 offset 으로 설정
      const newOffset = data.slice(-1).id;

      // itemList 업데이트
      set(state => ({ itemList: state.itemList.concat(...data), offset: newOffset }));

      console.log(`newOffset: ${newOffset}`);
      console.log(`itemList: ${get().itemList}`);

      // 더 이상 불러올 쇼케이스가 없는경우
      if (pageInfo.hasNext === false) {
        set({ disabledFlag: true, isLoading: false });
        callback();
        return;
      }
    } catch (err) {
      set({ error: err.response, disabledFlag: true });
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

  postComment: async (id, contents) => {
    try {
      await axios.post(`/showcases/${id}/comments`, contents);
      console.log('코멘트를 달았습니다.');
    } catch (err) {
      console.log(err);
    }
  },
}));

export default useShowcaseStore;
