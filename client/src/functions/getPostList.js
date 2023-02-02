const getPostList = (category, page) => {
  if (category !== 'All') {
    return `categories/${category}/posts?page=${page}&size=10&sort=newest`;
  }

  return `posts?page=${page}&size=10&sort=newest`;
};

export default getPostList;
