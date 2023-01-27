import create from 'zustand';
import axios from 'axios';

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
  imageSrc: '',
  fileInfos: [],
  setImageSrc: data => set({ imageSrc: data }),
  setFileInfos: fileInfos => {
    console.log(fileInfos);
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
      imageSrc: '',
      fileInfos: [],
    }),

  postShowcase: async () => {
    const { categoryKey, content, fileInfos } = get();
    try {
      const response = await axios.post('/showcases', {
        content,
        category: categoryKey,
        fileInfos,
      });
      console.log(response.data);
    } catch (errorMessage) {
      set({ errorMessage });
    }
  },

  postSeries: async () => {
    const { categoryKey, title, content, fileInfos } = get();
    // TODO: fileInfos => presign url 으로 변경

    try {
      const response = await axios.post('/showcases', {
        category: categoryKey,
        title,
        content,
        thumnail: fileInfos,
      });
      console.log(response.data);
    } catch (errorMessage) {
      set({ errorMessage });
    }
  },
}));

export default useShowcaseCreateStore;
