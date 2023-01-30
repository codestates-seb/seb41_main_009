import create from 'zustand';
import axios from 'axios';
import { Buffer } from 'buffer';

const useShowcaseCreateStore = create((set, get) => ({
  // category
  categoryKey: '',
  categoryName: 'Category',
  setCategoryKey: categoryKey => set({ categoryKey }),
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
      const response = await axios.post(`/${basePath}/files`, {
        size,
        contentType,
      });
      return response.data;
    } catch (err) {
      console.log(err);
      return {};
    }
  },

  // Google Cloud Storage 에 binary string upload
  uploadToGCS: async (encodedImage, sigendURL) => {
    const convertBase64ToBlob = base64String => {
      const dataArray = base64String.split(';base64,');
      const contentType = dataArray[0].split(':')[1];
      const raw = Buffer.from(dataArray[1], 'base64').toString('binary');
      return [raw, contentType];
      // Blob 을 리턴하는 코드
      // const rawLength = raw.length;
      // const uInt8Array = new Uint8Array(rawLength);
      // for (let i = 0; i < rawLength; i += 1) {
      //   uInt8Array[i] = raw.charCodeAt(i);
      // }
      // return new Blob([uInt8Array], { type: contentType });
    };

    const [raw, contentType] = convertBase64ToBlob(encodedImage);
    const response = await axios.put(sigendURL, raw, {
      headers: {
        'Content-Type': `${contentType}`,
      },
    });

    return response;
  },
  postShowcase: async () => {
    const { categoryKey, content, fileInfos, imageBase64, uploadToGCS } = get();
    try {
      // showcases 로 게시물 업로드
      const response = await axios.post('/showcases', {
        content,
        category: categoryKey,
        fileInfos,
      });

      // signedURL 을 받아왔다면 해당 URL로 PUT 요청 보내기
      const { signedURL } = response.data.fileInfos[0];
      await uploadToGCS(imageBase64, signedURL);

      console.log('showcase 업로드 성공');
    } catch (errorMessage) {
      set({ errorMessage });
      console.log('showcase 업로드 실패');
      console.log(errorMessage);
    }
  },

  postSeries: async () => {
    const { categoryKey, title, content, imageBase64, fileInfos, getPresignedURL, uploadToGCS } = get();

    try {
      const { fileURL, signedURL } = getPresignedURL('series', fileInfos.size, fileInfos.contentType);
      await uploadToGCS(imageBase64, signedURL);

      const response = await axios.post('/showcases', {
        category: categoryKey,
        title,
        content,
        thumnail: fileURL,
      });
      console.log(response.data);
    } catch (errorMessage) {
      set({ errorMessage });
    }
  },
}));

export default useShowcaseCreateStore;
