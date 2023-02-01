import create from 'zustand';
import axios from 'axios';

const useShowcaseCreateStore = create((set, get) => ({
  // category
  categoryKey: 'baseball',
  categoryName: 'Category',
  setCategoryKey: categoryKey => {
    console.log(categoryKey);
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

  // imageBinary
  imageBinary: '',
  setImageBinary: imageBinary => set({ imageBinary }),

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
    const { imageBinary, fileInfos } = get();

    const response = await axios.put(sigendURL, imageBinary, {
      headers: {
        'Content-Type': `image/${fileInfos.contentType}`,
      },
      withCredentials: false,
    });

    return response;
  },
  postShowcase: async () => {
    const { categoryKey, content, fileInfos, uploadToGCS } = get();
    try {
      // showcases 로 게시물 업로드
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
      // 업로드된 시점에서
      console.log('쇼케이스 업로드 1차 통과');
      console.log(response);

      // signedURL 을 받아왔다면 해당 URL로 PUT 요청 보내기
      const { signedURL } = response.data.fileInfos[0];
      console.log(`signedURL: ${signedURL}`);

      await uploadToGCS(signedURL);
      console.log('쇼케이스 업로드 2차 통과');

      console.log('showcase 업로드 성공');
    } catch (error) {
      set({ errorMessage: error });
      console.log('showcase 업로드 실패');
      console.log(error);
    }
  },

  postSeries: async () => {
    const { categoryKey, title, content, fileInfos, getPresignedURL, uploadToGCS } = get();

    try {
      const { fileURL, signedURL } = getPresignedURL('series', fileInfos.size, fileInfos.contentType);
      await uploadToGCS(signedURL);

      const response = await axios.post(
        '/showcases',
        {
          category: categoryKey,
          title,
          content,
          thumnail: fileURL,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      );
      console.log(response.data);
    } catch (errorMessage) {
      set({ errorMessage });
    }
  },
}));

export default useShowcaseCreateStore;
