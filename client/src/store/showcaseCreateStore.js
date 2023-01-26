import create from 'zustand';
import axios from 'axios';

const useShowcaseCreateStore = create((set, get) => ({
  // category
  categoryKey: '',
  categoryName: 'Category',
  setCategoryKey: categoryKey => set({ categoryKey }),
  setCategoryName: categoryName => set({ categoryName }),

  // content
  content: '',
  setContent: content => set({ content }),

  // image
  imageSrc: '',
  fileInfos: [],
  setImageSrc: data => set({ imageSrc: data }),
  setFileInfos: fileInfos => set({ fileInfos }),

  // error handle
  errorMessage: '',
  setErrorMessage: errorMessage => set({ errorMessage }),

  // functions
  postShowcase: async () => {
    try {
      const response = await axios.post('/showcases', {
        content: get().content,
        category: get().categoryKey,
        fileInfos: get().fileInfos,
      });
      console.log(response.data);
    } catch (err) {
      set({ errorMessage: err });
    }
  },
}));

export default useShowcaseCreateStore;
