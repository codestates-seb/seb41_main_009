import create from 'zustand';
import axios from 'axios';
import Swal from 'sweetalert2';
import {
  SELECT_CATEGORY,
  EMPTY_CONTENT_ERROR,
  EMPTY_TITLE_ERROR,
  MINIMUM_ONE_IMAGE_ERROR,
} from '../constants/Messages';

const useContentCreateStore = create((set, get) => ({
  // category
  categoryKey: '',
  categoryName: 'Category',
  setCategoryKey: categoryKey => {
    set({ categoryKey });
  },
  setCategoryName: categoryName => set({ categoryName }),

  // title
  title: '',
  setTitle: title => set({ title }),

  // content
  content: '',
  setContent: content => set({ content }),

  // image
  imageBase64: '',
  fileInfos: [],
  setImageBase64: data => set({ imageBase64: data }),
  setFileInfos: fileInfos => {
    set({ fileInfos });
  },

  // imageBlob
  imageBlob: '',
  setImageBlob: imageBlob => {
    set({ imageBlob });
  },

  // error handle
  errorMessage: '',
  setErrorMessage: errorMessage => set({ errorMessage }),

  // functions
  initStore: () =>
    set({
      categoryKey: '',
      categoryName: 'Category',
      title: '',
      content: '',
      imageBase64: '',
      fileInfos: [],
    }),

  // FileInfo API 를 사용하여 presigned url 을 받아오는 함수
  getPresignedURL: async (basePath, size, contentType) => {
    try {
      const response = await axios.post(
        `/${basePath}/files`,
        {
          size,
          contentType,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      );
      return response.data;
    } catch (err) {
      console.log(err);
      return {};
    }
  },

  uploadToGCS: async sigendURL => {
    // 블롭을 리턴
    const { imageBlob } = get();

    const response = await axios.put(sigendURL, imageBlob, {
      headers: {
        'Content-Type': `${imageBlob.type}`,
        authorization: null,
      },
      withCredentials: false,
    });

    return response;
  },
  postShowcase: async callback => {
    const { categoryKey, content, fileInfos, uploadToGCS } = get();
    try {
      // showcases 로 게시물 업로드
      if (categoryKey === '') {
        Swal.fire({ title: SELECT_CATEGORY, confirmButtonColor: 'Orange' });
        return;
      }

      if (content === '') {
        Swal.fire({ title: EMPTY_CONTENT_ERROR, confirmButtonColor: 'Orange' });
        return;
      }

      if (fileInfos.length === 0) {
        Swal.fire({ title: MINIMUM_ONE_IMAGE_ERROR, confirmButtonColor: 'Orange' });
        return;
      }

      const body = {
        content,
        category: categoryKey,
        fileInfos,
      };

      const response = await axios.post('/showcases', body, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      // signedURL 을 받아왔다면 해당 URL로 PUT 요청 보내기
      const { signedURL } = response.data.fileInfos[0];

      await uploadToGCS(signedURL);
      callback();
    } catch (error) {
      set({ errorMessage: error });
    }
  },

  postSeries: async callback => {
    const { categoryKey, title, content, fileInfos, getPresignedURL, uploadToGCS } = get();

    try {
      if (categoryKey === '') {
        Swal.fire({ title: SELECT_CATEGORY, confirmButtonColor: 'Orange' });
        return;
      }

      if (title === '') {
        Swal.fire({ title: EMPTY_TITLE_ERROR, confirmButtonColor: 'Orange' });
        return;
      }

      if (content === '') {
        Swal.fire({ title: EMPTY_CONTENT_ERROR, confirmButtonColor: 'Orange' });
        return;
      }

      if (fileInfos.length === 0) {
        Swal.fire({ title: MINIMUM_ONE_IMAGE_ERROR, confirmButtonColor: 'Orange' });
        return;
      }

      const reponsePresigned = await getPresignedURL('series', fileInfos[0].size, fileInfos[0].contentType);
      const { fileURL, signedURL } = reponsePresigned;
      await uploadToGCS(signedURL);
      await axios.post(
        '/series',
        {
          category: categoryKey,
          title,
          content,
          thumbnail: fileURL,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      );
    } catch (errorMessage) {
      console.log(errorMessage);
      set({ errorMessage });
    }
    callback();
  },
}));

export default useContentCreateStore;
