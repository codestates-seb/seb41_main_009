import axios from 'axios';
import create from 'zustand';
import Swal from 'sweetalert2';
import { INVALID_LOGIN } from '../constants/Messages';

const useShowcaseStore = create((set, get) => ({
  disabledFlag: false,
  setDisabledFlag: boolean => set({ disabledFlag: boolean }),

  offset: -1,
  setOffset: offset => set({ offset }),

  isLoading: false,
  error: null,
  itemList: [],

  initializeStore: () => set({ itemList: [], offset: -1, disabledFlag: false }),

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
      return;
    }

    try {
      set({ isLoading: true });
      const response = await axios.get('/showcases', body);

      const { data, pageInfo } = response.data;

      // for Offset-based Pagination
      // response.data 의 맨마지막 요소의 id 를 offset 으로 설정
      const newOffset = data.slice(-1)[0].id;

      // itemList 업데이트
      set(state => ({ itemList: state.itemList.concat(...data), offset: newOffset }));

      // 더 이상 불러올 쇼케이스가 없는경우
      if (pageInfo.hasNext === false) {
        set({ disabledFlag: true, isLoading: false });
        callback();
        return;
      }
    } catch (err) {
      set({ error: err.response, disabledFlag: true });
    }
    set({ isLoading: false });
  },

  postComment: async (id, content, currentUserId, handleInit) => {
    try {
      if (!currentUserId) {
        Swal.fire({ title: INVALID_LOGIN, confirmButtonColor: 'Orange' });
        return;
      }

      await axios.post(
        `/showcases/${id}/comments`,
        { content },
        {
          'Content-Type': 'application/json',
        },
      );

      const { itemList } = get();
      const targetIndex = itemList.findIndex(el => el.id === id);
      if (itemList[targetIndex]?.lastComment === undefined) {
        const newArray = [...itemList];
        newArray[targetIndex].lastComment = {
          id: 1, // 댓글 식별자
          content, // 댓글 내용
          writer: {
            // 댓글 작성자 정보
            id: currentUserId, // 댓글 작성자 식별자
            nickname: '나', // 댓글 작성자 닉네임
          },
        };
        set({
          itemList: newArray,
        });
      }

      handleInit();
    } catch (err) {
      console.log(err);
    }
  },
}));

export default useShowcaseStore;
