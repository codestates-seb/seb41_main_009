const getPostList = (category, page) => {
  if (category !== 'All') {
    return `categories/${category}/posts?page=${page}&size=10`;
  }

  return `posts?page=${page}&size=10`;
};

export default getPostList;
