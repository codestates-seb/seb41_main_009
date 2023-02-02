const getSeriesList = (category, page) => {
  if (category !== 'All') {
    return `categories/${category}/series?page=${page}&size=10`;
  }

  return `series?page=${page}&size=10`;
};

export default getSeriesList;
